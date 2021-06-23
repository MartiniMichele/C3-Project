package it.unicam.cs.ids.c3project.personale;

/**
 * classe che ha il compito di rappresentare un responsabile
 */
public class Responsabile extends Commesso {

    boolean isResponsabile;

    public Responsabile(String nome) {
        super(nome);
        isResponsabile = true;
    }


    @Override
    public boolean isResponsabile() {
        return isResponsabile;
    }


}
