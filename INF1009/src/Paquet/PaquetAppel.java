package Paquet;

import Enum.Directive;

public class PaquetAppel extends Paquet {
    private Directive type;

    public PaquetAppel(int adresseSource, int adresseDestination) {
        super(adresseSource,adresseDestination);
        this.type = Directive.N_CONNECT_req;
    }
   
    public Directive getType() {
        return type;
    }

    
    public void setType(Directive type) {
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

    @Override
    public String toString() {
        return type + " " + adresseSource + " " + adresseDestination;
    }

    
    
}
