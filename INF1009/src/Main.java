import Couche.Transport;


public class Main {
    static Transport transport = new Transport();
    static String message;
    static int nbTest = 0;

    public static void main(String[] args){
        genererSourceDestination();
        transport.readFromTransport();
        transport.writeFromTransport();
    }

    /**
     * Methode qui permet de générer les addresses sources et destinations
     * return void
     */
    private static void genererSourceDestination(){

        int destination =  transport.setAdresseDestination();
        int source = transport.setAdresseSource(destination);

        message = "N_CONNECT " + source + " " + destination + "\n" + 
                "N_DATA test #:  " + "Donnée - " +  ++nbTest + "\n" +
                "N_DISCONNECT " + source + " " + destination + "\n";

                //-->to-do : ecrire dans le fichier
                System.out.println(message );
    }


    
}




