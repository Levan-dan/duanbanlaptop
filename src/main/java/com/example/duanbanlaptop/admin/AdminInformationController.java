package com.example.duanbanlaptop.admin;


import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.User;
import com.example.duanbanlaptop.function.LoginFunction;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminInformationController {

    @FXML
    private Label nameCustomer;
    @FXML
    private Label numberCustomer;
    @FXML
    private Label addressCustomer;
    @FXML
    private Label emailCustomer;

    @FXML
    public void initialize() throws SQLException {
        userInformation();
    }


    public void userInformation() throws SQLException {
        Connect connect = new Connect();
        try (Connection conn = connect.connect()) {
            String query1 = "SELECT username, address, phoneNumber, email FROM user WHERE idUser = ? ";
            try (PreparedStatement preparedStatement1 = conn.prepareStatement(query1)) {
                preparedStatement1.setInt(1, LoginUser.getInstance().getId());
                try (ResultSet rt = preparedStatement1.executeQuery()) {

                    if (rt.next()) {
                        String name = rt.getString("username");
                        String phone = rt.getString("phoneNumber");
                        String address = rt.getString("address");
                        String email = rt.getString("email");

                        nameCustomer.setText(name);
                        numberCustomer.setText(phone);
                        addressCustomer.setText(address);
                        emailCustomer.setText(email);
                    }
                }
            }
        }
    }
}



