package it.unicam.cs.ids.c3project.contenuto;

import it.unicam.cs.ids.c3project.corriere.StatoPacco;
import it.unicam.cs.ids.c3project.corriere.TipologiaContenuto;

import java.time.LocalTime;
import java.util.List;

public class Pacco {
    private int ID;
    private int corriereID;
    private LocalTime orarioPartenza;
    private LocalTime tempoDiArrivoStimato;
    private TipologiaContenuto contenuto;
    private List<Articolo> articoli;
    private StatoPacco statoPacco;
    private String cliente;

    public Pacco(int ID, int corriereID, LocalTime tempoDiArrivoStimato, TipologiaContenuto contenuto, List<Articolo> articoli, StatoPacco statoPacco, String cliente) {
        this.ID=ID;
        this.corriereID = corriereID;
        this.tempoDiArrivoStimato = tempoDiArrivoStimato;
        this.contenuto = contenuto;
        this.articoli = articoli;
        this.statoPacco = statoPacco;
        this.cliente=cliente;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCorriereID() {
        return corriereID;
    }

    public void setCorriereID(int corriereID) {
        this.corriereID = corriereID;
    }

    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
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

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public StatoPacco getStatoPacco() {
        return statoPacco;
    }

    public void setStatoPacco(StatoPacco statoPacco) {
        this.statoPacco = statoPacco;
    }
}
