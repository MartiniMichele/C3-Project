package it.unicam.cs.ids.c3project.negozio;

import java.util.ArrayList;
import java.util.List;

public class Vetrina {
    private String nome;
    //Indirizzo da togliere
    private String indirizzo;
    private List<String> categoriaProdotti;
    private String tipologia;
    private List<Promozione> promozioni;
    private String contatto;

    public Vetrina(String nome,String indirizzo, String tipologia, String contatto){
        this.nome=nome;
        this.indirizzo=indirizzo;
        this.tipologia=tipologia;
        this.contatto=contatto;
        this.categoriaProdotti=new ArrayList<>();
        this.promozioni=new ArrayList<>();

    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public List<String> getCategoriaProdotti() {
        return categoriaProdotti;
    }

    public void setCategoriaProdotti(List<String> categoriaProdotti) {
        this.categoriaProdotti = categoriaProdotti;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public List<Promozione> getPromozioni() {
        return promozioni;
    }

    public void setPromozioni(List<Promozione> promozioni) {
        this.promozioni = promozioni;
    }

    public String getContatto() {
        return contatto;
    }

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }
}
