module org.example.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;


    opens org.example.project2.view to javafx.fxml;
    opens org.example.project2 to javafx.graphics;
    exports org.example.project2.view;
}