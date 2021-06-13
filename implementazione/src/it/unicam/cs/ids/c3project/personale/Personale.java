package it.unicam.cs.ids.c3project.personale;

import java.util.List;

/**
 * Interfaccia implementata dalle classi che rappresentano del personale che lavora in un negozio
 */
public interface Personale {

    int getId();
    void richiedereSpedizione(int idVenditore, int idOrdine);
    void attribuzionePunti(int punti);
    boolean isResponsabile();

}
