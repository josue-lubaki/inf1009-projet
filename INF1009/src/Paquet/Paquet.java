package Paquet;

public abstract class Paquet {
    protected int adresseSource;
    protected int adresseDestination;

    public Paquet(int adresseSource, int adresseDestination) {
        this.adresseSource = adresseSource;
        this.adresseDestination = adresseDestination;
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
        if(this instanceof PaquetAppel){
            PaquetAppel paquet = (PaquetAppel)this;
            return paquet.getType() + " " + paquet.getAdresseSource() + " " +  paquet.getAdresseDestination();
        }
           
        else if(this instanceof PaquetDonnees){
            PaquetDonnees paquet = (PaquetDonnees)this;
            return paquet.getType() + " " + paquet.getAdresseDestination() + " " + paquet.getDonnees();
        }
            
        else if(this instanceof PaquetLiberation){
            PaquetLiberation paquet = (PaquetLiberation)this;
            return paquet.getType() + " " + paquet.getAdresseSource() + " " + paquet.getAdresseDestination();
        }

        return "Paquet [adresseDestination=" + adresseDestination + ", adresseSource=" + adresseSource + "]";
            
    }
    
}
