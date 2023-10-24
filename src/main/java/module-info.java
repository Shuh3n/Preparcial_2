module com.example.preparcial_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    exports com.example.preparcial_2.application;
    exports com.example.preparcial_2.model;
    exports com.example.preparcial_2.viewControllers;
    opens com.example.preparcial_2.viewControllers to javafx.fxml;
    opens com.example.preparcial_2.model to javafx.fxml;
    opens com.example.preparcial_2.application to javafx.fxml;
}