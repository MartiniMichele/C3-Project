package it.unicam.cs.ids.c3project.magazzino;

import java.util.List;

public class MagazzinoDirector {
    MagazzinoBuilder builder;

    public MagazzinoDirector(MagazzinoBuilder builder) {
        this.builder = builder;
    }

    public MagazzinoInterface makeMagazzinoLocker(int ID, String indirizzo, List<Locker> lockers, int capienza, String addetto) {

        builder.setID(ID);
        builder.setLockers(lockers);
        builder.setDelegato(null);
        builder.setCapienza(capienza);
        builder.setAddetto(addetto);

        return builder.getResult();
    }

    public MagazzinoInterface makeMagazzinoDelegato(int ID, String indirizzo, String delegato, int capienza, String addetto) {

        builder.setID(ID);
        builder.setLockers(null);
        builder.setDelegato(delegato);
        builder.setCapienza(capienza);
        builder.setAddetto(addetto);

        return builder.getResult();
    }
}