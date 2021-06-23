package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.negozio.Promozione;
import it.unicam.cs.ids.c3project.negozio.Vetrina;
import it.unicam.cs.ids.c3project.personale.Personale;
import it.unicam.cs.ids.c3project.personale.Responsabile;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class GestioneVetrinaController {

    //TODO CORREGGERE INIT ELEMENTS

    GestoreNegozio gestore = GestoreNegozio.getInstance();

    Responsabile resp = new Responsabile("Enrico");
    List<Personale> pers = new ArrayList<>();
    List<Promozione> promo = new ArrayList<>();
    List<String> cat = new ArrayList<>();
    List<Promozione> promo2 = new ArrayList<>();
    List<String> cat2 = new ArrayList<>();

    @FXML
    ListView<String> categorieListView;

    @FXML
    TextField nuovaCategoriaTextField;

    @FXML
    TextField negozioTextField;

    @FXML
    Button nuovaCategoriaButton;

    @FXML
    Button rimuoviCategoriaButton;


    public void nuovaCategoriaButtonPushed() {
        initElements();

        String categoria = nuovaCategoriaTextField.getText();
        String negozio = negozioTextField.getText();

        if (!nuovaCategoriaTextField.getText().isBlank() && !negozioTextField.getText().isBlank()) {
            gestore.aggiungiCategoria(categoria, negozio);
            launchMessage("categoria aggiunta");
        }

        else launchError("uno dei campi è vuoto");
    }

    public void rimuoviCategoriaButtonPushed() {

        String categoria = categorieListView.getSelectionModel().getSelectedItems().get(0);

        Predicate<Vetrina> predicate = p -> p.getCategoriaProdotti().contains(categoria);


        gestore.rimuoviCategoria(categoria, gestore.searchVetrina(predicate).get(0).getNome());
        updateListView();
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

    public void populate() {
        initElements();
        updateListView();
    }

    private void updateListView() {
        categorieListView.getItems().clear();
        categorieListView.getItems().addAll(gestore.getAllCategorie());
    }

    private void initElements() {
        pers.add(resp);
        cat.add("pizza");
        cat.add("pane");

        cat2.add("chiodi");
        cat2.add("martello");

        gestore.creaNegozio("Pizzeria Gigi", "via Cassettoni", "Pizzeria", resp, pers, cat, "333666555888");
        gestore.creaNegozio("Ferramenta Franco", "via Giuseppe", "Ferramenta", resp, pers, cat2, "333666555888");
        gestore.avviaPromozione("3x2", "Ferramenta Franco", 30);
    }




}
