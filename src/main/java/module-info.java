module com.example.schedulingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.schedulingsystem to javafx.fxml;
    opens Class to javafx.fxml;
    exports com.example.schedulingsystem;
    exports Class;

}