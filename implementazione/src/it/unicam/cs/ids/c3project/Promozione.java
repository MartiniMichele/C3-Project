package it.unicam.cs.ids.c3project;

import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare una promozione per un negozio
 */
public class Promozione {
    String nome;
    static int ID = 0;
    String negozio;
    int puntiBonus;

    Promozione(String nome, String negozio, int puntiBonus) {
        ID++;
        this.nome = nome;
        this.negozio = negozio;
        this.puntiBonus = puntiBonus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getID() {
        return ID;
    }

    public String getNegozio() {
        return negozio;
    }

    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }

    public int getPuntiBonus() {
        return puntiBonus;
    }

    public void setPuntiBonus(int puntiBonus) {
        this.puntiBonus = puntiBonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promozione that = (Promozione) o;
        return getID() == that.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }
}
