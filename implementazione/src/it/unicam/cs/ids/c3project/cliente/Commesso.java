package it.unicam.cs.ids.c3project.cliente;

import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare un commesso di un negozio.
 * implementa l'interfaccia {@link Personale}
 */
public class Commesso implements Personale {

    protected static int ID = 0;
    protected String nome;

    Commesso(String nome) {
        ID++;
        this.nome = nome;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void richiedereSpedizione() {

    }

    @Override
    public void attribuzionePunti(int punti) {

    }

    @Override
    public boolean isResponsabile() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commesso commesso = (Commesso) o;
        return ID == commesso.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
