package com.example.duanbanlaptop;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("view/login.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("login");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) throws SQLException {
       launch(args);
    }
}