package Couche;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Transport {

    public class Npdu {
        public String type;
        public String destAddr;
        public String sourceAddr;
        public String routeAddr;
        public String data;
        public String target;
        public String connection;
        public int ps, pr;
        public boolean flag;

        public String toString() {
            return type + " " + destAddr + " " + sourceAddr + " " + routeAddr + " " + data;
        }
    
    }
    
    private final String S_LEC = "jeu_essai/s_lec.txt";
    private final String S_ECR = "jeu_essai/s_ecr.txt";
    private List<Npdu> CanalTransportToReseau;
    private static List<Npdu> listControlConnection = new ArrayList<>();
    private int destination;
    private int source;
    private String status;

    public Transport(int destination, int source, String status){

        this.destination =destination;
        this.source = source;
        this.status = status;
        CanalTransportToReseau = new ArrayList<>();

    }

    public Transport(){
        CanalTransportToReseau = new ArrayList<>();
    }

    /**
     * Methode qui permet de lire le fichier S_lec.txt
     * @return void
     */
    public void readFromTransport(){
        File myFile = new File(S_LEC);
        Npdu transportToReseau;
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
                    String numeroSource = dataFile[1];
                    String numeroDestination = null;

                    if(dataFile.length> 2)
                        numeroDestination = dataFile[2];

                    switch (directive) {
                        case "N_CONNECT":{
                            transportToReseau.type = "N_CONNECT.req";
                            transportToReseau.sourceAddr = numeroSource;
                            transportToReseau.destAddr = numeroDestination;
                            validNpdu = true;
                            break;
                        }
                        case "N_DATA":{
                            transportToReseau.type = "N_DATA.req";
                            for (int i = 1; i < dataFile.length; i++)
                                transportToReseau.data = "donnée - " + i;
                            validNpdu = true;
                            break;
                        }
                        case "N_DISCONNECT":{
                            transportToReseau.type = "N_DISCONNECT.req";
                            transportToReseau.sourceAddr = numeroSource;
                            transportToReseau.routeAddr = numeroDestination;
                            validNpdu = true;
                            break;
                        }
                        default:{
                            System.out.println("La structure du paquet n'est pas bon");
                            break;
                        }
                    }

                    if(validNpdu){
                        envoyerVersReseau(transportToReseau);
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
     * Methode qui permet d'envoyer la nouvelle connexion vers le réseau
     * Utilisation de Synchronized vu le partage de ressource entre plusieurs Threads
     * @return void
     */
    private synchronized void envoyerVersReseau(Npdu paquet){
        CanalTransportToReseau.add(paquet);
    }

    /**
     * Methode qui permet d'ajouter la connection dans la liste control du transport
     * @param connectPaquet : Le paquet dont on veut ajouter dans la liste de control de connection de la couche Transport
     * @return void
     */
    private synchronized void ajouterConnection(Npdu connectPaquet){
        if(!isConnectionExist(connectPaquet))
            listControlConnection.add(connectPaquet);
    }


     /**
      * Methode qui permet de vérifier si la connection existe déjà dans la liste de connexion du transportToReseau
      * @param connectPaquet : Le paquet dont on veut vérifier l'existence précedente
      * @return boolean
      */
    private synchronized boolean isConnectionExist(Npdu connectPaquet){
        for (Npdu npdu : listControlConnection) {
            if(npdu.equals(connectPaquet)){
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


     /**
     * Methode qui d'ecrire ddans le fichier S_ECR
     * @return String
     */
	public synchronized void writeFromTransport(){

			File file = new File(S_ECR);
            String lineRead;
            Npdu networkNNpdu;
            String[] settings;
            boolean valid;
            boolean endOfFile = false;

        try(
            FileOutputStream fos = new FileOutputStream(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            BufferedOutputStream bos = new BufferedOutputStream(fos)
            ){

           //PrintStream out = new PrintStream(bos);
            CanalTransportToReseau.forEach(e-> {
                try {
                    bufferedWriter.write(e.toString() + "\n");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
                 
		} catch (FileNotFoundException e) {
            e.printStackTrace();  
		}
        catch (IOException e) {
            e.printStackTrace();  
		}



    /*FileWriter fileWriter = new FileWriter(fileName);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.print("Some String");
    printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000);
    printWriter.close();*/
    
    }
	 
}


