package it.unicam.cs.ids.c3project.corriere;

import it.unicam.cs.ids.c3project.contenuto.Articolo;
import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.time.LocalTime;
import java.util.List;

public class GestoreSpedizioni {
    private List<Pacco> pacchiDaConsegnare;
    private List<Corriere> corrieriDisponibili;
    private static int ID=0;

    /**
     * Crea un nuovo Pacco
     * @return
     */
    public boolean creaPacco(int ID, int corriereID, LocalTime tempoDiArrivoStimato, TipologiaContenuto contenuto, List<Articolo> articoli, StatoPacco statoPacco) {
        Pacco pacco=new Pacco(generaID(),corriereID,tempoDiArrivoStimato,contenuto,articoli,statoPacco);
        pacchiDaConsegnare.add(pacco);
        return pacchiDaConsegnare.contains(pacco);
        //TODO ECCEZIONI
    }

    /**
     * Aggiorna lo stato di consegna del pacco individuandolo tramite ID
     * @param statoPacco
     * @return
     */
    public StatoPacco aggiornaStato(int ID,StatoPacco statoPacco){
        Pacco pacco=filtraPacchi(ID);
        pacco.setStatoPacco(statoPacco);
        return pacco.getStatoPacco();
    }


    public List<Pacco> getPacchiDaConsegnare() {
        return pacchiDaConsegnare;
    }

    public List<Corriere> getCorrieriDisponibili() {
        return corrieriDisponibili;
    }

    public void  contattaCorriere() {

    }

    /**
     * Genera un nuovo ID per il pacco da creare
     * @return
     */
    public int generaID(){
        return ID++;
    }

    public Pacco filtraPacchi(int ID){
        for (Pacco pacco :pacchiDaConsegnare) {
            if(pacco.getID()==ID){
                return pacco;
            }
        }
        throw new IllegalArgumentException("Non esiste alcun pacco con l'ID inserito");
    }

}
