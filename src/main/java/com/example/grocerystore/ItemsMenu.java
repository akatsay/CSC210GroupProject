package com.example.grocerystore;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenu extends Pane {
    // Declare IceCreamStore and spinners
    private IceCreamStore iceCreamStore;
    private List<Spinner<Integer>> quantitySpinners;

    public ItemsMenu() {
        // construct IceCreamStore and spinners
        iceCreamStore = new IceCreamStore();
        quantitySpinners = new ArrayList<>();

        // Initialize itemNames here
        String[] itemNames = iceCreamStore.getItemNames();

        // Use the createItemsMenuPane method to initialize the GridPane for Items menu
        GridPane grid = createItemsMenuPane(itemNames);

        getChildren().add(grid);
    }

    // New method to initialize the GridPane for the ItemsMenu
    private GridPane createItemsMenuPane(String[] itemNames) {
        GridPane grid = new GridPane();
        // center items inside the grid
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        // set gaps between elements and padding
        grid.setVgap(15);
        grid.setHgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.getColumnConstraints().add(new ColumnConstraints(200));

        // Display menu
        displayMenu(grid, itemNames);

        // Button to trigger calculations
        Button calculateButton = new Button("Calculate");
        grid.add(calculateButton, 2, 4);

        // Result display area
        TextArea resultTextArea = new TextArea();
        resultTextArea.setEditable(false);
        grid.add(resultTextArea, 0, 4, 1, 1);

        // Event handling for the calculate button
        calculateButton.setOnAction(e -> calculateAndDisplay(itemNames, resultTextArea));
        return grid;
    }

    // Method to map through all existing items and create display interactive line for each one
    private void displayMenu(GridPane grid, String[] itemNames) {
        // displaying controls and labels for each individual item
        quantitySpinners = new ArrayList<>();
        for (int i = 0; i < itemNames.length; i++) {
            grid.add(new Label(itemNames[i]), 0, i);
            grid.add(new Label("$" + iceCreamStore.getPrice(itemNames[i])), 1, i);
            Spinner<Integer> quantitySpinner = new Spinner<>(0, 99, 0); // Min, Max, Initial Value
            grid.add(quantitySpinner, 2, i);

            // Add the spinner to the list
            quantitySpinners.add(quantitySpinner);
        }
    }

    // method that runs all the necessary calculations and displays them in resultTextArea
    private void calculateAndDisplay(String[] itemNames, TextArea resultTextArea) {
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

