package it.unicam.cs.ids.c3project.cliente;

import it.unicam.cs.ids.c3project.contenuto.Pacco;
import it.unicam.cs.ids.c3project.negozio.Negozio;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String username;
    private int saldoPunti;
    private String indirizzo;
    private List<Negozio> preferiti;
    private List<Pacco> ordiniEffettuati;


    public Cliente(String username, int saldoPunti, String indirizzo){
        this.username = username;
        this.saldoPunti = saldoPunti;
        this.indirizzo = indirizzo;
        this.preferiti=new ArrayList<>();
        this.ordiniEffettuati=new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSaldoPunti() {
        return saldoPunti;
    }

    public void setSaldoPunti(int saldoPunti) {
        this.saldoPunti = saldoPunti;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Negozio> getPreferiti() {
        return preferiti;
    }

    public void addPreferito(Negozio negozio) {
        this.preferiti.add(negozio);
    }

    public boolean contienePreferito(Negozio negozio) {
        return preferiti.contains(negozio);
    }

    public void aggiungiPaccoAtteso(int id){

        //this.ordiniEffettuati.add();
    }
}
