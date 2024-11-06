module com.example.duanbanlaptop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;

    exports com.example.duanbanlaptop.admin to javafx.fxml;
    opens com.example.duanbanlaptop to javafx.fxml;
    exports com.example.duanbanlaptop;
    exports com.example.duanbanlaptop.function;
    opens com.example.duanbanlaptop.function to javafx.fxml;

    opens com.example.duanbanlaptop.Object to javafx.base;
    opens com.example.duanbanlaptop.admin to javafx.fxml, java.base;

}