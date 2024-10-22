package com.example.duanbanlaptop.function;

import com.example.duanbanlaptop.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TransitionFunction {
//    public static void transition(ActionEvent actionEvent, String file) throws IOException {
//        Parent root2 = FXMLLoader.load(getClass().getResource(file));
//        Stage win  = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root2);
//        win.setScene(scene);
//        win.show();
//    }

    public void transition( String file) throws IOException {
        Parent root2 = FXMLLoader.load(Main.class.getResource(file));
        Scene scene = new Scene(root2);
        Main.stage.setScene(scene);
        Main.stage.show();
    }
}
