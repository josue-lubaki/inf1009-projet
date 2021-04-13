package Paquet;

public class Npdu {
    public Paquet paquet;
    public String routeAddr;
    public String data;
    public String target;
    public int ps, pr;
    public boolean flag;
    public int connection;
    public boolean status;

    public String toString() {
        return paquet.toString();
    }

}