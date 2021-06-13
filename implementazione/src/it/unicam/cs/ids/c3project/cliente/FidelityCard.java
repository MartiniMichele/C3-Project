package it.unicam.cs.ids.c3project.cliente;

public class FidelityCard {
    private String numeroCarta;
    private int puntiAccreditati;
    private String nomeCliente;

    public String getNumeroCarta() {
        return numeroCarta;
    }

    public void setNumeroCarta(String numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    public int getPunti() {
        return puntiAccreditati;
    }

    public void setPunti(int punti) {
        this.puntiAccreditati = punti;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public void addPunti(int punti){
        this.puntiAccreditati=this.puntiAccreditati+punti;
    }
    public void removePunti(int punti){
        this.puntiAccreditati=this.puntiAccreditati-punti;
    }
}
