package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.negozio.Promozione;
import it.unicam.cs.ids.c3project.negozio.Vetrina;
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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RicercaNegozioController {

    @FXML
    ListView<Vetrina> negoziListView;

    @FXML
    ListView<String> categorieListView;

    @FXML
    Button visualizzaPromo;

    @FXML
    Button visualizzaCategorie;

    @FXML
    Button cercaNomeButton;

    @FXML
    TextField nomeNegozioTextField;

    GestoreNegozio gestore = GestoreNegozio.getInstance();


    public void visualizzaPromoButtonPushed() {
        Predicate<Vetrina> predicate = p -> p.getPromozioni().size() != 0;

        negoziListView.getItems().clear();
        negoziListView.getItems().addAll(gestore.getvetrine().stream().filter(predicate).collect(Collectors.toList()));
    }

    public  void visualizzaCategorieButtonPushed() {
        List<String> categorie = new ArrayList<>();

        for (Vetrina elem : gestore.getvetrine()) {
            String tmp = elem.getNome() + ":" + gestore.getCategorie(elem.getNome());
            categorie.add(tmp);
        }

        categorieListView.getItems().clear();
        categorieListView.getItems().addAll(categorie);
    }

    public void cercaNomeButtonPushed() {

        String nome = nomeNegozioTextField.getText();

        Predicate<Vetrina> predicate = p -> p.getNome().equals(nome);

        negoziListView.getItems().clear();
        negoziListView.getItems().addAll(gestore.getvetrine().stream().filter(predicate).collect(Collectors.toList()));
    }

    public void homeButtonPushed(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/HomeCliente.fxml"));
            Parent homeClienteParent = loader.load();
            Scene homeClienteScene = new Scene(homeClienteParent);

            HomeClienteController controller = loader.getController();

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(homeClienteScene);
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


    public void initElements() {
        negoziListView.getItems().addAll(gestore.getvetrine());
    }
}
