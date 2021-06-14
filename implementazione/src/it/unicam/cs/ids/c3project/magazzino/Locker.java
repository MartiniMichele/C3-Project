package it.unicam.cs.ids.c3project.magazzino;

import java.time.LocalDate;
import java.util.Objects;

public class Locker {

    private int ID;
    private String codiceSblocco;
    private StatoLocker stato;
    private Magazzino magazzino;
    private LocalDate timer;

    private Locker(int ID, String codiceSblocco, StatoLocker stato, Magazzino magazzino, LocalDate timer) {

        this.ID = ID;
        this.codiceSblocco = codiceSblocco;
        this.stato = stato;
        this.magazzino = magazzino;
        this.timer = timer;
    }

    /**
     * Factory Method per costruire nuovi locker
     * @param ID id del locker
     * @param codisceSblocco codice di sblocco del locker
     * @param stato stato del locker
     * @param magazzino magazzino in cui si trova il locker
     * @param timer timer per il blocco del locker
     * @return il locker creato
     */
    public Locker createLocker(int ID, String codisceSblocco, StatoLocker stato, Magazzino magazzino, LocalDate timer) {
        return new Locker(ID, codiceSblocco, stato, magazzino, timer);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCodiceSblocco() {
        return codiceSblocco;
    }

    public void setCodiceSblocco(String codiceSblocco) {
        this.codiceSblocco = codiceSblocco;
    }

    public StatoLocker getStato() {
        return stato;
    }

    public void setStato(StatoLocker stato) {
        this.stato = stato;
    }

    public Magazzino getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Magazzino magazzino) {
        this.magazzino = magazzino;
    }

    public LocalDate getTimer() {
        return timer;
    }

    public void setTimer(LocalDate timer) {
        this.timer = timer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locker locker = (Locker) o;
        return getID() == locker.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }
}
