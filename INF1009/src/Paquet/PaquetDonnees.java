package Paquet;

public class PaquetDonnees {
    private int numeroConnexion;
    private byte[] type;
    private String donnees;
    // P(s), P(R) et M

    public PaquetDonnees(int numeroConnexion, byte[] type, String donnees) {
        this.numeroConnexion = numeroConnexion;
        this.type = type;
        this.donnees = donnees;
    }

    public int getNumeroConnexion() {
        return numeroConnexion;
    }

    public void setNumeroConnexion(int numeroConnexion) {
        this.numeroConnexion = numeroConnexion;
    }

    public byte[] getType() {
        return type;
    }

    public void setType(byte[] type) {
        this.type = type;
    }

    public String getDonnees() {
        return donnees;
    }

    public void setDonnees(String donnees) {
        this.donnees = donnees;
    }
}
