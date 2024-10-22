module com.example.duanbanlaptop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.duanbanlaptop to javafx.fxml;
    exports com.example.duanbanlaptop;
    exports com.example.duanbanlaptop.function;
    opens com.example.duanbanlaptop.function to javafx.fxml;
}