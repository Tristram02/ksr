package org.example.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2.db.CsvReader;
import org.example.project2.logic.Quantifier;
import org.example.project2.logic.Summary;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
        csvReader.readData();
    }

    public static void main(String[] args) {
        launch();
    }
}