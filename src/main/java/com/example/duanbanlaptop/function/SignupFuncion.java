package com.example.duanbanlaptop.function;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.duanbanlaptop.Connect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignupFuncion {


    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private ChoiceBox<String> txtGender;
    @FXML
    private Label error;


    @FXML
    private void signup() throws SQLException, IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String phonenumber = txtPhoneNumber.getText();
        String email = txtEmail.getText();
        String address = txtAddress.getText();
        String gender = txtGender.getValue();


        if (username.isEmpty() || password.isEmpty() || phonenumber.isEmpty() || email.isEmpty() || address.isEmpty() || gender == null) {
            error.setText("Please fill in all registration information !");
        } else if (!SignupRegex.validUsername(username)) {
            error.setText("Usernames can only be written without accents !");
        } else {

            for (char t : username.toCharArray()) {
                if (!Character.isLetterOrDigit(t)) {
                    error.setText("Username cannot contain special characters.");
                    return;
                }
            }

            if (password.length() < 8) {
                error.setText("Password must be at least 8 characters long.");
            } else if (phonenumber.length() < 10 || phonenumber.length() > 10) {
                error.setText("Phone numbers must be 10 characters ");
                for (char t : phonenumber.toCharArray()) {
                    if (!Character.isDigit(t)) {
                        error.setText("Phone numbers must be number ");
                        return;
                    }
                }
            } else if (!SignupRegex.validPhoneNumber(phonenumber)) {
                error.setText("Invalid phone number");
            } else if (!SignupRegex.validEmail(email)) {
                error.setText("Email is not in correct format.");
            } else {
                Connect connect = new Connect();
                Connection conn = connect.connect();

                String query = "insert into  user  (username, password, email, phoneNumber, role, address, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement add = conn.prepareStatement(query);
                add.setString(1, username);
                add.setString(2, password);
                add.setString(3, email);
                add.setString(4, phonenumber);
                add.setString(5, "customer");
                add.setString(6, address);
                add.setString(7, gender);
                int row = add.executeUpdate();
                if (row != 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Registration successful!");
                    alert.showAndWait();  // Hiển thị hộp thoại

                    TransitionFunction transitionFunction = new TransitionFunction();
                    transitionFunction.transition("view/login.fxml");
                }

            }
        }
    }

    public void transitionToLogin() throws IOException {
        TransitionFunction transitionFunction = new TransitionFunction();
        transitionFunction.transition("view/login.fxml");
    }
}












