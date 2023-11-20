module com.example.grocerystore {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.grocerystore to javafx.fxml;
    exports com.example.grocerystore;
}