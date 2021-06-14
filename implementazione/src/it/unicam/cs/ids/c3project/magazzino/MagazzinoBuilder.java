package it.unicam.cs.ids.c3project.magazzino;

import java.util.List;

public class MagazzinoBuilder implements Builder {

    private Magazzino magazzino;

    @Override
    public void reset() {
        this.magazzino = new Magazzino();
    }

    @Override
    public void setID(int ID) {
        this.magazzino.setID(ID);
    }

    @Override
    public void setIndirizzo(String indirizzo) {
        this.magazzino.setIndirizzo(indirizzo);
    }

    @Override
    public void setLockers(List<Locker> lockers) {
        this.magazzino.setLockers(lockers);
    }

    @Override
    public void setDelegato(String delegato) {
        this.magazzino.setDelegato(delegato);
    }

    @Override
    public void setCapienza(int capienza) {
        this.magazzino.setCapienza(capienza);
    }

    @Override
    public void setAddetto(String addetto) {
        this.magazzino.setAddetto(addetto);
    }

    public MagazzinoInterface getResult() {
        Magazzino result = this.magazzino;
        reset();
        return result;
    }

}
