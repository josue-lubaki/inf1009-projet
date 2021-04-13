package Couche;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import Paquet.Npdu;
import Paquet.PaquetAppel;
import Paquet.PaquetDonnees;
import Paquet.PaquetLiberation;

public class Transport {
   
    Thread threadTransport, lireDeTransport, ecrireDeTransport;
    private final String S_LEC = "jeu_essai/s_lec.txt";
    private final String S_ECR = "jeu_essai/s_ecr.txt";
    private Queue<Npdu> CanalTransportToReseau;
    private static List<Npdu> listControlConnection = new ArrayList<>();

    public Transport(Queue<Npdu> canalTransportToReseau2) {
        this.CanalTransportToReseau = canalTransportToReseau2;
    }

    public Transport() {
        this.CanalTransportToReseau = new LinkedList<>();
    }


    /**
     * Methode qui permet de lire le fichier S_lec.txt
     */
    public void readFromTransport(){
        File myFile = new File(S_LEC);
        Npdu transportToReseau;
        PaquetAppel paquetAppel;
        PaquetDonnees paquetDonnee;
        PaquetLiberation paquetLiberation;
        try(Scanner myReader = new Scanner(myFile)){
            while(myReader.hasNextLine()){
                transportToReseau = new Npdu();
                String lineRead = myReader.nextLine();
                String[] dataFile = lineRead.split(" ");
                boolean validNpdu = false;
                
                // Si c'est la fin du fichier
                if(lineRead.equals(""))
                    break;

                else{

                    String directive = dataFile[0];
                    int numeroSource = Integer.parseInt(dataFile[1]);
                    int numeroDestination = 0;

                    if(dataFile.length> 2)
                        numeroDestination = Integer.parseInt(dataFile[2]);

                    switch (directive) {
                        case "N_CONNECT":{
                            paquetAppel = new PaquetAppel(numeroSource,numeroDestination);
                            transportToReseau.paquet = paquetAppel;
                            validNpdu = true;
                            transportToReseau.connection = genererNumeroConnection();
                            break;
                        }
                        case "N_DATA":{
                            paquetDonnee = new PaquetDonnees(numeroSource, numeroDestination, dataFile[3]);
                            transportToReseau.paquet = paquetDonnee;
                            validNpdu = true;
                            break;
                        }
                        case "N_DISCONNECT":{
                            paquetLiberation = new PaquetLiberation(numeroSource, numeroDestination);
                            transportToReseau.paquet = paquetLiberation;
                            validNpdu = true;
                            break;
                        }
                        default:{
                            System.out.println("La structure du paquet n'est pas bon");
                            break;
                        }
                    }

                    if(validNpdu){
                        ajouterConnection(transportToReseau);
                    }
                }
            }
            
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
			e.printStackTrace();
        }
    }


     /**
     * Methode qui d'ecrire ddans le fichier S_ECR
     * @throws FileNotFoundException : Si le fichier n'a pas été trouvé
     * @throws IOException : erreur au niveau du system I/O
     * 
     * @return String
     */
	public void writeFromTransport(){
        File file = new File(S_ECR);
        
        try(
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            PrintWriter out = new PrintWriter(bw)
            ){

            CanalTransportToReseau.forEach(e ->{
                    out.println(e.toString());
            });
                
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        catch (IOException e) {
            e.printStackTrace();  
        }
    }

    /**
     * Methode qui permet d'écrire dans le fichier S_ECR
     * @param message : le message à écrire dans le fichier
     * 
     * @throws FileNotFoundException : Si le fichier n'a pas été trouvé
     * @throws IOException : erreur au niveau du system I/O
     * @return void
     */
    public void writeTo_S_ECR(String message){
        File file = new File(S_ECR);
        try(
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        PrintWriter out = new PrintWriter(bw)){
            out.println(message.toString());  
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        catch (IOException e) {
            e.printStackTrace();  
        }
    }


    /**
     * Methode qui permet de générer un nombre correspondant au numero de Connexion
     * @return int
     */
    private int genererNumeroConnection() {
        return getRandomNumber(255);
    }

    /**
     * Methode qui permet d'envoyer la nouvelle connexion vers le réseau
     * Utilisation de Synchronized vu le partage de ressource entre plusieurs Threads
     * @throws IllegalStateException : La methode a été invoquer de manière illégal ou un moment inapproprié !
     * @return void
     */
    private void envoyerVersReseau(Npdu paquet) throws IllegalStateException{
        CanalTransportToReseau.offer(paquet);
    }

    /**
     * Methode qui permet d'ajouter la connection dans la liste control du transport
     * @param connectPaquet : Le paquet dont on veut ajouter dans la liste de control de connection de la couche Transport
     * et envoyer vers la couche Reséau
     * @return void
     */
    private void ajouterConnection(Npdu connectPaquet){
        if(!isConnectionExist(connectPaquet.paquet.getAdresseSource(), connectPaquet.paquet.getAdresseDestination()))
            listControlConnection.add(connectPaquet);

        //if(connectPaquet.status)
            envoyerVersReseau(connectPaquet);
    }


     /**
      * Methode qui permet de vérifier si la connection existe déjà dans la liste de connexion du transportToReseau
      * @param numeroConnection : Le paquet dont on veut vérifier l'existence précedente
      * @return boolean
      */
    private boolean isConnectionExist(int adresseSource, int adresseDestination){
        for (Npdu npdu : listControlConnection) {
            if(npdu.paquet.getAdresseSource() == adresseSource &&
                npdu.paquet.getAdresseDestination() == adresseDestination ){
                return true;
            }
        }

        return false;
    }


    /**
     * Methode qui permet de selectionner un chiffre aleatoirement
     * @return int
     */
    public int getRandomNumber(int max){
        
        Random random = new Random();
        return random.nextInt(max) + 1;
         
    }

    /**
     * Methode qui permet de set l'adresse d'une source
     * @return String
     */

     public int setAdresseSource(int destination){

        int source;

        do {
            source = getRandomNumber(255);
        }while(destination == source);

        return source;
     }

     
     /**
     * Methode qui permet de set l'adresse d'une source
     * @return String
     */
    public int setAdresseDestination(){
        return getRandomNumber(255);
    }

	 
}


