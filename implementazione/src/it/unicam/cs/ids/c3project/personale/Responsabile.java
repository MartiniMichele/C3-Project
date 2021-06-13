package it.unicam.cs.ids.c3project.personale;

/**
 * classe che ha il compito di rappresentare un responsabile
 */
public class Responsabile extends Commesso {

    Responsabile(String nome) {
        super(nome);
    }


    @Override
    public boolean isResponsabile() {
        return true;
    }


}
