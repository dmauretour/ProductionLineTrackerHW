package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 * Name: Dory Mauretour
 * Date: 9/21/2019
 * Purpose: Create software for a media player production facility that will
 *          keep track of what products are produced.
 */

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Production Facility");
        primaryStage.setScene(new Scene(root, 600, 525));
        primaryStage.show();
       // initializeDB();
        // Display JOB data
        //btShowJobs.setOnAction(e-> showData());
    }


    public static void main(String[] args) {
        launch(args);
    }
}

