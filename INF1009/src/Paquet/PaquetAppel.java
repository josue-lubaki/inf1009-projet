package Paquet;

import Enum.Directive;

public class PaquetAppel extends Paquet {
    private Directive type;
    private int adresseSource;
    private int adresseDestination;

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
    
}
