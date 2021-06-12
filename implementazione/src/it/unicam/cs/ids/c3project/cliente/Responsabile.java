package it.unicam.cs.ids.c3project.cliente;

/**
 * classe che ha il compito di rappresentare un responsabile
 */
public class Responsabile extends Commesso {

    Responsabile(String nome) {
        super(nome);
    }

    void gestioneVetrina() {

    }

    @Override
    public boolean isResponsabile() {
        return true;
    }
}
