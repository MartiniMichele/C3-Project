package it.unicam.cs.ids.c3project.cliente;

import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.negozio.Negozio;

import java.util.List;

public class GestoreCliente {

    private List<Cliente> clienti;
    private static GestoreCliente istanzaGestore;

    private GestoreCliente() {}

    public static GestoreCliente getInstance() {
        if (istanzaGestore == null) istanzaGestore = new GestoreCliente();
        return istanzaGestore;
    }


    public boolean creaCliente(String username, int saldoPunti, String indirizzo) {

        Cliente newCliente = new Cliente(username, saldoPunti, indirizzo);
        clienti.add(newCliente);

        return clienti.contains(newCliente);
    }

    public boolean usaFidelityPoint(String cliente, int punti) {

        Cliente istanzaCliente = searchCliente(cliente);
        int oldPoints = istanzaCliente.getSaldoPunti();

        istanzaCliente.setSaldoPunti(oldPoints - punti);
        return istanzaCliente.getSaldoPunti() < oldPoints;
    }

    public boolean assegnaFidelityPoint(String cliente, int punti) {

        Cliente istanzaCliente = searchCliente(cliente);
        int oldPoints = istanzaCliente.getSaldoPunti();

        istanzaCliente.setSaldoPunti(oldPoints + punti);
        return istanzaCliente.getSaldoPunti() > oldPoints;
    }

    public boolean addPreferiti(String cliente, Negozio negozio) {

        Cliente istanzaCliente = searchCliente(cliente);
        istanzaCliente.addPreferito(negozio);

        return istanzaCliente.contienePreferito(negozio);
    }

    private  Cliente searchCliente(String username) {

        Cliente result = null;

        for (Cliente elemento : clienti) {
            if (elemento.getUsername().equals(username)) {
                result = elemento;
                break;
            }
        }
        return result;
    }
}
