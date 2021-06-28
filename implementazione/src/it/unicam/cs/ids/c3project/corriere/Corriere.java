package it.unicam.cs.ids.c3project.corriere;

import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.util.List;

public class Corriere {
    private String usernameCorriere;
    private TipologiaCorriere tipologiaCorriere;
    private boolean stato;
    private double tariffa;

    public Corriere(String usernameCorriere, TipologiaCorriere tipologiaCorriere, double tariffa) {
        this.usernameCorriere = usernameCorriere;
        this.tipologiaCorriere = tipologiaCorriere;
        this.stato = false;
        this.tariffa = tariffa;
    }

    public String getUsernameCorriere() {
        return usernameCorriere;
    }

    public void setUsernameCorriere(String usernameCorriere) {
        this.usernameCorriere = usernameCorriere;
    }

    public TipologiaCorriere getTipologiaCorriere() {
        return tipologiaCorriere;
    }

    public void setTipologiaCorriere(TipologiaCorriere tipologiaCorriere) {
        this.tipologiaCorriere = tipologiaCorriere;
    }

    public boolean getStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
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

}
