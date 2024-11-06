package com.example.duanbanlaptop.admin;

import com.example.duanbanlaptop.Connect;
import com.example.duanbanlaptop.Object.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetProductInfo {

    public Products getProductInfo(int id) throws SQLException {
        Products products = null;
        Connect connect = new Connect();
        Connection conn = connect.connect();

        String query = "select nameProduct, `describe`, unit, image, price, stock from products where idProduct = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            String name = rs.getString("nameProduct");
            String describe = rs.getString("describe");
            String unit = rs.getString("unit");
            String image = rs.getString("image");
            double price = Double.parseDouble(rs.getString("price"));
            int stock = rs.getInt("stock");
            products = new Products(name, describe, unit, image, price, stock);

        }
        return products;
    }
}
