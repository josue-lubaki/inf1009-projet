package Paquet;

import java.util.Arrays;

class PaquetAcquitementNegatif {
    private int numeroConnexion;
    private byte[] paquet;

    public PaquetAcquitementNegatif(int numeroConnexion, byte[] paquet) {
        byte[] array = new byte[]{1,0,0};
        if(Arrays.equals(getPs(), array)){
            this.numeroConnexion = numeroConnexion;
            this.paquet = paquet;
        }
        else{
            throw new IllegalArgumentException("Ce n'est pas le bon acquittement");
        }
    }

    public int getNumeroConnexion() {
        return numeroConnexion;
    }

    public void setNumeroConnexion(int numeroConnexion) {
        this.numeroConnexion = numeroConnexion;
    }

    public void setPaquet(byte[] paquet) {
        this.paquet = paquet;
    }

    public byte[] getPaquet() {
        return paquet;
    }

    public byte[] getPr(){
        return Arrays.copyOfRange(paquet, 0, 3);
    }

    public byte[] getPs(){
        return Arrays.copyOfRange(paquet, 4, 7);
    }

    public byte getM(){
        return paquet[3];
    }

}
