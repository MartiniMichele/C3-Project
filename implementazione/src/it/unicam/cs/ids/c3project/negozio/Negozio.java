package it.unicam.cs.ids.c3project.negozio;

import it.unicam.cs.ids.c3project.personale.Personale;
import it.unicam.cs.ids.c3project.personale.Responsabile;

import java.util.List;
import java.util.Objects;

/**
 * Classe che ha il compito di rappresentare un negozio con le sue caratteristiche
 */
public class Negozio {

    private int id;
    private Responsabile responsabile;
    private Vetrina vetrina;
    private List<Personale> personale;

   public Negozio(Responsabile responsabile, List<Personale> personale ) {
        this.responsabile = obtainResponsabile(personale);
        this.personale = personale;
    }

    public int getId() {
        return id;
    }

    public Vetrina getVetrina() {
        return vetrina;
    }

    public Responsabile getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(Responsabile responsabile) {
        this.responsabile = responsabile;
    }



    public List<Personale> getPersonale() {
        return personale;
    }

    public void addPersonale(Personale personale) {
        this.personale.add(personale);
    }

 /*   public List<Promozione> getPromozioni() {
        return promozioni;
    }

    public void addPromozione(Promozione promozione) {

        if (!promozioneDuplicata(promozione.getID()))
        this.promozioni.add(promozione);

        else throw new IllegalArgumentException();
    }*/



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

  /*  private boolean promozioneDuplicata(int ID) {

        boolean result = false;

        for (Promozione elemento : promozioni) {

            if (elemento.getID() == ID) {
                result = true;
                break;
            }
        }

        return  result;
    }*/
    public void creaVetrina(String nome, String tipologia, String indirizzo, String contatto){
        Vetrina vetrina=new Vetrina(nome, indirizzo, tipologia, contatto);
        this.vetrina=vetrina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negozio negozio = (Negozio) o;
        return id == negozio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
