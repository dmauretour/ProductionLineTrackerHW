package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author : Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 *     facility that will keep track of what products are produced.
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root =
        FXMLLoader.load(
            getClass().getResource("ProductionTable.fxml")); // Load table from class sample
    primaryStage.setTitle("Production Facility");

    Scene scene = new Scene(root, 600, 525);
    primaryStage.setScene(scene);
    scene.getStylesheets().add(Main.class.getResource("guide.css").toExternalForm());
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
