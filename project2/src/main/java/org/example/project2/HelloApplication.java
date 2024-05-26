package org.example.project2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.project2.db.CsvReader;
import org.example.project2.logic.DataEntry;
import org.example.project2.logic.Quantifier;
import org.example.project2.logic.Summary;
import org.example.project2.variables.VarCoalLables;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
        List<DataEntry> dataEntries = csvReader.readData();
        VarCoalLables varCoalLables = new VarCoalLables();
        System.out.println(varCoalLables.getLabelsCoalAnnChangeProdTwh().get(0).getFuzzySet().degreeOfMembership(dataEntries.get(0)));
    }

    public static void main(String[] args) {
        launch();
    }
}