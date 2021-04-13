package Paquet;

import Enum.Directive;

public class PaquetDonnees extends Paquet{
    private Directive type;
    private String donnees;
    private int adresseDestination;
    // P(s), P(R) et M

    public PaquetDonnees(int addresseSource, int adresseDestination, String donnees) {
        super(addresseSource,adresseDestination);
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

  
}
