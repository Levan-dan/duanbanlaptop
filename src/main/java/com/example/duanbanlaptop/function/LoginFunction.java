package com.example.duanbanlaptop.function;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Main;
import com.example.duanbanlaptop.Object.User;
import com.example.duanbanlaptop.admin.AdminInformationController;
import com.example.duanbanlaptop.admin.LoginUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFunction {


    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label bug;



    private String username1;
    private String password;



    // Phương thức để lấy thông tin username

    @FXML
    public void transitionToSignup() throws IOException {

        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.transition("view/signup.fxml");
    }

    public void login() throws SQLException, IOException {

        username1 = txtUsername.getText();
        password = txtPassword.getText();


        if (username1.isEmpty() || password.isEmpty()) {
            bug.setText("Please fill in your login information completely!");
        } else if (!SignupRegex.validUsername(username1)) {
            bug.setText("Usernames can only be written without accents");
        } else if (password.length() < 8) {
            bug.setText("Password must be eight characters or more");
        } else {
            Connect connect = new Connect();
            Connection conn = connect.connect();
            String query = "select idUser, role from user where username = ? and password = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, username1);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("idUser");
                String role = resultSet.getString("role");
                if (role.equalsIgnoreCase("admin")) {
                    TransitionFunction transitionFunction = new TransitionFunction();
                    transitionFunction.transition("view/admin.fxml");



                } else {
                    TransitionFunction transitionFunction = new TransitionFunction();
                    transitionFunction.transition("view/homeUser.fxml");

                }
                LoginUser.login(id, username1);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Incorrect password or username!");
                alert.showAndWait();
                txtUsername.setText("");
                txtPassword.setText("");
                bug.setText("");
            }
        }


    }
}


