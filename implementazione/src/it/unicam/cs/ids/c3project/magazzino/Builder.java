package it.unicam.cs.ids.c3project.magazzino;

import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.util.List;

public interface Builder {

    void reset();
    void setID(int ID);
    void setIndirizzo(String indirizzo);
    void setLockers(List<Locker> lockers);
    void setDelegato(String delegato);
    void setCapienza(int capienza);
    void setAddetto(String addetto);
    MagazzinoInterface getResult();
}
