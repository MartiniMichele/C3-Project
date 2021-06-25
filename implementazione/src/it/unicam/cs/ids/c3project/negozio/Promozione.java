package it.unicam.cs.ids.c3project.negozio;

import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare una promozione per un negozio
 */
public class Promozione {
    private String nome;
    private static int ID = 0;
    private String negozio;
    private int puntiBonus;

   public Promozione(String nome, String nomeNegozio, int puntiBonus) {
        ID++;
        this.nome = nome;
        this.negozio = nomeNegozio;
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
        return getNome().equals(that.getNome()) && getNegozio().equals(that.getNegozio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }

    @Override
    public String toString() {
        return "Promozione" +
                ":'" + nome + '\'' +
                ", negozio='" + negozio + '\'' +
                ", puntiBonus=" + puntiBonus +
                '}';
    }
}
