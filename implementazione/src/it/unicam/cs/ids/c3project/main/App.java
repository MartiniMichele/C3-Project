package it.unicam.cs.ids.c3project.main;

import javafx.application.Application;

public class App {

    public static void main(String[] args) {
        launchGui();
    }

    private static void launchGui() {
        Application.launch(C3GUI.class);
    }
}
