package com.example.duanbanlaptop.admin;


import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;
import com.mysql.cj.jdbc.ConnectionImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private int idProduct1;

    public void setIdProduct(int idProduct) throws SQLException {
        this.idProduct1 = idProduct;
        dataProduct();
    }
    public void initialize() throws SQLException {
        unit1.setText("piece");
        unit1.setEditable(false);
        unit1.setDisable(true);
    }

    public void dataProduct() throws SQLException {
        String query = "select * from Products where idProduct = ?";

        Connect conn = new Connect();
        Connection connection = conn.connect();
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, idProduct1);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String productName = rs.getString("nameProduct");
            String productDescription = rs.getString("describe");
            String productPrice = rs.getString("price");
            String productStock = rs.getString("stock");
            String productImadeURL = rs.getString("image");
            nameproduct1.setText(productName);
            describe1.setText(productDescription);
            price1.setText(productPrice);
            stock1.setText(productStock);
            imadeURL1.setText(productImadeURL);

        }
        else {
            System.out.println("Khong tim thay");
        }
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
        update.setInt(7, idProduct1);

        if (name.isEmpty() || describeProduct.isEmpty() || unitProduct.isEmpty() || priceProduct.isEmpty()
                || stockProduct.isEmpty() || imadeURLProduct.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please complete the information for all fields");
            alert.showAndWait();
            return;
        }

        int rowsUpdated = update.executeUpdate();
        if (rowsUpdated > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("update successful ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("id not found");
        }


        Stage stage = (Stage) unit1.getScene().getWindow();
        stage.close();


        update.close();
        connection.close();
    }

}

