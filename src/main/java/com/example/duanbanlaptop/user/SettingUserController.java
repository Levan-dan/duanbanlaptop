package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.admin.LoginUser;
import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class SettingUserController {

    @FXML
    private Label title;
    public void userLogOut() throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure you want to log out?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Thực hiện chuyển cảnh
            TransitionFunction transitionFunction = new TransitionFunction();
            transitionFunction.transition("view/login.fxml");
            LoginUser.logout();

            Stage stage = (Stage) title.getScene().getWindow();
            stage.close();




        }
    }

    public void purchaseUser() {
        title.setText("Purchased orders");
    }

    public void orderUser() {
        title.setText("Order");
    }
}
