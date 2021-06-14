package it.unicam.cs.ids.c3project.magazzino;

import java.util.List;

public class MagazzinoDirector {
    MagazzinoBuilder builder;

    public void setBuilder(MagazzinoBuilder builder) {
        this.builder = builder;
    }

    public Magazzino makeMagazzinoLocker(int ID, String nome, String indirizzo, List<Locker> lockers, int capienza, String addetto) {

        builder.setID(ID);
        builder.setNome(nome);
        builder.setLockers(lockers);
        builder.setDelegato(null);
        builder.setCapienza(capienza);
        builder.setAddetto(addetto);

        return builder.getResult();
    }

    public Magazzino makeMagazzinoDelegato(int ID, String nome, String indirizzo, String delegato, int capienza, String addetto) {

        builder.setID(ID);
        builder.setNome(nome);
        builder.setLockers(null);
        builder.setDelegato(delegato);
        builder.setCapienza(capienza);
        builder.setAddetto(addetto);

        return builder.getResult();
    }
}