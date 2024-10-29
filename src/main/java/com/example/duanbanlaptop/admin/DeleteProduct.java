package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteProduct {
    @FXML

    public void deleteProduct(int productId) throws SQLException {

        Connect conn = new Connect();
        Connection connection = conn.connect();
        String query = "DELETE FROM products WHERE idProduct = ?";
        PreparedStatement delete = connection.prepareStatement(query);
        delete.setInt(1, productId);
        int rows = delete.executeUpdate();
        if (rows != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Delete successful ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("PRODUCT NOT FOUND ");
            alert.showAndWait();

        }

        delete.close();
        connection.close();
    }

}
