package com.example.grocerystore;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;

//Login Application Class
public class Login extends Application {
        //Class containing userName and Password validation
        public boolean isValidUser(String enterUsername, String enterPassword) {
            IceCreamStore groceryStore = new IceCreamStore();
            return enterUsername.equals(groceryStore.getUsername()) && enterPassword.equals(groceryStore.getPassword());
        }

        public static void main(String[] args) {
            launch(args);
        }
        //The stage which is the UI for the login screen
        @Override
        public void start(Stage primaryStage) throws Exception {
            GridPane grid = new GridPane();
            //Centers the login text box prompt
            grid.setAlignment(javafx.geometry.Pos.CENTER);

            //Login screen password and username prompt
            //Username
            Label usernameLabel = new Label("Username:");
            grid.add(usernameLabel, 0, 1);

            TextField usernameField = new TextField();
            grid.add(usernameField, 1, 1);

            //Password
            Label passwordLabel = new Label("Password:");
            grid.add(passwordLabel, 0, 2);

            PasswordField passwordField = new PasswordField();
            grid.add(passwordField, 1, 2);

            //Login button
            Button loginButton = new Button("Login");
            grid.add(loginButton, 1, 3);

            final Text actionTarget = new Text();
            grid.add(actionTarget, 1, 6);

            //Set action which initializes both username and password fields in order to get checked in the if statement
            loginButton.setOnAction(e -> {
                String enterUsername = usernameField.getText();
                String enterPassword = passwordField.getText();
                // if statement that uses the method in order to check which message it has to display
                if (isValidUser(enterUsername, enterPassword)) {
                    actionTarget.setFill(Color.GREEN);
                    actionTarget.setText("Login successful");
                }
                    else {
                        actionTarget.setFill(Color.RED);
                        actionTarget.setText("Invalid username or password");
                    }
            });
            //Shows the scene
            Scene scene = new Scene(grid, 400, 300);
            primaryStage.setScene(scene);
            primaryStage.show();

        }

    }

