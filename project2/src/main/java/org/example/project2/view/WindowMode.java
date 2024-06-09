package org.example.project2.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.stage.Stage;
import org.example.project2.Initialization;
import org.example.project2.db.CsvReader;
import org.example.project2.enums.ContinentsEnum;
import org.example.project2.enums.VariablesEnum;
import org.example.project2.logic.functions.GaussianFunction;
import org.example.project2.logic.functions.TrapezoidalFunction;
import org.example.project2.logic.functions.TriangularFunction;
import org.example.project2.logic.linguistics.*;
import org.example.project2.logic.sets.ClassicSet;
import org.example.project2.logic.sets.FuzzySet;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WindowMode extends Application {


    String subject1;
    String subject2;
    List<org.example.project2.logic.linguistics.Label> attributes = new ArrayList<>();
    List<org.example.project2.logic.linguistics.Label> qualifiers = new ArrayList<>();

    List<Summary> summaries = new ArrayList<>();
    List<BooleanProperty> selectionState = new ArrayList<>();
    List<Summary> filteredSummaries = new ArrayList<>();
    Initialization initialData = new Initialization();
    Double a;
    Double b;
    Double c;
    Double d;
    String name;
    boolean isAbsolute;
    List<Double> weights = new ArrayList<>();
    ToggleGroup filterGroup;

    @FXML private ComboBox subject1CB;
    @FXML private ComboBox subject2CB;
    @FXML private TreeView attributeTV;
    @FXML private Label T1;
    @FXML private Label T2;
    @FXML private Label T3;
    @FXML private Label T4;
    @FXML private Label T5;
    @FXML private Label T6;
    @FXML private Label T7;
    @FXML private Label T8;
    @FXML private Label T9;
    @FXML private Label T10;
    @FXML private Label T11;
    @FXML private Label T;
    @FXML private TextField w1;
    @FXML private TextField w2;
    @FXML private TextField w3;
    @FXML private TextField w4;
    @FXML private TextField w5;
    @FXML private TextField w6;
    @FXML private TextField w7;
    @FXML private TextField w8;
    @FXML private TextField w9;
    @FXML private TextField w10;
    @FXML private TextField w11;
    @FXML private TextField quantifierName;
    @FXML private ComboBox quantifierMembershipFunction;
    @FXML private ComboBox linguisticVariableComboBox;
    @FXML private Label quantifierParameter1;
    @FXML private Label quantifierParameter2;
    @FXML private Label quantifierParameter3;
    @FXML private Label quantifierParameter4;
    @FXML private Label begin;
    @FXML private Label end;
    @FXML private TextField quantifierParameter1TF;
    @FXML private TextField quantifierParameter2TF;
    @FXML private TextField quantifierParameter3TF;
    @FXML private TextField quantifierParameter4TF;
    @FXML private CheckBox quantifierType;
    @FXML private Button createQuantifier;
    @FXML private Button generateSummariesButton;
    @FXML private ListView summariesListView;
    @FXML private Button clearChosenAttributesButton;
    @FXML private Button clearListView;
    @FXML private Button saveAllSummariesBtn;
    @FXML RadioButton allButton;
    @FXML RadioButton singleButton;
    @FXML RadioButton multiButton;
    @FXML RadioButton quantifierButton;
    @FXML RadioButton labelButton;
    @FXML private Button saveChosenSummariesBtn;


    private void addQualifiersAndSummarizers() {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("Cechy");
        attributeTV.setRoot(root);

        CheckBoxTreeItem<String> treeItem1 = new CheckBoxTreeItem<>("Coal");
        addLabelsToTreeItem(treeItem1, initialData.getCoalAnnChangeProdTwh().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getCoalProdPerCapita().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getCoalProd().getLabels());
        root.getChildren().add(treeItem1);

        treeItem1 = new CheckBoxTreeItem<>("Oil");
        addLabelsToTreeItem(treeItem1, initialData.getOilAnnChangeProdTwh().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getOilProdPerCapita().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getOilProd().getLabels());
        root.getChildren().add(treeItem1);

        treeItem1 = new CheckBoxTreeItem<>("Gas");
        addLabelsToTreeItem(treeItem1, initialData.getGasAnnChangeProdTwh().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getGasProdPerCapita().getLabels());
        addLabelsToTreeItem(treeItem1, initialData.getGasProd().getLabels());
        root.getChildren().add(treeItem1);

        attributeTV.setCellFactory(CheckBoxTreeCell.forTreeView());

        attributeTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        filterGroup = new ToggleGroup();
        allButton.setToggleGroup(filterGroup);
        singleButton.setToggleGroup(filterGroup);
        multiButton.setToggleGroup(filterGroup);
        allButton.setSelected(true); // Default selection

        clearChosenAttributesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                attributes.clear();
                uncheckAll(root);
            }
        });

        clearListView.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(() -> {
                    summariesListView.getItems().clear();
                    summariesListView.getSelectionModel().clearSelection();
                    summaries.clear();
                });
            }
        });

        saveAllSummariesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                saveToTxt(summaries);
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Podsumowania zostały zapisane do pliku summaries.txt");
            }
        });

        saveChosenSummariesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                List<Summary> selectedSummaries = new ArrayList<>();
                for (int i = 0; i < summaries.size(); i++) {
                    if (selectionState.get(i).get()) {
                        selectedSummaries.add(summaries.get(i));
                    }
                }

                saveToTxt(selectedSummaries);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Selected summaries have been saved.");
            }
        });


        generateSummariesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.getLogger("INFO").log(System.Logger.Level.INFO, "Generating summaries...");

                generateSummary();

                summariesListView.getItems().clear();

                summaries.sort((o1, o2) -> {
                    Double t1 = o1.getDegreeOfTruthToSort();
                    Double t2 = o2.getDegreeOfTruthToSort();
                    if (t1.isNaN() && t2.isNaN()) {
                        return 0;
                    } else if (t1.isNaN()) {
                        return 1;
                    } else if (t2.isNaN()) {
                        return -1;
                    } else {
                        return Double.compare(t2, t1);
                    }
                });

                updateSummariesListView(summaries, filterGroup.getSelectedToggle());

            }
        });

        filterGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldToggle, Toggle newToggle) {
                if (newToggle != null) {
                    List<Summary> currentSummaries = generateSummary();
                    updateSummariesListView(currentSummaries, newToggle);
                }
            }
        });

    }

    public void saveToTxt(List<Summary> summaries) {
        try (FileWriter writer = new FileWriter("summaries.txt")) {
            writer.append("Summary, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T\n");
            for (Summary summary : summaries) {
                writer.append(summary.toString())
                        .append(", ")
                        .append(String.valueOf(Math.round(summary.getDegreeOfTruthToSort() * 100.0) / 100.0));

                if (summary.getForm() == 0) {
                    writer.append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfCovering() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfAppropriateness() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.lengthOfSummary() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfQuantifierImprecision() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfQuantifierCardinality() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfSummarizerCardinality() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfQualifierImprecision() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.degreeOfQualifierCardinality() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.lengthOfQualifier() * 100.0) / 100.0))
                            .append(", ")
                            .append(String.valueOf(Math.round(summary.quality() * 100.0) / 100.0));

                } else {
                    writer.append(", ~, ~, ~, ~, ~, ~, ~, ~, ~, ~, ~");
                }
                writer.append("\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLabelsToTreeItem(CheckBoxTreeItem<String> parentItem, List<org.example.project2.logic.linguistics.Label> labels) {
        for (org.example.project2.logic.linguistics.Label label : labels) {
            CheckBoxTreeItem<String> childItem = new CheckBoxTreeItem<>(label.getName());
            childItem.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        addLabelToAttributes(label);
                    } else {
                        removeLabelFromAttributes(label);
                    }
                }
            });
            parentItem.getChildren().add(childItem);
        }
    }

    private void addLabelToAttributes(org.example.project2.logic.linguistics.Label label) {
        if (!attributes.contains(label)) {
            attributes.add(label);
        }
    }

    private void removeLabelFromAttributes(org.example.project2.logic.linguistics.Label label) {
        attributes.remove(label);
    }

    private void uncheckAll(CheckBoxTreeItem<String> item) {
        item.setSelected(false);
        for (TreeItem<String> child : item.getChildren()) {
            if (child instanceof CheckBoxTreeItem) {
                uncheckAll((CheckBoxTreeItem<String>) child);
            }
        }
    }

    private void updateSummariesListView(List<Summary> summaries, Toggle selectedToggle) {
        summariesListView.getSelectionModel().clearSelection();
        summariesListView.getItems().clear();
        filteredSummaries.clear();
        if (summaries != null) {
            for (Summary summary : summaries) {
                if (selectedToggle != null) {
                    RadioButton selectedRadioButton = (RadioButton) selectedToggle;
                    if (selectedRadioButton.getText().equals("Jednopodmiotowe") && summary.getObjects2() == null) {
                        filteredSummaries.add(summary);
                    } else if (selectedRadioButton.getText().equals("Wielopodmiotowe") && summary.getObjects2() != null) {
                        filteredSummaries.add(summary);
                    } else if (selectedRadioButton.getText().equals("Wszystkie")) {
                        filteredSummaries.add(summary);
                    }
                }
            }
        }
        for (Summary summary: filteredSummaries) {
            CheckBox checkBox = new CheckBox(summary.toString());
            BooleanProperty isSelected = new SimpleBooleanProperty(false);
            checkBox.selectedProperty().bindBidirectional(isSelected);
            selectionState.add(isSelected);
            summariesListView.getItems().add(checkBox);
//            summariesListView.getItems().add(summary.toString());
        }
    }

    private void setMetrics(Summary summary) {
        T1.setText(STR."T1: \{Math.round(summary.getDegreeOfTruthToSort() * 100.0) / 100.0}");
        if(summary.getForm() == 0){
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
        } else {
            T2.setText("T2: ~");
            T3.setText("T3: ~");
            T4.setText("T4: ~");
            T5.setText("T5: ~");
            T6.setText("T6: ~");
            T7.setText("T7: ~");
            T8.setText("T8: ~");
            T9.setText("T9: ~");
            T10.setText("T10: ~");
            T11.setText("T11: ~");
            T.setText("T: ~");
        }
    }

    private void initializeNewQuantifierPane() {
        quantifierMembershipFunction.getItems().add("Trapezoidalna");
        quantifierMembershipFunction.getItems().add("Trójkątna");
        quantifierMembershipFunction.getItems().add("Gaussa");

        for (VariablesEnum v: VariablesEnum.values()) {
            linguisticVariableComboBox.getItems().add(v.getName());
        }

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
        summaries.clear();

        if (attributes.size() > 0) {
            CsvReader csvReader = new CsvReader("src/main/java/org/example/project2/db/db.csv");
            List<DataEntry> dataEntries = csvReader.readData();
            List<DataEntry> subject1Data = dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(subject1)).toList();
            List<DataEntry> subject2Data = dataEntries.stream().filter(dataEntry -> dataEntry.getContinent().equals(subject2)).toList();
            Set<List<org.example.project2.logic.linguistics.Label>> combinations = generateCombinations(attributes);

            for(int j = 0; j < initialData.getAllQuantifiers().size(); j++) {
                Quantifier quantifier = initialData.getAllQuantifiers().get(j);

                //first type one subj
                for (List<org.example.project2.logic.linguistics.Label> combination : combinations) {
                    Summary summary = new Summary(quantifier, null, dataEntries, combination, weights, " data entries");
                    summaries.add(summary);

                    //first type with subjects chosen
                    Summary summary1 = new Summary(quantifier, null, subject1Data, combination, weights, subject1);
                    summaries.add(summary1);
                    Summary summary2 = new Summary(quantifier, null, subject2Data, combination, weights, subject2);
                    summaries.add(summary2);
                }

                //second type one subj

                for (List<org.example.project2.logic.linguistics.Label> qualifiers : combinations) {
                    List<org.example.project2.logic.linguistics.Label> summarizers = new ArrayList<>(attributes);
                    summarizers.removeAll(qualifiers);
                    Set<List<org.example.project2.logic.linguistics.Label>> secondCombinations = generateCombinations(summarizers);
                    for (List<org.example.project2.logic.linguistics.Label> secondCombination : secondCombinations) {
                        if (secondCombination.isEmpty()) continue;

                        Summary summary2 = new Summary(quantifier, qualifiers, dataEntries, secondCombination, weights, " data entries");
                        summaries.add(summary2);

                        //with chosen subjects
                        Summary summary3 = new Summary(quantifier, qualifiers, subject1Data, secondCombination, weights, subject1);
                        summaries.add(summary3);
                        Summary summary4 = new Summary(quantifier, qualifiers, subject2Data, secondCombination, weights, subject2);
                        summaries.add(summary4);
                    }
                }

                //first type multi subj
                for (List<org.example.project2.logic.linguistics.Label> combination : combinations) {
                    Summary summary1 = new Summary(quantifier, null, subject1Data, subject2Data, combination, subject1, subject2,false);
                    summaries.add(summary1);
//                    Summary summary2 = new Summary(quantifier, null, subject2Data, subject1Data, combination, subject2, subject1,false);
//                    summaries.add(summary2);
                }
                //second and third type multi subj
                for (List<org.example.project2.logic.linguistics.Label> qualifiers : combinations) {
                    List<org.example.project2.logic.linguistics.Label> summarizers = new ArrayList<>(attributes);
                    summarizers.removeAll(qualifiers);
                    Set<List<org.example.project2.logic.linguistics.Label>> secondCombinations = generateCombinations(summarizers);

                    for (List<org.example.project2.logic.linguistics.Label> secondCombination : secondCombinations) {
                        if (secondCombination.isEmpty()) continue;

                        Summary summary = new Summary(quantifier, qualifiers, subject1Data, subject2Data, secondCombination, subject1,subject2, false);
                        summaries.add(summary);
                        Summary summary2 = new Summary(quantifier, qualifiers, subject2Data, subject1Data, secondCombination, subject2,subject1, false);
                        summaries.add(summary2);
                        Summary summary3 = new Summary(quantifier, qualifiers, subject1Data, subject2Data, secondCombination, subject1,subject2, true);
                        summaries.add(summary3);
                        Summary summary4 = new Summary(quantifier, qualifiers, subject2Data, subject1Data, secondCombination, subject2,subject1, true);
                        summaries.add(summary4);
                    }
                }

                if(j == 0) {
                    //4th type multi subj
                    for (List<org.example.project2.logic.linguistics.Label> combination : combinations) {
                        Summary summary1 = new Summary(null, null, subject1Data, subject2Data, combination, subject1, subject2, false);
                        summaries.add(summary1);
                        Summary summary2 = new Summary(null, null, subject2Data, subject1Data, combination, subject2, subject1, false);
                        summaries.add(summary2);
                    }
                }

            }

            System.getLogger("INFO").log(System.Logger.Level.INFO, "Summaries generated " + summaries.size());
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
        String membershipFunction = (String) quantifierMembershipFunction.getSelectionModel().getSelectedItem();
        String name = quantifierName.getText();
        boolean isAbsolute = quantifierType.isSelected();
        double a, b, c, d;

        if (quantifierButton.isSelected()) {
            // Creating a new Quantifier
            Quantifier newQuantifier;
            switch (membershipFunction) {
                case "Trapezoidalna":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    c = Double.parseDouble(quantifierParameter3TF.getText());
                    d = Double.parseDouble(quantifierParameter4TF.getText());
                    newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new TrapezoidalFunction(a, b, c, d, a, d)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                    break;
                case "Trójkątna":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    c = Double.parseDouble(quantifierParameter3TF.getText());
                    newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new TriangularFunction(a, b, c, a, c)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                    break;
                case "Gaussa":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    newQuantifier = new Quantifier(name, new FuzzySet(new ClassicSet(0, isAbsolute ? 11067 : 1), new GaussianFunction(a, b)), isAbsolute ? QuantifierType.ABSOLUTE : QuantifierType.RELATIVE);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + membershipFunction);
            }
            initialData.addQuantifier(newQuantifier);
        } else if (labelButton.isSelected()) {
            String linguisticVariable = (String) linguisticVariableComboBox.getSelectionModel().getSelectedItem();
            Variable var = initialData.getAllVariables().stream().filter(
                    v -> v.getName().equals(linguisticVariable)
            ).toList().getFirst();
            org.example.project2.logic.linguistics.Label newLabel;
            org.example.project2.logic.linguistics.Label l = (org.example.project2.logic.linguistics.Label) var.getLabels().getFirst();
            double beg = l.getFuzzySet().getUniverseOfDiscourse().getBegin();
            double e = l.getFuzzySet().getUniverseOfDiscourse().getEnd();

            switch (membershipFunction) {
                case "Trapezoidalna":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    c = Double.parseDouble(quantifierParameter3TF.getText());
                    d = Double.parseDouble(quantifierParameter4TF.getText());
                    newLabel = new org.example.project2.logic.linguistics.Label(name, new FuzzySet(new ClassicSet(beg, e), new TrapezoidalFunction(a, b, c, d, a, d)), linguisticVariable);
                    break;
                case "Trójkątna":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    c = Double.parseDouble(quantifierParameter3TF.getText());
                    newLabel = new org.example.project2.logic.linguistics.Label(name, new FuzzySet(new ClassicSet(beg, e), new TriangularFunction(a, b, c, a, c)), linguisticVariable);
                    break;
                case "Gaussa":
                    a = Double.parseDouble(quantifierParameter1TF.getText());
                    b = Double.parseDouble(quantifierParameter2TF.getText());
                    newLabel = new org.example.project2.logic.linguistics.Label(name, new FuzzySet(new ClassicSet(beg, e), new GaussianFunction(a, b)), linguisticVariable);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + membershipFunction);
            }
            var.addLabel(newLabel);

            addQualifiersAndSummarizers();
        }
    }

    @FXML
    private void handleSave() {
        try {
            BigDecimal sum = new BigDecimal(w1.getText())
                    .add(new BigDecimal(w2.getText()))
                    .add(new BigDecimal(w3.getText()))
                    .add(new BigDecimal(w4.getText()))
                    .add(new BigDecimal(w5.getText()))
                    .add(new BigDecimal(w6.getText()))
                    .add(new BigDecimal(w7.getText()))
                    .add(new BigDecimal(w8.getText()))
                    .add(new BigDecimal(w9.getText()))
                    .add(new BigDecimal(w10.getText()))
                    .add(new BigDecimal(w11.getText()));

            BigDecimal target = new BigDecimal("1.0");

            if (sum.compareTo(target) == 0) {
                showAlert(Alert.AlertType.INFORMATION, "Sukces", "Wagi zostały zapisane poprawnie!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Błąd", "Suma wag musi wynosić dokładnie 1.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Błąd", "Wszystkie pola muszą zawierać liczby.");
        }
        this.weights = new ArrayList<>();
        this.weights.add(Double.parseDouble(w1.getText()));
        this.weights.add(Double.parseDouble(w2.getText()));
        this.weights.add(Double.parseDouble(w3.getText()));
        this.weights.add(Double.parseDouble(w4.getText()));
        this.weights.add(Double.parseDouble(w5.getText()));
        this.weights.add(Double.parseDouble(w6.getText()));
        this.weights.add(Double.parseDouble(w7.getText()));
        this.weights.add(Double.parseDouble(w8.getText()));
        this.weights.add(Double.parseDouble(w9.getText()));
        this.weights.add(Double.parseDouble(w10.getText()));
        this.weights.add(Double.parseDouble(w11.getText()));
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initializeMultiSubject() {
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

        subject1CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ContinentsEnum selected = (ContinentsEnum) subject1CB.getItems().get((Integer) t1);
                for (ContinentsEnum continent: ContinentsEnum.values()) {
                    if (selected.getName().equals(continent.getName())) {
                        subject1 = continent.getName();
                    }
                }
            }
        });

        subject2CB.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                ContinentsEnum selected = (ContinentsEnum) subject2CB.getItems().get((Integer) t1);
                for (ContinentsEnum continent: ContinentsEnum.values()) {
                    if (selected.getName().equals(continent.getName())) {
                        subject2 = continent.getName();
                    }
                }
            }
        });


    }

    public void initialize() {
        initializeMultiSubject();
        addQualifiersAndSummarizers();
        initializeNewQuantifierPane();

        T1.setOnMouseClicked(event -> sortByDegreeOfTruthToSort());
        T2.setOnMouseClicked(event -> sortByDegreeOfImprecision());
        T3.setOnMouseClicked(event -> sortByDegreeOfCovering());
        T4.setOnMouseClicked(event -> sortByDegreeOfAppropriateness());
        T5.setOnMouseClicked(event -> sortByLengthOfSummary());
        T6.setOnMouseClicked(event -> sortByDegreeOfQuantifierImprecision());
        T7.setOnMouseClicked(event -> sortByDegreeOfQuantifierCardinality());
        T8.setOnMouseClicked(event -> sortByDegreeOfSummarizerCardinality());
        T9.setOnMouseClicked(event -> sortByDegreeOfQualifierImprecision());
        T10.setOnMouseClicked(event -> sortByDegreeOfQualifierCardinality());
        T11.setOnMouseClicked(event -> sortByLengthOfQualifier());
        T.setOnMouseClicked(event -> sortByQuality());
        sortByDegreeOfTruthToSort();
        summariesListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if ((Integer) t1 >= 0) {
                    Summary selected = filteredSummaries.get((Integer) t1);
                    setMetrics(selected);
                }
            }
        });

        linguisticVariableComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                for (Variable v: initialData.getAllVariables()) {
                    if (v.getName().equals((String) linguisticVariableComboBox.getSelectionModel().getSelectedItem())) {
                        org.example.project2.logic.linguistics.Label l = (org.example.project2.logic.linguistics.Label)v.getLabels().getFirst();
                        begin.setText(String.valueOf(l.getFuzzySet().getUniverseOfDiscourse().getBegin()));
                        end.setText(String.valueOf(l.getFuzzySet().getUniverseOfDiscourse().getEnd()));
                    }
                }
            }
        });

        ToggleGroup radioGroup = new ToggleGroup();
        quantifierButton.setToggleGroup(radioGroup);
        labelButton.setToggleGroup(radioGroup);
        quantifierButton.setSelected(true);
        quantifierType.setDisable(false);
        linguisticVariableComboBox.setDisable(true);
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                if (radioGroup.getSelectedToggle() == quantifierButton) {
                    quantifierType.setDisable(false);
                    linguisticVariableComboBox.setDisable(true);
                } else if (radioGroup.getSelectedToggle() == labelButton) {
                    quantifierType.setDisable(true);
                    linguisticVariableComboBox.setDisable(false);
                }
            }
        });

        quantifierType.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (quantifierType.isSelected()) {
                    begin.setText("0");
                    end.setText(String.valueOf(initialData.getAbout2000().getFuzzySet().getUniverseOfDiscourse().getEnd()));
                } else {
                    begin.setText("0");
                    end.setText("1");
                }
            }
        });

        this.weights = new ArrayList<>();
        this.weights.add(Double.parseDouble(w1.getText()));
        this.weights.add(Double.parseDouble(w2.getText()));
        this.weights.add(Double.parseDouble(w3.getText()));
        this.weights.add(Double.parseDouble(w4.getText()));
        this.weights.add(Double.parseDouble(w5.getText()));
        this.weights.add(Double.parseDouble(w6.getText()));
        this.weights.add(Double.parseDouble(w7.getText()));
        this.weights.add(Double.parseDouble(w8.getText()));
        this.weights.add(Double.parseDouble(w9.getText()));
        this.weights.add(Double.parseDouble(w10.getText()));
        this.weights.add(Double.parseDouble(w11.getText()));

    }

    private void sortByDegreeOfTruthToSort() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.getDegreeOfTruthToSort();
            Double t2 = o2.getDegreeOfTruthToSort();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfImprecision() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfImprecision();
            Double t2 = o2.degreeOfImprecision();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfCovering() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfCovering();
            Double t2 = o2.degreeOfCovering();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfAppropriateness() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfAppropriateness();
            Double t2 = o2.degreeOfAppropriateness();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByLengthOfSummary() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.lengthOfSummary();
            Double t2 = o2.lengthOfSummary();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfQuantifierImprecision() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfQuantifierImprecision();
            Double t2 = o2.degreeOfQuantifierImprecision();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfQuantifierCardinality() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfQuantifierCardinality();
            Double t2 = o2.degreeOfQuantifierCardinality();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfSummarizerCardinality() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfSummarizerCardinality();
            Double t2 = o2.degreeOfSummarizerCardinality();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfQualifierImprecision() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfQualifierImprecision();
            Double t2 = o2.degreeOfQualifierImprecision();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByDegreeOfQualifierCardinality() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.degreeOfQualifierCardinality();
            Double t2 = o2.degreeOfQualifierCardinality();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByLengthOfQualifier() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.lengthOfQualifier();
            Double t2 = o2.lengthOfQualifier();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private void sortByQuality() {
        filteredSummaries.sort((o1, o2) -> {
            Double t1 = o1.quality();
            Double t2 = o2.quality();
            return compareDegrees(t1, t2);
        });
        List<Summary> copiedFilteredSummaries = new ArrayList<>(filteredSummaries);
        updateSummariesListView(copiedFilteredSummaries, filterGroup.getSelectedToggle());
    }

    private int compareDegrees(Double t1, Double t2) {
        if (t1.isNaN() && t2.isNaN()) {
            return 0;
        } else if (t1.isNaN()) {
            return 1;
        } else if (t2.isNaN()) {
            return -1;
        } else {
            return Double.compare(t2, t1);
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Project 2");
        stage.setScene(scene);
        stage.show();
    }
}
