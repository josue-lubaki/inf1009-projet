package Paquet;

import Enum.Directive;

public class PaquetIndicationLiberation extends Paquet {
    private Directive type;
    private int adresseSource;
    private int adresseDestination;
    private String raison;

    public PaquetIndicationLiberation(int adresseSource, int adresseDestination, String raison) {
        super(adresseSource,adresseDestination);
        this.type = Directive.N_DISCONNECT_req;
        this.raison = raison;
    }

    public Directive getType() {
        return type;
    }

    public void setType(Directive type) {
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
