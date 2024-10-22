package com.example.duanbanlaptop.function;

import com.example.duanbanlaptop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFunction {

    @FXML
    public void transitionToSignup() throws IOException {

        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.transition("view/signup.fxml");
    }
}
