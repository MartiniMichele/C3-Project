module C3.Project {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires sqlite.jdbc;

    opens it.unicam.cs.ids.c3project.view;
    opens it.unicam.cs.ids.c3project.main;
    opens it.unicam.cs.ids.c3project.contenuto;
}