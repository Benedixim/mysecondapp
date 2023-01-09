package com.example.mysecondapp;

import com.example.mysecondapp.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.sql.ResultSet;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField LoginField;

    @FXML
    private javafx.scene.control.PasswordField PasswordField;

    @FXML
    private Button SignUpButton;

    @FXML
    void initialize() {

        LoginButton.setOnAction(event -> {
            String loginText = LoginField.getText().trim();
            String loginPassword = PasswordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else
                System.out.println("Ошибка! Поля Логин и Пароль пустые!");
        });

        SignUpButton.setOnAction(actionEvent -> {
            openNewScene("signup.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result  = dbHandler.getUser(user);

        int counter = 0;

        while(true)
        {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }
        if(counter >=1)
        {
            openNewScene("app.fxml");
            System.out.println("Успешно вошли!");
        }
        else {
            Shake userLoginAnim = new Shake(LoginField);
            Shake userPassAnim = new Shake(PasswordField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
        }
    }


    public void openNewScene(String window)
    {
        SignUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage  stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}