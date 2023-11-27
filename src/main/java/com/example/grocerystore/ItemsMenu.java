package com.example.grocerystore;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenu extends Application {

    private IceCreamStore iceCreamStore;
    private String[] itemNames;
    private List<Spinner<Integer>> quantitySpinners;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        iceCreamStore = new IceCreamStore();
        itemNames = iceCreamStore.getItemNames();
        quantitySpinners = new ArrayList<>();

        primaryStage.setTitle("Ice Cream Store App");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(15);
        grid.setHgap(5);

        // Display menu
        displayMenu(grid);


        // Button for calculations
        Button calculateButton = new Button("Calculate");
        grid.add(calculateButton, 2, 4);

        // Result display area
        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        grid.add(resultTextArea, 0, 4, 2, 1);

        // Event handling for the calculate button
        calculateButton.setOnAction(e -> calculateAndDisplay(this.itemNames, resultTextArea));

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayMenu(GridPane grid) {
        // displaying controls and labels for each individual item
        for (int i = 0; i < this.itemNames.length; i++) {
            grid.add(new Label(this.itemNames[i]), 0, i);
            grid.add(new Label("$" + iceCreamStore.getPrice(this.itemNames[i])), 1, i);
            Spinner<Integer> quantitySpinner = new Spinner<>(0, 99, 0); // Min, Max, Initial Value
            grid.add(quantitySpinner, 2, i);

            // Add the spinner to the list
            quantitySpinners.add(quantitySpinner);
        }
    }

    private void calculateAndDisplay(String[] itemNames, TextArea resultTextArea) {
        // Add user validation here
        ArrayList<Integer> quantitiesInt = new ArrayList<>();

        // Iterate over the list of Spinners to get their values
        for (Spinner<Integer> spinner : quantitySpinners) {
            quantitiesInt.add(spinner.getValue());
        }

        double cost = iceCreamStore.calculateCost(itemNames, quantitiesInt);
        double tax = iceCreamStore.calculateTaxes(cost);
        double totalCost = iceCreamStore.calculateTotalCost(cost, tax);

        // Display results
        resultTextArea.setText("Cost before tax: $" + cost + "\nTax: $" + tax + "\nTotal Cost: $" + totalCost);
    }
}