package Paquet;

public abstract class Paquet {
    private int numeroConnexion;
    private byte[] type;
    private int adresseSource;
    private int adresseDestination;

    public Paquet(int numeroConnexion, byte[] type, int adresseSource, int adresseDestination) {
        this.numeroConnexion = numeroConnexion;
        this.type = type;
        this.adresseSource = adresseSource;
        this.adresseDestination = adresseDestination;
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

    public int getAdresseSource() {
        return adresseSource;
    }

    public void setAdresseSource(int adresseSource) {
        this.adresseSource = adresseSource;
    }

    public int getAdresseDestination() {
        return adresseDestination;
    }

    public void setAdresseDestination(int adresseDestination) {
        this.adresseDestination = adresseDestination;
    }
}
