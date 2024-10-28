package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminFunction {
    @FXML
    private Label title;

    public void product() {
        title.setText("Product Management");
    }

    public void order() {
        title.setText("Order management");

    }

    public void bill() {
        title.setText("Bill Management");

    }

    public void customer() {
        title.setText("Customer Management");
    }
}
