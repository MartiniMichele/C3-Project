package it.unicam.cs.ids.c3project.corriere;

import it.unicam.cs.ids.c3project.contenuto.Articolo;
import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GestoreSpedizioni {
    private List<Pacco> pacchiDaConsegnare;
    private List<Corriere> corrieriDisponibili;
    private static int ID=0;

    /**
     * Crea un nuovo Pacco
     * @return
     */
    public boolean creaPacco(int ID, int corriereID,String indirizzo, LocalTime tempoDiArrivoStimato, TipologiaContenuto contenuto, List<Articolo> articoli, StatoPacco statoPacco,String cliente) {
        Pacco pacco=new Pacco(generaID(),corriereID, indirizzo,tempoDiArrivoStimato,contenuto,articoli,statoPacco, cliente);
        pacchiDaConsegnare.add(pacco);
        return pacchiDaConsegnare.contains(pacco);
        //TODO ECCEZIONI
    }

    /**
     * Aggiorna lo stato di consegna del pacco individuandolo tramite ID(il corriere lo inserisce manualmente, nell'ipotesi di utilizzare uno scanner)
     * @param statoPacco
     * @return
     */
    public StatoPacco aggiornaStato(int ID,StatoPacco statoPacco){
        Predicate<Pacco> predicate=p->p.getID()==ID;
        List<Pacco> pacchi=filtraPacchi(predicate);
        pacchi.get(0).setStatoPacco(statoPacco);
        return  pacchi.get(0).getStatoPacco();
    }


    public List<Pacco> getPacchiDaConsegnare() {
        return pacchiDaConsegnare;
    }

    public List<Corriere> getCorrieriDisponibili() {
        return corrieriDisponibili;
    }
    public Corriere sceltaCorriere(Corriere corriereScelto){
        return null;
    }
    public void  richiediSpedizione() {
    }

    /**
     * Genera un nuovo ID per il pacco da creare
     * @return
     */
    public int generaID(){
        return ID++;
    }

    public List<Pacco> filtraPacchi(Predicate<Pacco> paccoPredicate){
        List<Pacco> paccoList=pacchiDaConsegnare;
        paccoList=paccoList.stream().filter(paccoPredicate).collect(Collectors.toList());
        if(paccoList.isEmpty())
            throw new IllegalArgumentException("Non esiste alcun pacco con il criterio inserito");
        return paccoList;
    }

}