package Paquet;

public class PaquetIndicationLiberation extends Paquet {
    private int numeroConnexion;
    private byte[] type;
    private int adresseSource;
    private int adresseDestination;
    private String raison;

    public PaquetIndicationLiberation(int numeroConnexion, byte[] type, int adresseSource, int adresseDestination, String raison) {
        super(numeroConnexion,type,adresseSource,adresseDestination);
        this.type = new byte[]{0,0,0,1,0,0,1,1};
        this.raison = raison;
    }

    @Override
    public int getNumeroConnexion() {
        return numeroConnexion;
    }

    @Override
    public void setNumeroConnexion(int numeroConnexion) {
        this.numeroConnexion = numeroConnexion;
    }

    @Override
    public byte[] getType() {
        return type;
    }

    @Override
    public void setType(byte[] type) {
        this.type = type;
    }

    @Override
    public int getAdresseSource() {
        return adresseSource;
    }

    @Override
    public void setAdresseSource(int adresseSource) {
        this.adresseSource = adresseSource;
    }

    @Override
    public int getAdresseDestination() {
        return adresseDestination;
    }

    @Override
    public void setAdresseDestination(int adresseDestination) {
        this.adresseDestination = adresseDestination;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }
}