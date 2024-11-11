package com.example.duanbanlaptop.user;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;
import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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
        imageView.setFitHeight(135);

        Rectangle clip = new Rectangle(135, 145);
        clip.setArcWidth(20);  // Độ cong của góc (càng lớn, góc càng tròn)
        clip.setArcHeight(20);

// Áp dụng clip vào ImageView
        imageView.setClip(clip);

        Label nameLabel = new Label(nameProductUser);
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px; -fx-text-fill: #333333;");
        Label priceLabel = new Label(  priceUser + " VND");
        Label quantityLabel = new Label("Quantity: " + stockUser);

//        button

        vbox.getChildren().addAll(imageView, nameLabel, priceLabel, quantityLabel);
        vbox.setStyle("-fx-border-color: black; -fx-border-radius: 15");
        vbox.setMinWidth(160);
        vbox.setMinHeight(260);
        iterm.getChildren().add(vbox);
//        products.add(new Products(imageUrl, nameProductUser, priceUser,stockUser));
    }
//    return products;
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
