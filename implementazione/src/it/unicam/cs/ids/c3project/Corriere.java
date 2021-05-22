package it.unicam.cs.ids.c3project;

import java.util.List;

public class Corriere {
    private int ID;
    private TipologiaCorriere tipologiaCorriere;
    private boolean stato;
    private List<Pacco> carico;
    private double tariffa;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TipologiaCorriere getTipologiaCorriere() {
        return tipologiaCorriere;
    }

    public void setTipologiaCorriere(TipologiaCorriere tipologiaCorriere) {
        this.tipologiaCorriere = tipologiaCorriere;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public List<Pacco> getCarico() {
        return carico;
    }

    public void setCarico(List<Pacco> carico) {
        this.carico = carico;
    }

    public double getTariffa() {
        return tariffa;
    }

    public void setTariffa(double tariffa) {
        this.tariffa = tariffa;
    }

    /**
     * Metodo usato per cambiare lo stato del corriere da online a offline o viceversa
     * @return
     */
    public boolean aggiornaStato(){
        return !this.stato;
    }
    /**
     * Metodo usato per aggiungere un pacco alla lista dei pacchi
     * @param pacco
     * @return
     */
    public boolean aggiungiPacco(Pacco pacco){
        return this.carico.add(pacco);
    }

    /**
     * Metodo usato per rimuovere un pacco dalla lista di pacchi
     * @param pacco
     * @return
     */
    public boolean rimuoviPacco(Pacco pacco){
       return this.carico.remove(pacco);
    }

    /**
     * Metodo usato per aggiornare la tariffa del Corriere
     * @param tariffa
     * @return
     */
    public double aggiornaTariffa(double tariffa){
        setTariffa(tariffa);
        return tariffa;
    }

    public void ricevereSpedizione(){
    }



}
