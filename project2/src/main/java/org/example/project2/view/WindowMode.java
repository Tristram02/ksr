package org.example.project2.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.project2.db.CsvReader;
import org.example.project2.logic.linguistics.DataEntry;
import org.example.project2.logic.linguistics.Label;
import org.example.project2.logic.linguistics.Quantifier;
import org.example.project2.Initialization;
import org.example.project2.logic.linguistics.Summary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WindowMode extends Application {

    Quantifier quantifier;
    List<Label> summarizers = new ArrayList<>();
    List<Label> qualifiers = new ArrayList<>();

    Initialization initialData = new Initialization();


    @FXML
    private ComboBox quantifierCB;
    @FXML
    private TreeView qualifiersTV;
    @FXML
    private TreeView summarizersTV;
    @FXML
    private javafx.scene.control.Label summary;
    @FXML
    private javafx.scene.control.Label T1;
    @FXML
    private javafx.scene.control.Label T2;
    @FXML
    private javafx.scene.control.Label T3;
    @FXML
    private javafx.scene.control.Label T4;
    @FXML
    private javafx.scene.control.Label T5;
    @FXML
    private javafx.scene.control.Label T6;
    @FXML
    private javafx.scene.control.Label T7;
    @FXML
    private javafx.scene.control.Label T8;
    @FXML
    private javafx.scene.control.Label T9;
    @FXML
    private javafx.scene.control.Label T10;
    @FXML
    private javafx.scene.control.Label T11;
    @FXML
    private javafx.scene.control.Label T;

    private void addQuantifiers() {
        quantifierCB.getItems().add(initialData.getNearlyNone().getName());
        quantifierCB.getItems().add(initialData.getAround1_4().getName());
        quantifierCB.getItems().add(initialData.getAroundHalf().getName());
        quantifierCB.getItems().add(initialData.getAround3_4().getName());
        quantifierCB.getItems().add(initialData.getMost().getName());
        quantifierCB.getItems().add(initialData.getNearlyAll().getName());
        quantifierCB.getItems().add(initialData.getLessThan1000().getName());
        quantifierCB.getItems().add(initialData.getAbout2000().getName());
        quantifierCB.getItems().add(initialData.getAbout5000().getName());
        quantifierCB.getItems().add(initialData.getAbout6000().getName());
        quantifierCB.getItems().add(initialData.getOver8000().getName());
        quantifierCB.getItems().add(initialData.getOver10000().getName());

        quantifierCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                String selected = (String) quantifierCB.getItems().get((Integer) t1);

                for (Quantifier q: initialData.getAllQuantifiers()) {
                    if (selected.equals(q.getName())) {
                        quantifier = q;
                    }
                }
                generateSummary();
            }
        });
    }

    private void addQualifiersAndSummarizers() {

        qualifiersTV.setRoot(new TreeItem("Kwalifikatory"));
        summarizersTV.setRoot(new TreeItem("Summaryzatory"));

        TreeItem treeItem1 = new TreeItem("Coal");
        TreeItem treeItem2 = new TreeItem("Coal");

        for (Label label: initialData.getLabelsCoalAnnChangeProdTwh()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsCoalProdPerCapita()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsCoalProd()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        qualifiersTV.getRoot().getChildren().add(treeItem1);
        summarizersTV.getRoot().getChildren().add(treeItem2);

        treeItem1 = new TreeItem("Oil");
        treeItem2 = new TreeItem("Oil");

        for (Label label: initialData.getLabelsOilAnnChangeProdTwh()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsOilProdPerCapita()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsOilProd()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        qualifiersTV.getRoot().getChildren().add(treeItem1);
        summarizersTV.getRoot().getChildren().add(treeItem2);

        treeItem1 = new TreeItem("Gas");
        treeItem2 = new TreeItem("Gas");

        for (Label label: initialData.getLabelsGasAnnChangeProdTwh()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsGasProdPerCapita()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        for (Label label: initialData.getLabelsGasProd()) {
            treeItem1.getChildren().add(new TreeItem(label.getName()));
            treeItem2.getChildren().add(new TreeItem(label.getName()));
        }
        qualifiersTV.getRoot().getChildren().add(treeItem1);
        summarizersTV.getRoot().getChildren().add(treeItem2);

        qualifiersTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        qualifiersTV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                List<Integer> selected = qualifiersTV.getSelectionModel().getSelectedIndices().stream().toList();
                qualifiers.clear();

                for (Integer id: selected) {
                    TreeItem treeItem = qualifiersTV.getTreeItem(id);
                    if (qualifiersTV.getTreeItemLevel(treeItem) == 2) {
                        for (Label label: initialData.getAllLabels()) {
                            if (label.getName().equals(treeItem.getValue())) {
                                qualifiers.add(label);
                            }
                        }
                    }
                }
                generateSummary();
            }
        });

        summarizersTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        summarizersTV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                List<Integer> selected = summarizersTV.getSelectionModel().getSelectedIndices().stream().toList();
                summarizers.clear();

                for (Integer id: selected) {
                    TreeItem treeItem = summarizersTV.getTreeItem(id);
                    if (summarizersTV.getTreeItemLevel(treeItem) == 2) {
                        for (Label label: initialData.getAllLabels()) {
                            if (label.getName().equals(treeItem.getValue())) {
                                summarizers.add(label);
                            }
                        }
                    }
                }
                generateSummary();
            }
        });
    }

    private void setMetrics(Summary summary) {
        T1.setText(STR."T1: \{Math.round(summary.degreeOfTruth() * 100.0) / 100.0}");
        T2.setText(STR."T2: \{Math.round(summary.degreeOfImprecision() * 100.0) / 100.0}");
        T3.setText(STR."T3: \{Math.round(summary.degreeOfCovering() * 100.0) / 100.0}");
        T4.setText(STR."T4: \{Math.round(summary.degreeOfAppropriateness() * 100.0) / 100.0}");
        T5.setText(STR."T5: \{Math.round(summary.lengthOfSummary() * 100.0) / 100.0}");
        T6.setText(STR."T6: \{Math.round(summary.degreeOfQuantifierImprecision() * 100.0) / 100.0}");
        T7.setText(STR."T7: \{Math.round(summary.degreeOfQuantifierCardinality() * 100.0) / 100.0}");
        T8.setText(STR."T8: \{Math.round(summary.degreeOfSummarizerCardinality() * 100.0) / 100.0}");
        T9.setText(STR."T9: \{Math.round(summary.degreeOfQualifierImprecision() * 100.0) / 100.0}");
        T10.setText(STR."T10: \{Math.round(summary.degreeOfQualifierCardinality() * 100.0) / 100.0}");
        T11.setText(STR."T11: \{Math.round(summary.lengthOfQualifier() * 100.0) / 100.0}");
        T.setText(STR."T: \{Math.round(summary.quality() * 100.0) / 100.0}");
    }
    private void generateSummary() {
        if (quantifier != null && summarizers.size() > 0) {
            CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
            List<DataEntry> dataEntries = csvReader.readData();
            if (qualifiers.size() > 0) {
                Summary generatedSummary = new Summary(quantifier, qualifiers, dataEntries, summarizers);
                summary.setText(generatedSummary.toString());
                setMetrics(generatedSummary);
            } else {
                Summary generatedSummary = new Summary(quantifier, null, dataEntries, summarizers);
                summary.setText(generatedSummary.toString());
                setMetrics(generatedSummary);
            }

        }
    }
    public void initialize() {
        addQuantifiers();
        addQualifiersAndSummarizers();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("Project 2");
        stage.setScene(scene);
        stage.show();
    }
}
