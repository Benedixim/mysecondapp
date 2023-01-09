package com.example.mysecondapp;

        import java.net.URL;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.CheckBox;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;

public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField LoginField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button signupButton;

    @FXML
    private TextField signupCountry;

    @FXML
    private CheckBox signupFemale;

    @FXML
    private TextField signupLastName;

    @FXML
    private CheckBox signupMale;

    @FXML
    private TextField signupName;

    @FXML
    void initialize() {


        signupButton.setOnAction(event -> {

            signUpNewUser();


        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();

        String firstname = signupName.getText();
        String lastname = signupLastName.getText();
        String username = LoginField.getText();
        String password = PasswordField.getText();
        String location =  signupCountry.getText();

        String gender = "";
        if(signupMale.isSelected())
            gender = "Мужской";
        else
            gender = "Женский";

        User user = new User(firstname, lastname, username, password, location, gender);

        dbHandler.signupUser(user);
    }

}

