module org.example.pmanagementapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pmanagementapp to javafx.fxml;
    exports org.example.pmanagementapp;
}