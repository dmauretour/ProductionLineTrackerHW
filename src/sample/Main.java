package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.*;

/**
 * Name: Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 * facility that will keep track of what products are produced.
 */
public class Main extends Application {
  private TextArea ta = new TextArea();
  private Button btShowJobs = new Button("Show Records");
  private Statement stmt;

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root =
        FXMLLoader.load(getClass().getResource("sample.fxml")); // Load table from class sample
    primaryStage.setTitle("Production Facility");
    // primaryStage.setScene(new Scene(root, 600, 525)); // Table format

    Scene scene = new Scene(root, 600, 525);
    primaryStage.setScene(scene);
    scene.getStylesheets().add(Main.class.getResource("guide.css").toExternalForm());
    primaryStage.show();

    // Display JOB data
    // btShowJobs.setOnAction(e-> showData());
  }

  public static void main(String[] args) {
    launch(args);
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProductionLineTracker";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;

    try {

      String sql =
          "INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' )"; // Get value in database
      stmt.executeUpdate(sql);

      // area
    } catch (SQLException e) {
      e.printStackTrace();
    } // end try catch
  } // end method showData
}
