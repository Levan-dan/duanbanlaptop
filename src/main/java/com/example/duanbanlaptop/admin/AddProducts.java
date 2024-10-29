package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;


public class AddProducts {
    @FXML
    private TextField nameproduct;
    @FXML
    private TextField describe;
    @FXML
    private TextField unit;
    @FXML
    private TextField price;
    @FXML
    private TextField stock;
    @FXML
    private TextField imadeURL;

    public void add() throws SQLException {
        String name = nameproduct.getText();
        String describeProduct = describe.getText();
        String unitProduct = unit.getText();
        String imadeURLProduct = imadeURL.getText();
        double priceProduct = Double.parseDouble(price.getText());
        int stockProduct = Integer.parseInt(stock.getText());

        Connect conn = new Connect();
        Connection connection = conn.connect();

        String query = "insert into products ( nameProduct, `describe`, unit, image, price, stock) values (?,?,?,?,?,?)";
        PreparedStatement add = connection.prepareStatement(query);
        add.setString(1, name);
        add.setString(2, describeProduct);
        add.setString(3, unitProduct);
        add.setString(4, imadeURLProduct);
        add.setDouble(5, priceProduct);
        add.setInt(6, stockProduct);

        int rows = add.executeUpdate();
        if (rows != 0) {
            System.out.println("Them thanh cong");
        }
    }






    }




