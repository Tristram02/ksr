module org.example.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;
    requires commons.math3;


    opens org.example.project2.view to javafx.fxml;
    opens org.example.project2 to javafx.graphics;
    exports org.example.project2.view;
}