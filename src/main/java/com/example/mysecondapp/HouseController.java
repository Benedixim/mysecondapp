package com.example.mysecondapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HouseController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button menu1;

    @FXML
    private Button menu2;

    @FXML
    private Button menu3;

    @FXML
    private Button menuForAdminers;

    @FXML
    private ImageView openserverpanel;

    @FXML
    void initialize() {
        menuForAdminers.setOnAction(actionEvent -> {
            openNewScene("usersTable.fxml");
        });

    }

    public void openNewScene(String window)
    {
        menuForAdminers.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public void openServerPanel(MouseEvent mouseEvent) {
        openNewScene("server.fxml");
    }
}
