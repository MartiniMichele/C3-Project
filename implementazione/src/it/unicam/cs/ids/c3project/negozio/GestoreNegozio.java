package it.unicam.cs.ids.c3project.negozio;

import it.unicam.cs.ids.c3project.personale.Personale;
import it.unicam.cs.ids.c3project.personale.Responsabile;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * classe gestore che ha il compito di aggiungere e gestire i negozi
 */
public class GestoreNegozio{

    private List<Negozio> negozi = new ArrayList<>();       //lista dei negozi aggiunti
    private List<Vetrina> vetrine = new ArrayList<>();
    private static GestoreNegozio istanzaGestore;


    private GestoreNegozio() {}

    public static GestoreNegozio getInstance() {
        if (istanzaGestore == null) istanzaGestore = new GestoreNegozio();
        return istanzaGestore;
    }

    /**
     * metodo che ritorna la lista dei negozi
     * @return la lista dei negozi
     */
    public List<Negozio> getNegozi() {
        return negozi;
    }

    public List<Vetrina> getVetrine() {
        return vetrine;
    }

    /**
     * metodo che aggiunge una categoria, torna true se viene aggiunta e false altrimenti
     * @param categoria da aggiungere
     * @param nomeNegozio associato
     * @return true se viene aggiunta e false altrimenti
     */
    public boolean aggiungiCategoria(String categoria, String nomeNegozio) {
        Predicate<Vetrina> predicate = p -> p.getNome().equals(nomeNegozio);
        Vetrina istanzaNegozio = searchVetrina(predicate).get(0);
        return istanzaNegozio.addCategoria(categoria);
    }

    /**
     * metodo che rimuove una categoria e torna true se viene rimosso e false altrimenti
     * @param nomeNegozio associato alla categoria
     * @return true se rimosso false altrimenti
     */
    public boolean rimuoviCategoria(String categoria,String nomeNegozio) {
        Predicate<Vetrina> predicate = p -> p.getNome().equals(nomeNegozio);
        Vetrina istanzaNegozio = searchVetrina(predicate).get(0);
        return istanzaNegozio.removeCategoria(categoria);
    }

    /**
     * Metodo utilizzato per ritornare tutte le categorie associate a un nome di un negozio
     * @param vetrina il nome del negozio
     * @return la lista delle categorie esistenti in un certo negozio
     */
    public List<String> getCategorie(String vetrina) {
        Predicate<Vetrina> predicate = p -> p.getNome().equals(vetrina);
        Vetrina istanzaNegozio = searchVetrina(predicate).get(0);
        return istanzaNegozio.getCategoriaProdotti();
    }

    /**
     * Metodo utilizzato per ritornare tutte le categorie esistenti
     * @return la lista delle categorie esistenti
     */
    public List<String> getAllCategorie() {
        List<String> categorie = new ArrayList<>();
        for (Vetrina vetrina : vetrine) {
            for (String categoria : vetrina.getCategoriaProdotti()){
                if(!categorie.contains(categoria))
                    categorie.add(categoria);
            }
        }
        return categorie;
    }

    public List<Promozione> getAllPromozioni() {
        List<Promozione> promozioni = new ArrayList<>();
        for (Vetrina vetrina : vetrine) {
            promozioni.addAll(vetrina.getPromozioni());
        }
        return promozioni;
    }

    public List<Promozione> getPromozioni(String vetrina) {
        Predicate<Vetrina> predicate = p -> p.getNome().equals(vetrina);
        Vetrina istanzaNegozio = searchVetrina(predicate).get(0);
        return istanzaNegozio.getPromozioni();
    }

    public boolean avviaPromozione(String nomePromozione, String negozio, int puntiBonus) {

       Promozione promozione = new Promozione(nomePromozione, negozio, puntiBonus);
       Predicate<Vetrina> predicate = p -> p.getNome().equals(negozio);
       Vetrina istanzaVetrina = searchVetrina(predicate).get(0);
       istanzaVetrina.addPromozione(promozione);
       return istanzaVetrina.contienePromozione(promozione);

    }

    public  boolean rimuoviPromozione(Promozione promozione) {

        Predicate<Vetrina> predicate = p -> p.getNome().equals(promozione.getNegozio());

        Vetrina vetrina = searchVetrina(predicate).get(0);
        vetrina.rimuoviPromozione(promozione);

        return vetrina.contienePromozione(promozione);
    }



    public boolean creaNegozio(String nome, String indirizzo, String tipologia, Responsabile responsabile, List<Personale> personale, String contatto) {

        boolean flag = negozioEsistente(nome);
        if (!flag) {
            Negozio negozio = new Negozio(responsabile, personale);
            negozio.creaVetrina(nome, tipologia, indirizzo, contatto);
            negozi.add(negozio);
            vetrine.add(negozio.getVetrina());
        }

        return flag;
    }




    /**
     * metodo di supporto per cercare un negozio tramite il nome nella lista dei negozi
     * @param predicate per filtrare i negozi
     * @return l'ogetto negozio
     */
    public List<Negozio> searchNegozio(Predicate<Negozio> predicate) {

        List<Negozio> listaNegozi = new ArrayList<>();
        listaNegozi = negozi;
        listaNegozi = listaNegozi.stream().filter(predicate).collect(Collectors.toList());
        return listaNegozi;
    }

    public List<Vetrina> searchVetrina(Predicate<Vetrina> predicate){
        List<Vetrina> listaVetrine = new ArrayList<>();
        listaVetrine = vetrine;
        listaVetrine=listaVetrine.stream().filter(predicate).collect(Collectors.toList());
        return listaVetrine;
    }

    private boolean negozioEsistente(String negozio) {
        boolean result = false;
        for (Vetrina elemento : vetrine) {
            if (elemento.getNome().equals(negozio)) {
                result = true;
                break;
            }
        }
        return  result;
    }


}
