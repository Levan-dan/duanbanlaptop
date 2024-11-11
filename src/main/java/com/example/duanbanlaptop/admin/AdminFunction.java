package com.example.duanbanlaptop.admin;


import com.example.duanbanlaptop.function.LoginFunction;
import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

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



    public void logOut() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Thực hiện chuyển cảnh
            TransitionFunction transitionFunction = new TransitionFunction();
            transitionFunction.transition("view/login.fxml");
            LoginUser.logout();

        }

    }
}
