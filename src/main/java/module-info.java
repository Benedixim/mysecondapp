module com.example.mysecondapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;


    opens com.example.mysecondapp to javafx.fxml;
    exports com.example.mysecondapp;

}