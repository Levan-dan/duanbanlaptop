package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

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


    public void add() {
        String name = nameproduct.getText();
        String describeProduct = describe.getText();
        String unitProduct = unit.getText();
        String imadeURLProduct = imadeURL.getText();

        double priceProduct = 0;
        int stockProduct = 0;

        // Kiểm tra và chuyển đổi giá trị price
        try {
            priceProduct = Double.parseDouble(price.getText());
            stockProduct = Integer.parseInt(stock.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid Price or Stock value.");
            alert.showAndWait();
            return;
        }


        if (name.isEmpty() || describeProduct.isEmpty() || unitProduct.isEmpty() || imadeURLProduct.isEmpty() || priceProduct < 0 || stockProduct < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please double check the data you just entered.");
            alert.showAndWait();
        } else {
            Connect conn = new Connect();
            try (Connection connection = conn.connect()) {
                String query = "INSERT INTO products (nameProduct, `describe`, unit, image, price, stock) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement add = connection.prepareStatement(query);
                add.setString(1, name);
                add.setString(2, describeProduct);
                add.setString(3, unitProduct);
                add.setString(4, imadeURLProduct);
                add.setDouble(5, priceProduct);
                add.setInt(6, stockProduct);

                int rows = add.executeUpdate();
                if (rows != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thêm sản phẩm thành công!", ButtonType.OK);
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            Stage stage = (Stage) nameproduct.getScene().getWindow();
                            stage.close();
                        }
                    });
                }

            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi nếu có
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Có lỗi xảy ra khi thêm sản phẩm vào cơ sở dữ liệu.");
                alert.showAndWait();
            }
        }
    }






    }




