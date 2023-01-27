package com.example.mysecondapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton femaleField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField locationField;

    @FXML
    private RadioButton maleField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void clean() {
        nameField.setText(null);
        lastnameField.setText(null);
        usernameField.setText(null);
        passwordField.setText(null);
        locationField.setText(null);
        maleField.setSelected(true);
    }

    @FXML
    void save(MouseEvent event) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = nameField.getText();
        String lastname = lastnameField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String location =  locationField.getText();

        String gender = "";
        if(maleField.isSelected())
            gender = "Мужской";
        else if (femaleField.isSelected())
            gender = "Женский";




        if (firstname.isEmpty() || lastname.isEmpty() || username.isEmpty() || password.isEmpty() || location.isEmpty() || gender.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            User user = new User(firstname, lastname, username, password, location, gender);
            dbHandler.signupUser(user);
            clean();

        }
    }

    @FXML
    void initialize() {

    }



}
