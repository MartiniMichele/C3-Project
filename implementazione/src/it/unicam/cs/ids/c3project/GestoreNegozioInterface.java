package it.unicam.cs.ids.c3project;

import java.util.List;

public interface GestoreNegozioInterface {

    boolean aggiungiCategoria(String categoria, String negozio);
    boolean rimuoviCategoria(String categoria, String negozio);
    List<String> getCategorie(String negozio);
    boolean avviaPromozione(String nome, String negozio, int puntiBonus);
    boolean addNegozio(String nome, String indirizzo, String tipologia, List<Personale> personale, List<Promozione> promozioni, List<String> categorie);

}
