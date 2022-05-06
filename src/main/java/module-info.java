module com.example.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.schedulingsystem to javafx.fxml;
    exports com.example.schedulingsystem;
}