package com.example.duanbanlaptop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("view/homeUser.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }


    public static  void main(String[] args) throws SQLException {
    launch(args);

    }


}