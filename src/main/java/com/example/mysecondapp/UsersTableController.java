package com.example.mysecondapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UsersTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<User, String> genderColumn;

    @FXML
    private TableColumn<User, Long> idColumn;

    @FXML
    private TableColumn<User, String> lastnameColumn;

    @FXML
    private TableColumn<User, String> locationColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    void close(MouseEvent event) {

    }

    @FXML
    void getAddView(MouseEvent event) {

    }

    @FXML
    void refreshTable(MouseEvent event) {

    }

    @FXML
    void initialize() {
      loaddate();
    }

    private void loaddate() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idusers"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }

}
