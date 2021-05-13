package it.unicam.cs.ids.c3project;

import java.util.ArrayList;
import java.util.List;

/**
 * classe gestore che ha il compito di aggiungere e gestire i negozi
 */
public class GestoreNegozio implements GestoreNegozioInterface {

    private  static List<Negozio> negozi = new ArrayList<>();       //lista dei negozi aggiunti


    /**
     * metodo che ritorna la lista dei negozi
     * @return la lista dei negozi
     */
    public List<Negozio> getNegozi() {
        return negozi;
    }

    @Override
    public boolean aggiungiCategoria(String categoria, String negozio) {

        Negozio istanzaNegozio = getNegozio(negozio);
        istanzaNegozio.addcategoria(categoria);

        return istanzaNegozio.categorie.contains(categoria);
    }

    @Override
    public boolean rimuoviCategoria(String categoria, String negozio) {
        return false;
    }

    @Override
    public boolean avviaPromozione(String nome, String negozio, int puntiBonus) {
        return false;
    }

    @Override
    public boolean addNegozio(String nome, String indirizzo, String tipologia, List<Personale> personale, List<Promozione> promozioni, List<String> categorie) {
        //aggiungere controllo se già esistente
        Negozio negozio = new Negozio(nome, indirizzo, tipologia, personale, promozioni, categorie);

        return negozi.contains(negozio);
    }

    /**
     * metodo di supporto per cercare un negozio tramite il nome nella lista dei negozi
     * @param nome del negozio da cercare
     * @return l'ogetto negozio
     */
    private Negozio getNegozio(String nome) {

        Negozio result = null;

        for (Negozio elem : negozi) {
            if (elem.getNome().equals(nome)) {
                result = elem;
            }
        }
        return result;
    }

}
