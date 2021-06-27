package it.unicam.cs.ids.c3project.corriere;

import it.unicam.cs.ids.c3project.contenuto.Pacco;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GestoreSpedizioni {
    private List<Pacco> pacchiDaConsegnare=new ArrayList<>();
    private List<Corriere> listaCorrieri=new ArrayList<>();
    private static int ID=0;
    private static GestoreSpedizioni istanzaGestoreSpedizione;


    private GestoreSpedizioni() {}

    public static GestoreSpedizioni getInstance() {
        if (istanzaGestoreSpedizione == null) istanzaGestoreSpedizione = new GestoreSpedizioni();

        return istanzaGestoreSpedizione;
    }


    /**
     * Crea un nuovo Pacco
     * @return
     */
    public boolean creaPacco(int ID, String usernameCorriere,String indirizzo, LocalTime tempoDiArrivoStimato, TipologiaContenuto contenuto, List<String> articoli, StatoPacco statoPacco,String cliente)
    {
        Pacco pacco=new Pacco(ID,usernameCorriere, indirizzo,tempoDiArrivoStimato,contenuto,articoli,statoPacco, cliente);
        pacchiDaConsegnare.add(pacco);
        return pacchiDaConsegnare.contains(pacco);
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

    /**
     * Metodo che ritorna una lista di Corrieri disponibili
     * @return lista di corrieri disponibili
     */
    public List<Corriere> getCorrieriDisponibili() {
        List<Corriere> corrieriDisponibili=new ArrayList<>();
        for (Corriere corriere:listaCorrieri) {
            if (corriere.getStato())
                corrieriDisponibili.add(corriere);
        }
        return corrieriDisponibili;
    }



    public int  contattaCorriere(String indirizzo, TipologiaContenuto contenuto, Corriere corriere, boolean flag) {
        if(flag) {
            corriere.aggiornaStato();
            return corriere.getID();
        }
        return 0;
    }
    public void comunicaDati(int idCorrereScelto, String indirizzo, int idPacco, TipologiaContenuto tipologiaContenuto ) {
        for (Corriere corriere:listaCorrieri) {
            if(corriere.getID()==idCorrereScelto){
                for (Pacco pacco:pacchiDaConsegnare) {
                    if(pacco.getID()==idPacco)
                        corriere.aggiungiPacco(pacco);
                }
            }
        }
    }

    /**
     * Genera un nuovo ID per il pacco da creare
     * @return
     */
    public int generaID(){
        return ID++;
    }

    public List<Pacco> filtraPacchi(Predicate<Pacco> paccoPredicate){
        List<Pacco> paccoList = pacchiDaConsegnare;
        paccoList=paccoList.stream().filter(paccoPredicate).collect(Collectors.toList());
        if(paccoList.isEmpty())
            throw new IllegalArgumentException("Non esiste alcun pacco con il criterio inserito");
        return paccoList;
    }


}