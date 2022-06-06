module com.catcompanion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.catcompanion to javafx.fxml;
    exports com.catcompanion;
}