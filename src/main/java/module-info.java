module com.example.saanpsidi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.saanpsidi to javafx.fxml;
    exports com.example.saanpsidi;
}