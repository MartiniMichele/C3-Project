package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.autenticazione.DatabaseConnection;
import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.negozio.Promozione;
import it.unicam.cs.ids.c3project.negozio.Vetrina;
import it.unicam.cs.ids.c3project.personale.Commesso;
import it.unicam.cs.ids.c3project.personale.Personale;
import it.unicam.cs.ids.c3project.personale.Responsabile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class GestioneVetrinaController {

    private Connection con= DatabaseConnection.ConnectionToDB();
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    GestoreNegozio gestore = GestoreNegozio.getInstance();
    String negozio;

    @FXML
    ListView<String> categorieListView;

    @FXML
    ListView<Promozione> promozioniListView;

    @FXML
    TextField nuovaCategoriaTextField;

    @FXML
    TextField negozioTextField;

    @FXML
    TextField nomePromozioneTextField;

    @FXML
    TextField negozioPromozioneTextField;

    @FXML
    TextField puntiPromozioneTextField;

    @FXML
    Button nuovaCategoriaButton;

    @FXML
    Button rimuoviCategoriaButton;

    @FXML
    Button avviaPromozioneButton;

    @FXML
    Button rimuoviPromozioneButton;


    public void nuovaCategoriaButtonPushed() {

        String categoria = nuovaCategoriaTextField.getText();
        String negozio = negozioTextField.getText();

        if (!categoria.isBlank() && !negozio.isBlank()) {
            gestore.aggiungiCategoria(categoria, negozio);
            updateListView(negozio);
            launchMessage("categoria aggiunta");
        }

        else launchError("uno dei campi è vuoto");
    }

    public void rimuoviCategoriaButtonPushed() {

        String categoria = categorieListView.getSelectionModel().getSelectedItem();

        Predicate<Vetrina> predicate = p -> p.getCategoriaProdotti().contains(categoria);


        gestore.rimuoviCategoria(categoria, gestore.searchVetrina(predicate).get(0).getNome());
        updateListView(negozio);
    }

    public void homeButtonPushed(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/HomeResponsabile.fxml"));
            Parent homeResponsabileParent = loader.load();
            Scene homeResponsabileScene = new Scene(homeResponsabileParent);

            HomeResponsabileController controller = loader.getController();

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(homeResponsabileScene);
            window.show();

        } catch (IOException e) {
            e.printStackTrace();
            launchError("errore nel caricamento del file");
        }
    }

    public void avviaPromozioneButtonPushed(){

        String nome = nomePromozioneTextField.getText();
        String negozio = negozioPromozioneTextField.getText();
        String puntiStr = puntiPromozioneTextField.getText();

        if (!nome.isBlank() && !negozio.isBlank() && !puntiStr.isBlank()) {
            int punti = 0;
            try {
                punti = Integer.parseInt(puntiStr);
            }
            catch (NumberFormatException e) {
                launchError("si è verificato un errore, riprovare");
            }
            gestore.avviaPromozione(nome, negozio, punti);
            updateListView(negozio);
            launchMessage("promozione aggiunta");
        }
        else launchError("uno dei campi è vuoto");

    }

    public void rimuoviPromozioneButtonPushed() {

        Promozione promo = promozioniListView.getSelectionModel().getSelectedItem();

        gestore.rimuoviPromozione(promo);
        updateListView(negozio);
        launchMessage("promozione rimossa");
    }

    public void launchError(String str) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRORE!");
        alert.setHeaderText("Qualcosa e' andato storto");
        alert.setContentText("ERRORE: " + str);
        alert.showAndWait();
    }

    public void launchMessage(String str) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GESTIONE VETRINA MESSAGE");
        alert.setHeaderText(str + "!");
        alert.showAndWait();
    }

    public void populate(String nome) {
        negozio = nome;
        populateNegozi(nome);
        populateCategorie(nome);
        populatePromozioni(nome);
        updateListView(nome);
    }

    private void updateListView(String negozio) {
        categorieListView.getItems().clear();
        categorieListView.getItems().addAll(gestore.getCategorie(negozio));

        promozioniListView.getItems().clear();
        promozioniListView.getItems().addAll(gestore.getPromozioni(negozio));

    }

    private void populateNegozi(String negozio) {

        String query = "SELECT * FROM Negozio JOIN Vetrina ON Negozio.Vetrina = Vetrina.NomeNegozio JOIN Commesso C ON Negozio.Vetrina = C.NomeNegozioAssociato where Vetrina.NomeNegozio like ?";


        try {
            pst = con.prepareStatement(query);
            pst.setString(1, negozio);
            rs = pst.executeQuery();

            while (rs.next()) {
                gestore.creaNegozio(
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
    }

    private void populateCategorie(String negozio) {
        //String query="SELECT * FROM CategorieVendute join Negozio ON Vetrina=Negozio WHERE Responsabile="+responsabile+"";
        String query="SELECT * from CategorieVendute where Negozio like ?";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, negozio);
            rs = pst.executeQuery();

            while (rs.next()) {
                gestore.aggiungiCategoria(
                        rs.getString("CategorieVendute"),
                        rs.getString("Negozio"));

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void populatePromozioni(String negozio) {
        //String query = "SELECT * from Promozioni join Negozio on Vetrina=Promozioni.Negozio where Responsabile="+responsabile+"";
        String query="SELECT * from Promozioni where Negozio like ?";
        try {
            pst=con.prepareStatement(query);
            pst.setString(1, negozio);
            rs=pst.executeQuery();
            while(rs.next()) {
                gestore.avviaPromozione(
                        rs.getString("NomePromozione"),
                        rs.getString("Negozio"),
                        rs.getInt("PuntiBonus"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }





}
