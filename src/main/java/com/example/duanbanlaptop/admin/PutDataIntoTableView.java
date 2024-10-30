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
    public void initialize(){
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

        // Lấy dữ liệu từ cơ sở dữ liệu và đặt vào bảng
        try {
            ObservableList<Products> data = getDataFromDatabase();
            tableView.setItems(data);  // Đặt dữ liệu vào bảng
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

        // Sử dụng vòng lặp để duyệt qua từng dòng kết quả
        while (rt.next()) {
            int idProduct = rt.getInt("idProduct");
            String nameProduct = rt.getString("nameProduct");
            String describe = rt.getString("describe");
            String unit = rt.getString("unit");
            String image = rt.getString("image");
            double price = rt.getDouble("price");
            int stock = rt.getInt("stock");

            // Tạo đối tượng Products và thêm vào danh sách
            Products product = new Products(idProduct, nameProduct, describe, unit, image, price, stock);
            productList.add(product);
        }

        // Đóng các tài nguyên sau khi hoàn thành
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
}
