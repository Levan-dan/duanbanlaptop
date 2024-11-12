package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;

import com.example.duanbanlaptop.Main;
import com.example.duanbanlaptop.Object.Products;
import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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

        String query = "SELECT idProduct,image ,nameProduct, price, stock FROM products";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        iterm.getChildren().clear();

        while (resultSet.next()) {
            String imageUrl = resultSet.getString("image");
            String nameProductUser = resultSet.getString("nameProduct");
            Double priceUser = resultSet.getDouble("price");
            int stockUser = resultSet.getInt("stock");
            int idProduct = resultSet.getInt("idProduct");


            VBox vbox = createProductBox(idProduct,imageUrl, nameProductUser, priceUser, stockUser);
            iterm.getChildren().add(vbox);
        }

        resultSet.close();
        statement.close();
    }

    private VBox createProductBox(int id,String imageUrl, String name, double price, int stock) {
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

        ImageView imageView = new ImageView(new Image(imageUrl));
        imageView.setFitWidth(135);
        imageView.setFitHeight(145);

        imageView.setOnMouseClicked(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("productDetailsUsers.fxml"));
                Parent root = loader.load();
                ProductDetailsUsersController controller = loader.getController();
                controller.setInfo(id);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();

            }catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Rectangle clip = new Rectangle(135, 145);
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        Label nameLabel = new Label(name);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #333333;");
        Label priceLabel = new Label(  price + " VND");
        Label quantityLabel = new Label("Quantity: " + stock);


        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel);
        vbox.setMinHeight(260);
        vbox.setMinWidth(160);
        vbox.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 15; -fx-background-radius: 15");
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

        String query = "SELECT idProduct, image, nameProduct, price, stock FROM products WHERE nameProduct LIKE ?";
        java.sql.PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, "%" + keyword + "%");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("idProduct");
            String imageUrl = resultSet.getString("image");
            String nameProductUser = resultSet.getString("nameProduct");
            Double priceUser = resultSet.getDouble("price");
            int stockUser = resultSet.getInt("stock");

            VBox vbox = createProductBox(id,imageUrl, nameProductUser, priceUser, stockUser);
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

    public void userInformation() throws IOException, SQLException {

        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/com/example/duanbanlaptop/view/admin-information.fxml"));
        Parent parent = fxmlLoader.load();

        Stage adminInformation = new Stage();
        adminInformation.setTitle("Admin information");
        adminInformation.setScene(new Scene(parent));

        // Đặt chế độ cho Stage mới
        adminInformation.initModality(Modality.APPLICATION_MODAL); // Chặn tương tác với Stage khác
        adminInformation.showAndWait();

    }

    public void setting() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/duanbanlaptop/view/setting.fxml"));
        Parent parent = fxmlLoader.load();

        Stage userSetting = new Stage();
        userSetting.setTitle("Admin information");
        userSetting.setScene(new Scene(parent));

        // Đặt chế độ cho Stage mới
        userSetting.initModality(Modality.APPLICATION_MODAL); // Chặn tương tác với Stage khác
        userSetting.showAndWait();


    }


}
