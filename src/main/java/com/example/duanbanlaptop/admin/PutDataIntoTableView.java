package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;
import com.example.duanbanlaptop.function.TransitionFunction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class PutDataIntoTableView {
    @FXML
    private TableView<Products> tableView;
    @FXML
    private TableColumn<Products, Integer> columnId;
    @FXML
    private TableColumn<Products, String> columnName;
    @FXML
    private TableColumn<Products, String> columnDescribe;
    @FXML
    private TableColumn<Products, String> columnUnit;
    @FXML
    private TableColumn<Products, String> columnImage;
    @FXML
    private TableColumn<Products, Double> columnPrice;
    @FXML
    private TableColumn<Products, Integer> columnStock;





    @FXML
    public void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        columnDescribe.setCellValueFactory(new PropertyValueFactory<>("describe"));
        columnUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        columnImage.setCellValueFactory(new PropertyValueFactory<Products, String>("image"));

        columnImage.setCellFactory(column -> new TableCell<Products, String>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    imageView.setImage(new Image(imagePath));
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(80);
                    setGraphic(imageView);
                    setAlignment(Pos.CENTER);
                }
            }
        });


        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        try {
            ObservableList<Products> data = getDataFromDatabase();
            tableView.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Products> getDataFromDatabase() throws SQLException {
        ObservableList<Products> productList = FXCollections.observableArrayList();
        Connect connect = new Connect();
        Connection conn = connect.connect();

        String query = "SELECT * FROM products";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet rt = preparedStatement.executeQuery();

        while (rt.next()) {
            int idProduct = rt.getInt("idProduct");
            String nameProduct = rt.getString("nameProduct");
            String describe = rt.getString("describe");
            String unit = rt.getString("unit");
            String image = rt.getString("image");
            double price = rt.getDouble("price");
            int stock = rt.getInt("stock");

            Products product = new Products(idProduct, nameProduct, describe, unit, image, price, stock);
            productList.add(product);
        }

        rt.close();
        preparedStatement.close();
        conn.close();

        return productList;
    }


    public void addProduct() throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/com/example/duanbanlaptop/view/addProduct.fxml"));
        Parent parent = fxmlLoader.load();

        Stage addProductStage = new Stage();
        addProductStage.setTitle("Add Product");
        addProductStage.setScene(new Scene(parent));

        // Đặt chế độ cho Stage mới
        addProductStage.initModality(Modality.APPLICATION_MODAL); // Chặn tương tác với Stage khác
        addProductStage.showAndWait();


  
    }

    public void delete() throws SQLException {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Delete ");
        inputDialog.setHeaderText("Please enter the product code you want to delete ");
        inputDialog.setContentText("idProduct");
        Optional<String> result = inputDialog.showAndWait();

        int number;
        if (result.isPresent()){
            try{
                number = Integer.parseInt(result.get());
            }catch (NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter the correct format ");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete this product");

            Optional<ButtonType> result1 = alert.showAndWait();
            if (result1.isPresent() && result1.get() == ButtonType.OK){

                DeleteProduct deleteProduct = new DeleteProduct();
                deleteProduct.deleteProduct(number);

                tableView.getItems().removeIf(product -> product.getIdProduct() == number);

            }
        }

    }
    public void edit(ActionEvent event) throws SQLException, IOException {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Edit");
        inputDialog.setHeaderText("Please enter the product code you want to edit");
        inputDialog.setContentText("idProduct");
        Optional<String> result = inputDialog.showAndWait();

        int number;
        if (result.isPresent()) {
            try {
                number = Integer.parseInt(result.get());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter the correct format");
                alert.showAndWait();
                return;
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to edit this product");

            Optional<ButtonType> result1 = alert.showAndWait();
            if (result1.isPresent() && result1.get() == ButtonType.OK) {
                GetProductInfo getProductInfo = new GetProductInfo();
                Products products = getProductInfo.getProductInfo(number);

                if (products != null) {
                    System.out.println(products.getNameProduct());

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/duanbanlaptop/view/edit.fxml"));
                    Parent parent = fxmlLoader.load();

                    EditProductAdmin editProductAdmin = fxmlLoader.getController();
                    editProductAdmin.setIdProduct(number);

                    Stage addProductStage = new Stage();
                    addProductStage.setTitle("Edit Product");
                    addProductStage.setScene(new Scene(parent));

                    addProductStage.initModality(Modality.APPLICATION_MODAL);
                    addProductStage.showAndWait();
                } else {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setTitle("Confirmation");
                    alert1.setContentText("NOT FOUND IDPRODUCT");
                    alert1.showAndWait();
                }
            }
        }
    }




}
