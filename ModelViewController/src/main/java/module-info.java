module com.example.modelviewcontroller {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.modelviewcontroller to javafx.fxml;
    exports com.example.modelviewcontroller;
}