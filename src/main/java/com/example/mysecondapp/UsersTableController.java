package com.example.mysecondapp;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class UsersTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> usersTable;

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
    private TableColumn<User, String> iconColumn;

    @FXML
    private TextField search;

    @FXML
    private PieChart pieGender;



    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        openNewScene("app.fxml", "none");

    }

    @FXML
    void search()
    {
        FilteredList<User> flPerson = new FilteredList<>(UserList, p -> true);//Pass the data to a filtered list
        //table.setItems(flPerson);//Set the table's items using the filtered list
        //table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        search.textProperty().addListener((obs, oldValue, newValue) -> {
            flPerson.setPredicate(user -> {
                if(newValue == null || newValue.isEmpty())
                    return true;
                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;
                else if (user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                  return true;
                } else if (user.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getPassword().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getLocation().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else return false;
                //flPerson.setPredicate(p -> p.getFirstName().toLowerCase().contains(newValue.toLowerCase().trim()));//filter table by first name


            });

            });
        SortedList<User> sortedData = new SortedList<>(flPerson);
        sortedData.comparatorProperty().bind(usersTable.comparatorProperty());
        usersTable.setItems(sortedData);
    }

    @FXML
    void getAddView(MouseEvent event) {


        openNewScene("addUser.fxml", "UTILITY");
        //refreshTable();

    }

    public void openNewScene(String window, String style)
    {

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
        if(style.equals("UTILITY") )        stage.initStyle(StageStyle.UTILITY);//всплывающее окно с одним крестиком
        if(style.equals("TRANSPARENT") )        stage.initStyle(StageStyle.TRANSPARENT);//вообще без значков сверху
        stage.showAndWait();
    }



    @FXML
    void refreshTable() {
        try {

            UserList.clear();

            DatabaseHandler dbHandler = new DatabaseHandler();
            resultSet  = dbHandler.getAllUsers();

            while (resultSet.next()){
                UserList.add(new  User(
                        resultSet.getLong("idusers"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("location"),
                        resultSet.getString("gender")));
                usersTable.setItems(UserList);
                search();
            }


        } catch (SQLException ex) {
            Logger.getLogger(UsersTableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void initialize() {
      loaddate();
      search();

    }


    ResultSet resultSet = null ;

    ObservableList<User> UserList = FXCollections.observableArrayList();

    private void loaddate() {



        refreshTable();

        //"поля класса"
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        //add cell of button edit
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFoctory = (TableColumn<User, String> param) -> {
            // make cell containing buttons
            final TableCell<User, String> cell = new TableCell<User, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {


                        Image image = new Image("file:/D:/Java/mysecondapp/src/main/assets/edit.png");
                        ImageView iv1 = new ImageView();
                        iv1.setImage(image);
                        iv1.setFitWidth(28);
                        iv1.setFitHeight(28);

                        Image delete = new Image("file:/D:/Java/mysecondapp/src/main/assets/delete-user.png");
                        ImageView del = new ImageView();
                        del.setImage(delete);
                        del.setFitWidth(28);
                        del.setFitHeight(28);


                        iv1.setOnMouseClicked((MouseEvent event) -> {
                            User user = usersTable.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("updateUser.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(UsersTableController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            UpdateUserController upUserController = loader.getController();
                            upUserController.setUpdate(true);
                            upUserController.setTextField(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(),
                                    user.getPassword(),  user.getLocation(), user.getGender());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();



                        });

                        del.setOnMouseClicked((MouseEvent event) -> {

                            DatabaseHandler db = new DatabaseHandler();
                            db.deleteUser(usersTable.getSelectionModel().getSelectedItem());
                            refreshTable();
                        });

                        HBox managebtn = new HBox(5);
                        managebtn.getChildren().addAll( iv1, del);
                        //HBox hbox = new HBox(8); // spacing = 8
                       // managebtn.getChildren().addAll(new Image("file:/D:/Java/mysecondapp/src/main/assets/edit.png"),new Image("file:/D:/Java/mysecondapp/src/main/assets/edit.png"));
                        //managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(image, new Insets(2, 2, 0, 3));
                        //HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        iconColumn.setCellFactory(cellFoctory);
        usersTable.setItems(UserList);


        DatabaseHandler dbHandler = new DatabaseHandler();


        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Мужчины", dbHandler.maleCount()),
                new PieChart.Data("Женщины", dbHandler.femaleCount())
        );
        pieGender.setData(list);

    }



}
