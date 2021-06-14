package it.unicam.cs.ids.c3project.magazzino;

import it.unicam.cs.ids.c3project.contenuto.Pacco;

public interface MagazzinoInterface {

    boolean cercaPacco(int ID);

    boolean aggiungiPacco(Pacco pacco);

    boolean rimuoviPacco(Pacco pacco);
}
