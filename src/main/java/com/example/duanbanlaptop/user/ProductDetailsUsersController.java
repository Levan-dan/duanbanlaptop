package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetailsUsersController {
    @FXML
    private ImageView imageProduct;
    @FXML
    private Label idProduct;
    @FXML
    private Label nameProduct;
    @FXML
    private Label describeProduct;
    @FXML
    private Label unitProduct;
    @FXML
    private Label stockProduct;
    @FXML
    private Label priceProduct;
    private int id;
    public void setInfo(int id) throws SQLException {
        this.id = id;
        String query = " select * from products where idProduct = ?";

        Connect connect = new Connect();
        Connection connection = connect.connect();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            idProduct.setText(resultSet.getString("idProduct"));
            nameProduct.setText(resultSet.getString("nameProduct"));
            describeProduct.setText(resultSet.getString("describe"));
            unitProduct.setText(resultSet.getString("unit"));
            stockProduct.setText(resultSet.getString("stock") + " Piece");
            priceProduct.setText(resultSet.getString("price") + " VND");
            imageProduct.setImage(new Image(resultSet.getString("image")));

        }
        preparedStatement.executeQuery();
    }
}


