package it.unicam.cs.ids.c3project.negozio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vetrina {
    private String nome;
    private String indirizzo;
    private List<String> categoriaProdotti;
    private String tipologia;
    private List<Promozione> promozioni;
    private String contatto;

    public Vetrina(String nome,String indirizzo, String tipologia, String contatto, List<String> categoriaProdotti){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
        this.contatto = contatto;
        this.categoriaProdotti = categoriaProdotti;
        this.promozioni = new ArrayList<>();

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

    public boolean addCategoria(String categoria){
        if(categoriaProdotti.contains(categoria))
            return false;
        this.categoriaProdotti.add(categoria);
        return true;
    }

    public void addPromozione(Promozione promozione) {
        if (!promozioneDuplicata(promozione.getID()))
            this.promozioni.add(promozione);
        else throw new IllegalArgumentException();
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
    public boolean contienePromozione(Promozione promozione) {
        return promozioni.contains(promozione);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vetrina vetrina = (Vetrina) o;
        return nome.equals(vetrina.nome) &&
                indirizzo.equals(vetrina.indirizzo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, indirizzo);
    }


    @Override
    public String toString() {
        return  "nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", contatto='" + contatto + '\'';
    }
}
