package it.unicam.cs.ids.c3project.magazzino;

import java.util.List;

public interface Builder {

    void reset();
    void setID(int ID);
    void setNome(String nome);
    void setIndirizzo(String indirizzo);
    void setLockers(List<Locker> lockers);
    void setDelegato(String delegato);
    void setCapienza(int capienza);
    void setAddetto(String addetto);
    Magazzino getResult();
}
