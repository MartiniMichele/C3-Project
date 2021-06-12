package it.unicam.cs.ids.c3project.negozio;

import it.unicam.cs.ids.c3project.cliente.Personale;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * classe gestore che ha il compito di aggiungere e gestire i negozi
 */
public class GestoreNegozio implements GestoreNegozioInterface {

    private static List<Negozio> negozi = new ArrayList<>();       //lista dei negozi aggiunti


    /**
     * metodo che ritorna la lista dei negozi
     * @return la lista dei negozi
     */
    public List<Negozio> getNegozi() {
        return negozi;
    }

    /**
     * metodo che aggiunge una categoria, torna true se viene aggiunta e false altrimenti
     * @param categoria da aggiungere
     * @param negozio associato
     * @return true se viene aggiunta e false altrimenti
     */
    @Override
    public boolean aggiungiCategoria(String categoria, String negozio) {

        Predicate<Negozio> predicate = p -> p.getNome().equals(negozio);
        Negozio istanzaNegozio = searchNegozio(predicate).get(0);
        istanzaNegozio.addCategoria(categoria);

        return istanzaNegozio.contieneCategoria(categoria);
    }

    /**
     * metodo che rimuove una categoria e torna true se viene rimosso e false altrimenti
     * @param categoria da aggiungere
     * @param negozio associato alla categoria
     * @return true se rimosso false altrimenti
     */
    @Override
    public boolean rimuoviCategoria(String categoria, String negozio) {

        Predicate<Negozio> predicate = p -> p.getNome().equals(negozio);
        Negozio istanzaNegozio = searchNegozio(predicate).get(0);
        negozi.remove(istanzaNegozio);

        return !negozi.contains(istanzaNegozio);
    }

    //TODO RIMUOVERE
    @Override
    public List<String> getCategorie(String negozio) {
        //aggiungere controlli
        Predicate<Negozio> predicate = p -> p.getNome().equals(negozio);
        Negozio istanzaNegozio = searchNegozio(predicate).get(0);

        return istanzaNegozio.getcategorie();
    }

    @Override
    public boolean avviaPromozione(String nome, String negozio, int puntiBonus) {

        Promozione promozione = new Promozione(nome, negozio, puntiBonus);
        Predicate<Negozio> predicate = p -> p.getNome().equals(negozio);
        Negozio istanzaNegozio = searchNegozio(predicate).get(0);
        istanzaNegozio.addPromozione(promozione);

        return istanzaNegozio.contienePromozione(promozione);
    }

    @Override
    public boolean creaNegozio(String nome, String indirizzo, String tipologia, List<Personale> personale, List<Promozione> promozioni, List<String> categorie) {
        //aggiungere controllo se già esistente

        boolean flag = negozioEsistente(nome);

        if (!flag) {
            Negozio negozio = new Negozio(nome, indirizzo, tipologia, personale, promozioni, categorie);
            negozi.add(negozio);
        }
        else throw new IllegalArgumentException("negozio già presente");


        return !flag;
    }

    /**
     * metodo di supporto per cercare un negozio tramite il nome nella lista dei negozi
     * @param predicate per filtrare i negozi
     * @return l'ogetto negozio
     */
    public List<Negozio> searchNegozio(Predicate<Negozio> predicate) {

        List<Negozio> lista = new ArrayList<>();

        lista = negozi;
        lista = lista.stream().filter(predicate).collect(Collectors.toList());

        return lista;
    }

    private boolean negozioEsistente(String negozio) {

        boolean result = false;

        for (Negozio elemento : negozi) {

            if (elemento.getNome().equals(negozio)) {
                result = true;
                break;
            }
        }
        return  result;
    }

}
