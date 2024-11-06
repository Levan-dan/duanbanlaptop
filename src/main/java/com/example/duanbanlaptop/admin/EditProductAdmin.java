package com.example.duanbanlaptop.admin;


import com.example.duanbanlaptop.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditProductAdmin {
    @FXML
    public Button editButton;
    @FXML
    private TextField nameproduct1;

    @FXML
    private TextField describe1;
    @FXML
    private TextField unit1;
    @FXML
    private TextField price1;
    @FXML
    private TextField stock1;
    @FXML
    private TextField imadeURL1;
    @FXML
    private int idProduct;

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @FXML
    public void edit(ActionEvent event) throws SQLException {
        String name = nameproduct1.getText();
        String describeProduct = describe1.getText();
        String unitProduct = unit1.getText();
        String priceProduct = price1.getText();
        String stockProduct = stock1.getText();
        String imadeURLProduct = imadeURL1.getText();


        Connect conn = new Connect();
        Connection connection = conn.connect();
        String query = "UPDATE Products SET nameproduct=? , `describe`=?, unit=?, price=?, stock=?, image=? WHERE idProduct=?";

        PreparedStatement update = connection.prepareStatement(query);
        update.setString(1, name);
        update.setString(2, describeProduct);
        update.setString(3, unitProduct);
        update.setString(4, priceProduct);
        update.setString(5, stockProduct);
        update.setString(6, imadeURLProduct);
        update.setInt(7, idProduct );

        int rowsUpdated = update.executeUpdate();
        if (rowsUpdated > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("update successful ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("id not found");
        }


        update.close();
        connection.close();
    }

}

