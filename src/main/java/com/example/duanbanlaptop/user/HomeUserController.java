package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeUserController {

    @FXML
    private FlowPane iterm;
    @FXML
    private Image imageViewUser;
    @FXML
    private Label nameView;
    @FXML
    private Label priceView;
    @FXML
    private Label stockView;

    @FXML
    public void initialize() throws SQLException {
        getData();
    }


public void getData() throws SQLException {
        iterm.setHgap(41);
        iterm.setVgap(30);
        iterm.setPrefWrapLength(965);

//    List<Products> products = new ArrayList<>();
    Connect connect = new Connect();
    Connection conn = connect.connect();

    String query = "select image, nameProduct, price, stock from products";
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery(query);

    while (resultSet.next()){
        String imageUrl = resultSet.getString("image");
        String nameProductUser = resultSet.getString("nameProduct");
        Double priceUser = resultSet.getDouble("price");
        int stockUser = resultSet.getInt("stock");

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(imageUrl);
        imageView.setFitWidth(135);
        imageView.setFitHeight(145);

        Rectangle clip = new Rectangle(135, 145);
        clip.setArcWidth(20);  // Độ cong của góc (càng lớn, góc càng tròn)
        clip.setArcHeight(20);

// Áp dụng clip vào ImageView
        imageView.setClip(clip);

        Label nameLabel = new Label(nameProductUser);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #333333;");
        Label priceLabel = new Label(  priceUser + " VND");
        priceLabel.setStyle("-fx-text-fill: red");
        Label quantityLabel = new Label("Quantity: " + stockUser);

//        button

        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel);
        vbox.setStyle("-fx-border-color: black; -fx-border-radius: 15");
        vbox.setMinWidth(160);
        iterm.getChildren().add(vbox);
//        products.add(new Products(imageUrl, nameProductUser, priceUser,stockUser));
    }
//    return products;
}

}
