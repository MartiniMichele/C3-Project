package it.unicam.cs.ids.c3project.cliente;

import it.unicam.cs.ids.c3project.negozio.Negozio;

public interface GestoreClienteInterface {

    boolean creaCliente(String username, int saldoPunti, String indirizzo);
    boolean usaFidelityPoint(String cliente, int punti);
    boolean assegnaFidelityPoint(String cliente, int punti);
    boolean addPreferiti(String cliente, Negozio negozio);
}
