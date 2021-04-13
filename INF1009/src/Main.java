import java.util.Queue;
import java.util.concurrent.SynchronousQueue;

import Couche.Transport;
import Paquet.Npdu;



public class Main {
    private static Queue<Npdu> CanalTransportToReseau = new SynchronousQueue<Npdu>();
    //private static Transport transport = new Transport(CanalTransportToReseau);
    private static Transport transport = new Transport();
    private static String message;
    private static int nbTest = 0;
    private static Thread lireDeTransport, ecrireDeTransport, lireDeReseau, ecrireDeReseau;


    public static void main(String[] args){
        demarreThreads();
        int nbreThreadCurrent = Thread.activeCount();
        System.out.println(nbreThreadCurrent);
    }

    /**
     * Methode qui permet de générer les addresses sources et destinations
     * @code {destination} correspond également au numero de connexion
     * @return void
     */
    private static void genererSourceDestination(){

        int destination =  transport.setAdresseDestination();
        int source = transport.setAdresseSource(destination);

        message = "N_CONNECT " + source + " " + destination + "\n" + 
                "N_DATA " + destination + " Donnée-" +  ++nbTest + "\n" +
                "N_DISCONNECT " + source + " " + destination + "\n";
 
        transport.writeTo_S_ECR(message);
    }

    /**
     * Methode qui permet de demarrer les Threads 
     * @return void
     */
    private static void demarreThreads(){
        lireDeTransport = new Thread(transport::readFromTransport);
        lireDeTransport.setName("Thread-lireDeTransport");
        lireDeTransport.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ecrireDeTransport = new Thread(transport::writeFromTransport);
        ecrireDeTransport.setName("Thread-ecrireDeTransport");
        ecrireDeTransport.start(); 
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}




