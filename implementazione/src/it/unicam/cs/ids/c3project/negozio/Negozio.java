package it.unicam.cs.ids.c3project.negozio;

import it.unicam.cs.ids.c3project.cliente.Personale;
import it.unicam.cs.ids.c3project.cliente.Responsabile;

import java.util.List;
import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare un negozio con le sue caratteristiche
 */
public class Negozio {

    private String nome;
    private Responsabile responsabile;
    private Vetrina vetrina;
    private String indirizzo;
    private String tipologia;
    private List<Personale> personale;
    private List<Promozione> promozioni;
    private List<String> categorie;

   public Negozio(String nome, String indirizzo, String tipologia, List<Personale> personale, List<Promozione> promozioni, List<String> categorie) {
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

        if (!promozioneDuplicata(promozione.getID()))
        this.promozioni.add(promozione);

        else throw new IllegalArgumentException();
    }

    public List<String> getcategorie() {
        return categorie;
    }

    public void addCategoria(String categoria) {
        this.categorie.add(categoria);
    }

    public boolean contieneCategoria(String categoria) {
        return categorie.contains(categoria);
    }

    public boolean contienePromozione(Promozione promozione) {
        return promozioni.contains(promozione);
    }

    /**
     * metodo di supporto per ottenere il responsabile dalla lista del personale
     * @param personale la lista del personale del negozio
     * @return il responsabile del negozio
     */
    private Responsabile obtainResponsabile(List<Personale> personale) {

        Personale responsabile = null;

        for (Personale elemento : personale) {

            if (elemento.isResponsabile()) {
                responsabile = elemento;
            }
        }

        return  (Responsabile) responsabile;
    }

    private boolean promozioneDuplicata(int ID) {

        boolean result = false;

        for (Promozione elemento : promozioni) {

            if (elemento.getID() == ID) {
                result = true;
                break;
            }
        }

        return  result;
    }
    public void creaVetrina(String nome, String tipologia, String indirizzo, String contatto){
        Vetrina vetrina=new Vetrina(nome, indirizzo, tipologia, contatto);
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
