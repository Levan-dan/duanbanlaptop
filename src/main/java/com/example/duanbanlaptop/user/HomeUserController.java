package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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
    private TextField searchField;

    @FXML
    private Button onLaptop, onAccessory, onKeyboard;

    @FXML
    public void initialize() throws SQLException {
        getData();
    }

    public void getData() throws SQLException {
        iterm.setHgap(41);
        iterm.setVgap(30);
        iterm.setPrefWrapLength(965);

        Connect connect = new Connect();
        Connection conn = connect.connect();

        String query = "SELECT image, nameProduct, price, stock FROM products";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        iterm.getChildren().clear();

        while (resultSet.next()) {
            String imageUrl = resultSet.getString("image");
            String nameProductUser = resultSet.getString("nameProduct");
            Double priceUser = resultSet.getDouble("price");
            int stockUser = resultSet.getInt("stock");

            VBox vbox = createProductBox(imageUrl, nameProductUser, priceUser, stockUser);
            iterm.getChildren().add(vbox);
        }

        resultSet.close();
        statement.close();
        conn.close();
    }

    private VBox createProductBox(String imageUrl, String name, double price, int stock) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(135);
        imageView.setFitHeight(145);

        Rectangle clip = new Rectangle(135, 145);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #333333;");
        Label priceLabel = new Label(price + " VND");
        priceLabel.setStyle("-fx-text-fill: #ff0000;");
        Label quantityLabel = new Label("Quantity: " + stock);

        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel);
        vbox.setStyle("-fx-border-color: black; -fx-border-radius: 15;");
        vbox.setMinWidth(160);

        return vbox;
    }

    @FXML
    private void onSearch() throws SQLException {
        String keyword = searchField.getText().trim();
        displayProductsByKeyword(keyword);
    }

    @FXML
    private void onLaptop() throws SQLException {
        displayProductsByKeyword("Laptop");
    }

    @FXML
    private void onAccessory() throws SQLException {
        displayProductsByKeyword("Accessory");
    }

    @FXML
    private void onKeyboard() throws SQLException {
        displayProductsByKeyword("Key");
    }

    private void displayProductsByKeyword(String keyword) throws SQLException {
        List<VBox> searchResults = searchProductByName(keyword);
        iterm.getChildren().clear();
        iterm.getChildren().addAll(searchResults);
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    private List<VBox> searchProductByName(String keyword) throws SQLException {
        List<VBox> results = new ArrayList<>();
        Connect connect = new Connect();
        Connection conn = connect.connect();

        String query = "SELECT image, nameProduct, price, stock FROM products WHERE nameProduct LIKE ?";
        java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "%" + keyword + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String imageUrl = resultSet.getString("image");
            String nameProductUser = resultSet.getString("nameProduct");
            Double priceUser = resultSet.getDouble("price");
            int stockUser = resultSet.getInt("stock");

            VBox vbox = createProductBox(imageUrl, nameProductUser, priceUser, stockUser);
            results.add(vbox);
        }

        resultSet.close();
        preparedStatement.close();
        conn.close();

        return results;
    }


    @FXML
    private void onHome() throws SQLException {
        getData();
    }

}
