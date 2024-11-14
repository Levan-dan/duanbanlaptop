package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class CartController {
    public void backHome() throws IOException {
        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.transition("view/homeUser.fxml");
    }
}
