package it.unicam.cs.ids.c3project.contenuto;

import it.unicam.cs.ids.c3project.corriere.StatoPacco;
import it.unicam.cs.ids.c3project.corriere.TipologiaContenuto;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Pacco {
    private int ID;
    private String usernameCorriere;
    private String indirizzoDiConsegna;
    private LocalTime tempoDiArrivoStimato;
    private TipologiaContenuto contenuto;
    private List<String> articoli;
    private StatoPacco statoPacco;
    private String cliente;

    public Pacco(int ID, String usernameCorriere,String indirizzoDiConsegna, LocalTime tempoDiArrivoStimato, TipologiaContenuto contenuto, List<String> articoli, StatoPacco statoPacco, String cliente) {
        this.ID = ID;
        this.usernameCorriere = usernameCorriere;
        this.indirizzoDiConsegna=indirizzoDiConsegna;
        this.tempoDiArrivoStimato = tempoDiArrivoStimato;
        this.contenuto = contenuto;
        this.articoli = articoli;
        this.statoPacco = statoPacco;
        this.cliente = cliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getusernameCorriere() {
        return usernameCorriere;
    }

    public void setusernameCorriere(String usernameCorriere) {
        this.usernameCorriere = usernameCorriere;
    }

    public String getIndirizzoDiConsegna() {
        return indirizzoDiConsegna;
    }

    public void setIndirizzoDiConsegna(String indirizzoDiConsegna) {
        this.indirizzoDiConsegna = indirizzoDiConsegna;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public LocalTime getTempoDiArrivoStimato() {
        return tempoDiArrivoStimato;
    }

    public void setTempoDiArrivoStimato(LocalTime tempoDiArrivoStimato) {
        this.tempoDiArrivoStimato = tempoDiArrivoStimato;
    }

    public TipologiaContenuto getContenuto() {
        return contenuto;
    }

    public void setContenuto(TipologiaContenuto contenuto) {
        this.contenuto = contenuto;
    }

    public List<String> getArticoli() {
        return articoli;
    }

    public StatoPacco getStatoPacco() {
        return statoPacco;
    }

    public void setStatoPacco(StatoPacco statoPacco) {
        this.statoPacco = statoPacco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacco pacco = (Pacco) o;
        return getID() == pacco.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }

    @Override
    public String toString() {
        return "Pacco{" +
                "ID=" + ID +
                ", usernameCorriere=" + usernameCorriere +
                ", tempoDiArrivoStimato=" + tempoDiArrivoStimato +
                ", statoPacco=" + statoPacco +
                '}';
    }
}
