package com.example.grocerystore;


import java.util.ArrayList;

public class IceCreamStore {
    // test
//    public static void main(String[] args) {
//        String[] itemsToBuy = {"Chocolate", "Vanilla"};
//        ArrayList<Integer> amountsToBuy = [2, 5];
//        IceCreamStore store = new IceCreamStore();
//        System.out.println(store.getUsername());
//        System.out.println(store.getPassword());
//        System.out.println(store.getPrice("Chocolate"));
//        double calculatedCost = store.calculateCost( itemsToBuy, amountsToBuy);
//        System.out.println(calculatedCost);
//        double calculatedTax = store.calculateTaxes(calculatedCost);
//        System.out.println(calculatedTax);
//        System.out.println(store.calculateTotalCost(calculatedCost, calculatedTax));
//
//    }
    // declare all variables
    private String[][] usernameAndPassword;
    private String[] itemNames;
    private double[] itemPrices;
    private double taxRate;

    public IceCreamStore() {
        // Initialize arrays and set tax rate
        usernameAndPassword = new String[][]{{"Baskin"}, {"100 Chambers"}};
        itemNames = new String[]{"Chocolate", "Mint Chip", "Vanilla", "Strawberry"};
        itemPrices = new double[]{1.99, 2.49, 3.99, 4.50};
        taxRate = 0.08;
    }

    public String getUsername() {
        return usernameAndPassword[0][0];
    }

    public String getPassword() {
        return usernameAndPassword[1][0];
    }

    public double getPrice(String itemName) {
        int index = -1;
        for (int i = 0; i < itemNames.length; i++) {
            if (itemNames[i].equals(itemName)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            return itemPrices[index];
        } else {
            return -1; // Item not found
        }
    }

    public String[] getItemNames() {
        return itemNames;
    }

    public double calculateCost(String[] itemNames, ArrayList<Integer> itemAmounts) {
        double cost = 0;
        for (int i = 0; i < itemNames.length; i++ ) {
            double price = getPrice(itemNames[i]);
            int itemAmount = itemAmounts.get(i);
            cost += price * itemAmount;
        }
        return cost;
    }

    public double calculateTaxes(double cost) {
        return cost * taxRate;
    }

    public double calculateTotalCost(double cost, double tax) {
        return cost + tax;
    }
}