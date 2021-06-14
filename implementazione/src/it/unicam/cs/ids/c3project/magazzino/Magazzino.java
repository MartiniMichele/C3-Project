package it.unicam.cs.ids.c3project.magazzino;

import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Magazzino implements MagazzinoInterface {

    private int ID;
    private List<Pacco> pacchiPresenti = new ArrayList<>();
    private String indirizzo;
    private List<Locker> lockers = new ArrayList<>();
    private String delegato;
    private int capienza;
    private String addetto;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Pacco> getPacchiPresenti() {
        return pacchiPresenti;
    }

    @Override
    public boolean aggiungiPacco(Pacco pacco) {
        return pacchiPresenti.add(pacco);
    }

    @Override
    public boolean rimuoviPacco(Pacco pacco) {
        return pacchiPresenti.remove(pacco);
    }

    @Override
    public boolean cercaPacco(int ID) {

        for (Pacco elem : pacchiPresenti) {
            if (elem.getID() == ID) return true;
        }

        return false;
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
