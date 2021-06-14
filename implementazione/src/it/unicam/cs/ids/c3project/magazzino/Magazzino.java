package it.unicam.cs.ids.c3project.magazzino;

import java.util.List;
import java.util.Objects;

public class Magazzino {

    private int ID;
    private String nome;
    private String indirizzo;
    private List<Locker> lockers;
    private String delegato;
    private int capienza;
    private String addetto;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public String getDelegato() {
        return delegato;
    }

    public void setDelegato(String delegato) {
        this.delegato = delegato;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public String getAddetto() {
        return addetto;
    }

    public void setAddetto(String addetto) {
        this.addetto = addetto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazzino magazzino = (Magazzino) o;
        return getID() == magazzino.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }
}
