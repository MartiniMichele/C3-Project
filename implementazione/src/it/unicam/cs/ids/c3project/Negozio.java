package it.unicam.cs.ids.c3project;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare un negozio con le sue caratteristiche
 */
public class Negozio {

    String nome;
    Responsabile responsabile;
    String indirizzo;
    String tipologia;
    List<Personale> personale;
    List<Promozione> promozioni;
    List<String> categorie;

    Negozio(String nome, String indirizzo, String tipologia, List<Personale> personale, List<Promozione> promozioni, List<String> categorie) {

        this.nome = nome;
        this.responsabile = obtainResponsabile(personale);
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
        this.personale = personale;
        this.promozioni = promozioni;
        this.categorie = categorie;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Responsabile getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(Responsabile responsabile) {
        this.responsabile = responsabile;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public List<Personale> getPersonale() {
        return personale;
    }

    public void addPersonale(Personale personale) {
        this.personale.add(personale);
    }

    public List<Promozione> getPromozioni() {
        return promozioni;
    }

    public void addPromozione(Promozione promozione) {
        this.promozioni.add(promozione);
    }

    public List<String> getcategorie() {
        return categorie;
    }

    public void addcategoria(String categoria) {
        this.categorie.add(categoria);
    }

    /**
     * metodo di supporto per ottenere il responsabile dalla lista del personale
     * @param personale la lista del personale del negozio
     * @return il responsabile del negozio
     */
    private Responsabile obtainResponsabile(List<Personale> personale) {

        Iterator<Personale> iterator = personale.iterator();
        Personale responsabile = null;

        for (Personale elemento : personale) {

            if (elemento.isResponsabile()) {
                responsabile = elemento;
            }
        }

        return  (Responsabile) responsabile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negozio negozio = (Negozio) o;
        return getNome().equals(negozio.getNome()) &&
                getIndirizzo().equals(negozio.getIndirizzo()) &&
                getTipologia().equals(negozio.getTipologia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getIndirizzo(), getTipologia());
    }
}
