package com.example.mysecondapp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.mysecondapp.Network.StartServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ServerController {

    private StartServer server=new StartServer();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label status;

    @FXML
    private TextArea textArea;

    @FXML
    void clean(MouseEvent event) {

    }

    @FXML
    void save(MouseEvent event) {

    }

    @FXML
    void startserver(ActionEvent event) {
        server.startServer();
        status.setText("ON");
        appendEvent("Server Started\n");

    }

    @FXML
    void stopserver(ActionEvent event) {
        server.stopServer();
        status.setText("OFF");

        appendEvent("Server Stopped\n");

    }

    @FXML
    void initialize() {
        appendEvent("Server Started\n");

        status.setText("ON");
    }

    public void close(MouseEvent mouseEvent) {
        openNewScene("app.fxml", "none");
    }

    public void openNewScene(String window, String style)
    {
        status.getScene().getWindow().hide();

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
        if(style.equals("UTILITY") )        stage.initStyle(StageStyle.UTILITY);//всплывающее окно с одним крестиком
        if(style.equals("TRANSPARENT") )        stage.initStyle(StageStyle.TRANSPARENT);//вообще без значков сверху
        stage.showAndWait();
    }

    public void appendEvent(String string) {
        //messages.add(string);
        textArea.appendText(string);
    }

    public ArrayList<String> messages = new ArrayList<String>();

    public void printMessages()
    {
        for (String item : messages)
            textArea.appendText(item);
    }
}

