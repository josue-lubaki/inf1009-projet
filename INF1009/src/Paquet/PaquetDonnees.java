package Paquet;

import Enum.Directive;

public class PaquetDonnees extends Paquet{
    private Directive type;
    private String donnees;
    // P(s), P(R) et M

    public PaquetDonnees(int adresseSource, int adresseDestination, String donnees) {
        super(adresseSource,adresseDestination);
        this.type = Directive.N_DATA_req;
        this.donnees = donnees;
    }

    public int getAdresseDestination(){
        return adresseDestination;
    }

    public void setAdresseDestination(int adresseDestination) {
        this.adresseDestination = adresseDestination;
    }

    public Directive getType() {
        return type;
    }

    public void setType(Directive type) {
        this.type = type;
    }

    public String getDonnees() {
        return donnees;
    }

    public void setDonnees(String donnees) {
        this.donnees = donnees;
    }

    public int getAdresseSource() {
        return adresseSource;
    }
    
    public void setAdresseSource(int adresseSource) {
        this.adresseSource = adresseSource;
    }

    @Override
    public String toString() {
        return type + " " + adresseSource + " " +  adresseDestination + " " + donnees;
    }
 
}
