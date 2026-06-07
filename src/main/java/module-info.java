module proyecto15.proyecto_15 {
    requires javafx.controls;
    requires javafx.fxml;


    opens proyecto15.proyecto_15 to javafx.fxml;
    exports proyecto15.proyecto_15;
}