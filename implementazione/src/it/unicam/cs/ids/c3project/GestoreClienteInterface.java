package it.unicam.cs.ids.c3project;

public interface GestoreClienteInterface {

    boolean creaCliente(String username, int saldoPunti, String indirizzo);
    boolean usaFidelityPoint(String cliente, int punti);
    boolean assegnaFidelityPoint(String cliente, int punti);
    boolean addPreferiti(String cliente, Negozio negozio);
}
