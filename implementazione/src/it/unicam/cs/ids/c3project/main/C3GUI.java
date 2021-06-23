package it.unicam.cs.ids.c3project.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class C3GUI extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        primaryStage.setTitle("C3 Project");
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
