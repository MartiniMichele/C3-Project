package it.unicam.cs.ids.c3project;

import java.util.List;

public class GestoreCliente implements GestoreClienteInterface {

    private static List<Cliente> clienti;


    @Override
    public boolean creaCliente(String username, int saldoPunti, String indirizzo) {

        Cliente newCliente = new Cliente(username, saldoPunti, indirizzo);
        clienti.add(newCliente);

        return clienti.contains(newCliente);
    }

    @Override
    public boolean usaFidelityPoint(String cliente, int punti) {

        Cliente istanzaCliente = searchCliente(cliente);
        int oldPoints = istanzaCliente.getSaldoPunti();

        istanzaCliente.setSaldoPunti(oldPoints - punti);
        return istanzaCliente.getSaldoPunti() < oldPoints;
    }

    @Override
    public boolean assegnaFidelityPoint(String cliente, int punti) {

        Cliente istanzaCliente = searchCliente(cliente);
        int oldPoints = istanzaCliente.getSaldoPunti();

        istanzaCliente.setSaldoPunti(oldPoints + punti);
        return istanzaCliente.getSaldoPunti() > oldPoints;
    }

    @Override
    public boolean addPreferity(String cliente, Negozio negozio) {

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
