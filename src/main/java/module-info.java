module com.example.duanbanlaptop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.duanbanlaptop to javafx.fxml;
    exports com.example.duanbanlaptop;
}