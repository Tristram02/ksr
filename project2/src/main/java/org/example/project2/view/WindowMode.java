package org.example.project2.view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.project2.Initialization;
import org.example.project2.db.CsvReader;
import org.example.project2.enums.ContinentsEnum;
import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.*;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WindowMode extends Application {



    List<org.example.project2.logic.linguistics.Label> attributes = new ArrayList<>();
    List<org.example.project2.logic.linguistics.Label> qualifiers = new ArrayList<>();
    Initialization initialData = new Initialization();
    Double a;
    Double b;
    Double c;
    Double d;
    String name;
    boolean isAbsolute;

    @FXML
    private ComboBox subject1CB;
    @FXML
    private ComboBox subject2CB;
    @FXML
    private CheckBox subject1ChB;
    @FXML
    private CheckBox subject2ChB;
    @FXML
    private ComboBox quantifierCB;
    @FXML
    private ComboBox mQuantifierCB;
    @FXML
    private TreeView attributeTV;
    @FXML
    private TreeView mQualifiersTV;
    @FXML
    private TreeView mSummarizersTV;
    @FXML
    private Label summary;
    @FXML
    private Label mSummary;
    @FXML
    private Label mT1;
    @FXML
    private Label T1;
    @FXML
    private Label T2;
    @FXML
    private Label T3;
    @FXML
    private Label T4;
    @FXML
    private Label T5;
    @FXML
    private Label T6;
    @FXML
    private Label T7;
    @FXML
    private Label T8;
    @FXML
    private Label T9;
    @FXML
    private Label T10;
    @FXML
    private Label T11;
    @FXML
    private Label T;
    @FXML
    private TextField quantifierName;
    @FXML
    private ComboBox quantifierMembershipFunction;
    @FXML
    private Label quantifierParameter1;
    @FXML
    private Label quantifierParameter2;
    @FXML
    private Label quantifierParameter3;
    @FXML
    private Label quantifierParameter4;
    @FXML
    private TextField quantifierParameter1TF;
    @FXML
    private TextField quantifierParameter2TF;
    @FXML
    private TextField quantifierParameter3TF;
    @FXML
    private TextField quantifierParameter4TF;
    @FXML
    private CheckBox quantifierType;
    @FXML
    private Button createQuantifier;
    @FXML
    private Button generateSummariesButton;
    @FXML
    private ListView summariesListView;
    @FXML
    private Button clearChosenAttributesButton;

//    private void addQuantifiers() {
//        quantifierCB.getItems().add(initialData.getNearlyNone().getName());
//        quantifierCB.getItems().add(initialData.getAround1_4().getName());
//        quantifierCB.getItems().add(initialData.getAroundHalf().getName());
//        quantifierCB.getItems().add(initialData.getAround3_4().getName());
//        quantifierCB.getItems().add(initialData.getMost().getName());
//        quantifierCB.getItems().add(initialData.getNearlyAll().getName());
//        quantifierCB.getItems().add(initialData.getLessThan1000().getName());
//        quantifierCB.getItems().add(initialData.getAbout2000().getName());
//        quantifierCB.getItems().add(initialData.getAbout5000().getName());
//        quantifierCB.getItems().add(initialData.getAbout6000().getName());
//        quantifierCB.getItems().add(initialData.getOver8000().getName());
//        quantifierCB.getItems().add(initialData.getOver10000().getName());
//
//        quantifierCB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                String selected = (String) quantifierCB.getItems().get((Integer) t1);
//
//                for (Quantifier q: initialData.getAllQuantifiers()) {
//                    if (selected.equals(q.getName())) {
//                        quantifier = q;
//                    }
//                }
//            }
//        });
//    }

    private void addQualifiersAndSummarizers() {

        attributeTV.setRoot(new TreeItem<>("Cechy"));


        TreeItem<String> treeItem1 = new TreeItem<>("Coal");


        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        attributeTV.getRoot().getChildren().add(treeItem1);


        treeItem1 = new TreeItem<>("Oil");

        for (org.example.project2.logic.linguistics.Label label: initialData.getOilAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getOilProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getOilProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        attributeTV.getRoot().getChildren().add(treeItem1);


        treeItem1 = new TreeItem<>("Gas");


        for (org.example.project2.logic.linguistics.Label label: initialData.getGasAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getGasProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getGasProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));

        }
        attributeTV.getRoot().getChildren().add(treeItem1);

        attributeTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        attributeTV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                List<Integer> selected = attributeTV.getSelectionModel().getSelectedIndices().stream().toList();
                for (Integer id: selected) {
                    TreeItem treeItem = attributeTV.getTreeItem(id);
                    if (attributeTV.getTreeItemLevel(treeItem) == 2) {
                        for (Variable<DataEntry> var: initialData.getAllVariables()) {
                            for (org.example.project2.logic.linguistics.Label label: var.getLabels()) {
                                if(!attributes.contains(label)) {
                                    if (label.getName().equals(treeItem.getValue())) {
                                        attributes.add(label);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        clearChosenAttributesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                attributes.clear();
            }

        });

        generateSummariesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Summary> summaries = generateSummary();

                summaries.sort((o1, o2) -> {
                    int comparison = Double.compare(o2.degreeOfTruth(), o1.degreeOfTruth());
                    if (comparison == 0) {
                        return Double.compare(o2.quality(), o1.quality());
                    }
                    return comparison;
                });

                summariesListView.getItems().clear();
                if (summaries != null) {
                    for (Summary summary : summaries) {
                        summariesListView.getItems().add(summary.toStringSingle());
                    }
                }

                summariesListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        Summary selected = summaries.get((Integer) t1);
                        setMetrics(selected);
                    }
                });
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

    private void initializeNewQuantifierPane() {
        quantifierMembershipFunction.getItems().add("Trapezoidalna");
        quantifierMembershipFunction.getItems().add("Trójkątna");
        quantifierMembershipFunction.getItems().add("Gaussa");

        quantifierMembershipFunction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                switch ((String)quantifierMembershipFunction.getItems().get((Integer)t1)) {
                    case "Trapezoidalna": {
                        quantifierParameter3.setVisible(true);
                        quantifierParameter4.setVisible(true);
                        quantifierParameter3TF.setVisible(true);
                        quantifierParameter4TF.setVisible(true);
                        quantifierParameter1.setText("Lewe minimum");
                        quantifierParameter2.setText("Lewe maksimum");
                        quantifierParameter3.setText("Prawe maksimum");
                        quantifierParameter4.setText("Prawe minimum");
                        break;
                    }
                    case "Trójkątna": {
                        quantifierParameter3.setVisible(true);
                        quantifierParameter3TF.setVisible(true);
                        quantifierParameter1.setText("Lewe minimum");
                        quantifierParameter2.setText("Maksimum");
                        quantifierParameter3.setText("Prawe minimum");
                        quantifierParameter4.setVisible(false);
                        quantifierParameter4TF.setVisible(false);
                        break;
                    }
                    case "Gaussa": {
                        quantifierParameter1.setText("Średnia");
                        quantifierParameter2.setText("Odchylenie standardowe");
                        quantifierParameter3.setVisible(false);
                        quantifierParameter4.setVisible(false);
                        quantifierParameter3TF.setVisible(false);
                        quantifierParameter4TF.setVisible(false);
                        break;
                    }
                }
            }
        });

        quantifierParameter1TF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*\\.?\\d*") || t1.startsWith(".") || t1.chars().filter(ch -> ch == '.').count() > 1) {
                    String corrected = t1.replaceAll("[^\\d.]", "");
                    corrected = corrected.replaceFirst("^\\.", "");
                    while (corrected.chars().filter(ch -> ch == '.').count() > 1) {
                        corrected = corrected.replaceFirst("\\.(?=.*\\.)", "");
                    }
                    quantifierParameter1TF.setText(corrected);
                }
            }
        });
        quantifierParameter2TF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*\\.?\\d*") || t1.startsWith(".") || t1.chars().filter(ch -> ch == '.').count() > 1) {
                    String corrected = t1.replaceAll("[^\\d.]", "");
                    corrected = corrected.replaceFirst("^\\.", "");
                    while (corrected.chars().filter(ch -> ch == '.').count() > 1) {
                        corrected = corrected.replaceFirst("\\.(?=.*\\.)", "");
                    }
                    quantifierParameter2TF.setText(corrected);
                }
            }
        });
        quantifierParameter3TF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*\\.?\\d*") || t1.startsWith(".") || t1.chars().filter(ch -> ch == '.').count() > 1) {
                    String corrected = t1.replaceAll("[^\\d.]", "");
                    corrected = corrected.replaceFirst("^\\.", "");
                    while (corrected.chars().filter(ch -> ch == '.').count() > 1) {
                        corrected = corrected.replaceFirst("\\.(?=.*\\.)", "");
                    }
                    quantifierParameter3TF.setText(corrected);
                }
            }
        });
        quantifierParameter4TF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if (!t1.matches("\\d*\\.?\\d*") || t1.startsWith(".") || t1.chars().filter(ch -> ch == '.').count() > 1) {
                    String corrected = t1.replaceAll("[^\\d.]", "");
                    corrected = corrected.replaceFirst("^\\.", "");
                    while (corrected.chars().filter(ch -> ch == '.').count() > 1) {
                        corrected = corrected.replaceFirst("\\.(?=.*\\.)", "");
                    }
                    quantifierParameter4TF.setText(corrected);
                }
            }
        });

        createQuantifier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createNewQuantifier();
            }
        });
    }

    private List<Summary> generateSummary() {

        if (attributes.size() > 0) {
            CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
            List<DataEntry> dataEntries = csvReader.readData();
            List<Double> weights = new ArrayList<>();
            weights.add(0.7);
            for (int i = 0; i < 10; i++) {
                weights.add(0.03);
            }
            List<Summary> summaries = new ArrayList<>();
            for(int j = 0; j < initialData.getAllQuantifiers().size(); j++) {
                Quantifier quantifier = initialData.getAllQuantifiers().get(j);

                Set<List<org.example.project2.logic.linguistics.Label>> combinations = generateCombinations(attributes);
                //first type
                for (List<org.example.project2.logic.linguistics.Label> combination : combinations) {
                    Summary summary = new Summary(quantifier, null, dataEntries, combination, weights);
                    summaries.add(summary);
                }

                //second type

                for (List<org.example.project2.logic.linguistics.Label> qualifiers : combinations) {
                    List<org.example.project2.logic.linguistics.Label> summarizers = new ArrayList<>(attributes);
                    summarizers.removeAll(qualifiers);
                    Set<List<org.example.project2.logic.linguistics.Label>> secondCombinations = generateCombinations(summarizers);

                    for (List<org.example.project2.logic.linguistics.Label> secondCombination : secondCombinations) {
                        if (secondCombination.isEmpty()) continue;

                        Summary summary2 = new Summary(quantifier, qualifiers, dataEntries, secondCombination, weights);
                        summaries.add(summary2);
                    }
                }

            }
            return summaries;
        }

        return null;
    }

    private Set<List<org.example.project2.logic.linguistics.Label>> generateCombinations(List<org.example.project2.logic.linguistics.Label> attributes) {
        Set<List<org.example.project2.logic.linguistics.Label>> combinations = new HashSet<>();
        int n = attributes.size();

        for (int i = 1; i < (1 << n); i++) {
            List<org.example.project2.logic.linguistics.Label> combination = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    combination.add(attributes.get(j));
                }
            }
            combinations.add(new ArrayList<>(combination));
        }

        return combinations;
    }

    private void createNewQuantifier() {
        String membershipFunction = (String) quantifierMembershipFunction.getItems().get(quantifierMembershipFunction.getSelectionModel().getSelectedIndex());
        Quantifier newQuantifier;
        name = quantifierName.getText();
        isAbsolute = quantifierType.isSelected();
        switch (membershipFunction) {
            case "Trapezoidalna": {
                a = Double.parseDouble(quantifierParameter1TF.getText());
                b = Double.parseDouble(quantifierParameter2TF.getText());
                c = Double.parseDouble(quantifierParameter3TF.getText());
                d = Double.parseDouble(quantifierParameter4TF.getText());
                newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new TrapezoidalFunction(a, b, c, d, a, d)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                break;
            }
            case "Trójkątna": {
                a = Double.parseDouble(quantifierParameter1TF.getText());
                b = Double.parseDouble(quantifierParameter2TF.getText());
                c = Double.parseDouble(quantifierParameter3TF.getText());
                newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new TriangularFunction(a, b, c, a, d)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                break;
            }
            case "Gaussa": {
                a = Double.parseDouble(quantifierParameter1TF.getText());
                b = Double.parseDouble(quantifierParameter2TF.getText());
                newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new GaussianFunction(a, b)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + membershipFunction);
        }

        quantifierCB.getItems().add(newQuantifier.getName());
        initialData.addQuantifier(newQuantifier);
    }


    private void generateMultiSubjectSummary() {

    }


    private void initializeMultiSubjectPane() {
        subject1CB.getItems().add(ContinentsEnum.EUROPE);
        subject1CB.getItems().add(ContinentsEnum.ASIA);
        subject1CB.getItems().add(ContinentsEnum.AFRICA);
        subject1CB.getItems().add(ContinentsEnum.NORTH_AMERICA);
        subject1CB.getItems().add(ContinentsEnum.SOUTH_AMERICA);
        subject1CB.getItems().add(ContinentsEnum.OCEANIA);

        subject2CB.getItems().add(ContinentsEnum.EUROPE);
        subject2CB.getItems().add(ContinentsEnum.ASIA);
        subject2CB.getItems().add(ContinentsEnum.AFRICA);
        subject2CB.getItems().add(ContinentsEnum.NORTH_AMERICA);
        subject2CB.getItems().add(ContinentsEnum.SOUTH_AMERICA);
        subject2CB.getItems().add(ContinentsEnum.OCEANIA);

        mQuantifierCB.getItems().add(initialData.getNearlyNone().getName());
        mQuantifierCB.getItems().add(initialData.getAround1_4().getName());
        mQuantifierCB.getItems().add(initialData.getAroundHalf().getName());
        mQuantifierCB.getItems().add(initialData.getAround3_4().getName());
        mQuantifierCB.getItems().add(initialData.getMost().getName());
        mQuantifierCB.getItems().add(initialData.getNearlyAll().getName());


        mQualifiersTV.setRoot(new TreeItem<>("Kwalifikatory"));
        mSummarizersTV.setRoot(new TreeItem<>("Summaryzatory"));

        TreeItem<String> treeItem1 = new TreeItem<>("Coal");
        TreeItem<String> treeItem2 = new TreeItem<>("Coal");

        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getCoalProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        mQualifiersTV.getRoot().getChildren().add(treeItem1);
        mSummarizersTV.getRoot().getChildren().add(treeItem2);

        treeItem1 = new TreeItem<>("Oil");
        treeItem2 = new TreeItem<>("Oil");

        for (org.example.project2.logic.linguistics.Label label: initialData.getOilAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getOilProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getOilProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        mQualifiersTV.getRoot().getChildren().add(treeItem1);
        mSummarizersTV.getRoot().getChildren().add(treeItem2);

        treeItem1 = new TreeItem<>("Gas");
        treeItem2 = new TreeItem<>("Gas");

        for (org.example.project2.logic.linguistics.Label label: initialData.getGasAnnChangeProdTwh().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getGasProdPerCapita().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        for (org.example.project2.logic.linguistics.Label label: initialData.getGasProd().getLabels()) {
            treeItem1.getChildren().add(new TreeItem<>(label.getName()));
            treeItem2.getChildren().add(new TreeItem<>(label.getName()));
        }
        mQualifiersTV.getRoot().getChildren().add(treeItem1);
        mSummarizersTV.getRoot().getChildren().add(treeItem2);

        mQualifiersTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mQualifiersTV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                List<Integer> selected = mQualifiersTV.getSelectionModel().getSelectedIndices().stream().toList();
                qualifiers.clear();

                for (Integer id: selected) {
                    TreeItem treeItem = mQualifiersTV.getTreeItem(id);
                    if (mQualifiersTV.getTreeItemLevel(treeItem) == 2) {
                        for (Variable<DataEntry> var: initialData.getAllVariables()) {
                            for (org.example.project2.logic.linguistics.Label label: var.getLabels()) {
                                if (label.getName().equals(treeItem.getValue())) {
                                    qualifiers.add(label);
                                }
                            }
                        }
                    }
                }
                generateMultiSubjectSummary();
            }
        });

        mSummarizersTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        mSummarizersTV.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                List<Integer> selected = mSummarizersTV.getSelectionModel().getSelectedIndices().stream().toList();
                attributes.clear();

                for (Integer id: selected) {
                    TreeItem treeItem = mSummarizersTV.getTreeItem(id);
                    if (mSummarizersTV.getTreeItemLevel(treeItem) == 2) {
                        for (Variable<DataEntry> var: initialData.getAllVariables()) {
                            for (org.example.project2.logic.linguistics.Label label: var.getLabels()) {
                                if (label.getName().equals(treeItem.getValue())) {
                                    attributes.add(label);
                                }
                            }
                        }
                    }
                }
                generateMultiSubjectSummary();
            }
        });
    }

    public void initialize() {
//        addQuantifiers();
        addQualifiersAndSummarizers();
        initializeNewQuantifierPane();
        initializeMultiSubjectPane();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 500);
        stage.setTitle("Project 2");
        stage.setScene(scene);
        stage.show();
    }
}
