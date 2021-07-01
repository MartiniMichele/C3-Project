package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.database.DatabaseConnection;
import it.unicam.cs.ids.c3project.corriere.GestoreSpedizioni;
import it.unicam.cs.ids.c3project.contenuto.StatoPacco;
import it.unicam.cs.ids.c3project.contenuto.TipologiaContenuto;
import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.personale.Commesso;
import it.unicam.cs.ids.c3project.personale.Responsabile;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.util.Collections;

public class HomeClienteController {

   private Connection con= DatabaseConnection.getConnection();
    private PreparedStatement pst=null;
   private ResultSet rs=null;
   private GestoreSpedizioni gestoreSpedizioni = GestoreSpedizioni.getInstance();
   private GestoreNegozio gestoreNegozio = GestoreNegozio.getInstance();

    public void ricercaNegozioButtonPushed(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/RicercaNegozio.fxml"));
        Parent ricercaNegozioParent = loader.load();
        Scene ricercaNegozioScene = new Scene(ricercaNegozioParent);

        RicercaNegozioController controller = loader.getController();
        controller.initElements();

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(ricercaNegozioScene);
        window.show();

        } catch (IOException e) {
            e.printStackTrace();
            launchError("errore nel caricamento del file");
        }
    }

    public void tracciaPaccoButtonPushed(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/TracciaPacchi.fxml"));
            Parent tracciaPacchiParent = loader.load();
            Scene tracciaPacchiScene = new Scene(tracciaPacchiParent);

            TracciaPacchiController controller = loader.getController();
            controller.initTableView();

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tracciaPacchiScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            launchError("errore nel caricamento del file");
        }
    }

    public void launchError(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE!");
        alert.setHeaderText("Qualcosa e' andato storto");
        alert.setContentText("ERRORE: " + str);
        alert.showAndWait();
    }

    public void populatePacchi(String cliente) {
        String query="SELECT * FROM Pacco, ContenutoPacco WHERE Cliente="+cliente+"";

        try{
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()){
                gestoreSpedizioni.creaPacco(
                        rs.getInt("ID"),
                        rs.getString("UsernameCorriere"),
                        rs.getString("IndirizzoDiConsegna"),
                        rs.getTime("TempoDiArrivoStimato").toLocalTime(),
                        TipologiaContenuto.valueOf(rs.getString("TipologiaContenuto")),
                        Collections.singletonList(rs.getString("Articolo")),
                        StatoPacco.valueOf(rs.getString("StatoConsegna")),
                        rs.getString("Cliente"));
            }
        }
        catch (Exception e){
            e.printStackTrace();

        }
        finally {
            try {
                rs.close();
                pst.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void populateNegozi() {
        //String query=   "SELECT * FROM Vetrina,Negozio,Commesso,Categorie";
     //"FUNZIONANTE" String query="SELECT * FROM(SELECT DISTINCT NomeNegozio,Indirizzo,Tipologia,Responsabile,UsernameCommesso,CategorieVendute,Contatto FROM Vetrina,Negozio,Commesso,CategorieVendute)";
     //   String query="SELECT DISTINCT Negozio,Indirizzo,Tipologia,Responsabile,UsernameCommesso,Contatto,CategorieVendute FROM Vetrina,Negozio,Commesso,CategorieVendute";
     //   String query="SELECT * FROM(SELECT DISTINCT NomeNegozio,Indirizzo,Tipologia,Responsabile,UsernameCommesso,Contatto FROM Vetrina,Negozio,Commesso)   CategorieVendute FROM CategorieVendute";
        String query = "SELECT * FROM CategorieVendute left join Vetrina V on V.NomeNegozio = CategorieVendute.Negozio  join Negozio N on V.NomeNegozio = N.Vetrina join Commesso C on N.Vetrina = C.NomeNegozioAssociato";
        try{
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while (rs.next()){
                gestoreNegozio.creaNegozio(
                        rs.getString("NomeNegozio"),
                        rs.getString("Indirizzo"),
                        rs.getString("Tipologia"),
                        new Responsabile(rs.getString("Responsabile")),
                        Collections.singletonList(new Commesso(rs.getString("UsernameCommesso"))),
                        rs.getString("Contatto"));
            }

        }
        catch (Exception e){
            e.printStackTrace();
            launchError("Si è verificato un errore nel popolamento dei negozi");
        }
        finally {
            try {
                rs.close();
                pst.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void populateCategorie() {
        String query="SELECT * FROM CategorieVendute";
        try {
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()){
                gestoreNegozio.aggiungiCategoria(
                        rs.getString("CategorieVendute"),
                        rs.getString("Negozio"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            launchError("Si è verificato un errore nel popolamento delle categorie");
        }
        finally {
            try {
                rs.close();
                pst.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void populatePromozioni() {
        String query="SELECT * from Promozioni";
        try {
            pst=con.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()) {
                gestoreNegozio.avviaPromozione(
                        rs.getString("NomePromozione"),
                        rs.getString("Negozio"),
                        rs.getInt("PuntiBonus"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                rs.close();
                pst.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
