package it.unicam.cs.ids.c3project.view;

import it.unicam.cs.ids.c3project.negozio.GestoreNegozio;
import it.unicam.cs.ids.c3project.negozio.Promozione;
import it.unicam.cs.ids.c3project.negozio.Vetrina;
import it.unicam.cs.ids.c3project.personale.Personale;
import it.unicam.cs.ids.c3project.personale.Responsabile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RicercaNegozioController {

    //TODO RIMUOVERE E CORREGGERE "initElements()"


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



    //TODO RIMUOVERE
    GestoreNegozio gestore = GestoreNegozio.getInstance();
    Responsabile resp = new Responsabile("Enrico");
    List<Personale> pers = new ArrayList<>();
    List<Promozione> promo = new ArrayList<>();
    List<String> cat = new ArrayList<>();
    List<Promozione> promo2 = new ArrayList<>();
    List<String> cat2 = new ArrayList<>();


    public void visualizzaPromoButtonPushed() {
        Predicate<Vetrina> predicate = p -> p.getPromozioni().size() != 0;

        negoziListView.getItems().addAll(gestore.getvetrine().stream().filter(predicate).collect(Collectors.toList()));
    }

    public  void visualizzaCategorieButtonPushed() {
        initElements();
        List<String> categorie = new ArrayList<>();

        for (Vetrina elem : gestore.getvetrine()) {
            String tmp = elem.getNome() + ":" + gestore.getCategorie(elem.getNome());
            categorie.add(tmp);
        }

        categorieListView.getItems().addAll(categorie);
    }

    public void cercaNomeButtonPushed() {
        initElements();

        String nome = nomeNegozioTextField.getText();

        Predicate<Vetrina> predicate = p -> p.getNome().equals(nome);

        negoziListView.getItems().addAll(gestore.getvetrine().stream().filter(predicate).collect(Collectors.toList()));
    }


    //TODO CAMBIARE E FARE PER BENE
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
