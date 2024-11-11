package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SearchUserFunction {

    public List<VBox> searchProductByName(String keyword) throws SQLException {
        List<VBox> searchResults = new ArrayList<>();

        Connect connect = new Connect();
        Connection conn = connect.connect();

        String query = "SELECT image, nameProduct, price, stock FROM products WHERE nameProduct LIKE '%" + keyword + "%'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String imageUrl = resultSet.getString("image");
            String nameProductUser = resultSet.getString("nameProduct");
            Double priceUser = resultSet.getDouble("price");
            int stockUser = resultSet.getInt("stock");

            VBox vbox = createProductBox(imageUrl, nameProductUser, priceUser, stockUser);
            searchResults.add(vbox);
        }

        return searchResults;
    }

    private VBox createProductBox(String imageUrl, String name, Double price, int stock) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(new javafx.scene.image.Image(imageUrl));
        imageView.setFitWidth(135);
        imageView.setFitHeight(145);

        javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(135, 145);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        javafx.scene.control.Label nameLabel = new javafx.scene.control.Label(name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #333333;");
        javafx.scene.control.Label priceLabel = new javafx.scene.control.Label(price + " VND");
        priceLabel.setStyle("-fx-text-fill: #ff0000");
        javafx.scene.control.Label quantityLabel = new javafx.scene.control.Label("Quantity: " + stock);

        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel);
        vbox.setStyle("-fx-border-color: black; -fx-border-radius: 15");
        vbox.setMinWidth(160);
        return vbox;
    }
}
