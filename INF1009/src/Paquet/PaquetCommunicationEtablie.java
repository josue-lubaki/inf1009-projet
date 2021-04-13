package Paquet;

import Enum.Directive;
import Enum.StatusConnection;

public class PaquetCommunicationEtablie extends Paquet {
    private int numeroConnexion;
    private StatusConnection type;
    private int adresseSource;
    private int adresseDestination;

    public PaquetCommunicationEtablie(int adresseSource, int adresseDestination) {
        super(adresseSource,adresseDestination);
        this.type = StatusConnection.ConnectionEtablie;
    }
    
    public StatusConnection getType() {
        return type;
    }

    
    public void setType(StatusConnection type) {
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
