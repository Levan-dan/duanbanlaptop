package com.example.duanbanlaptop.admin;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AdminFunction {
    @FXML
    private VBox content;
    @FXML
    private Label title;

    public void product() throws IOException {
        title.setText("Product Management");
        loadSubScene("/com/example/duanbanlaptop/view/manageProductUI.fxml");

    }

    public void order() {
        title.setText("Order management");

    }

    public void bill() {
        title.setText("Bill Management");

    }

    public void customer() {
        title.setText("Customer Management");
    }




    public void loadSubScene(String fxmlFile) {
        try {
            content.getChildren().clear();
            FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource(fxmlFile));
            Parent parent = fxmlLoader.load();
           content.getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backHome() {
        title.setText("Hello Admin");
        content.getChildren().clear();
    }
}
