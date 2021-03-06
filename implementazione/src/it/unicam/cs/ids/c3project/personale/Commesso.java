package it.unicam.cs.ids.c3project.personale;

import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare un commesso di un negozio.
 * implementa l'interfaccia {@link Personale}
 */
public class Commesso implements Personale {

    protected String nome;
    boolean isResponsabile;

    public Commesso(String nome) {
        this.nome = nome;
        isResponsabile = false;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public void richiedereSpedizione(int idVenditore, int idOrdine) {

    }

    @Override
    public void attribuzionePunti(int punti) {

    }

    @Override
    public boolean isResponsabile() {
        return isResponsabile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commesso commesso = (Commesso) o;
        return isResponsabile == commesso.isResponsabile && nome.equals(commesso.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, isResponsabile);
    }

}



