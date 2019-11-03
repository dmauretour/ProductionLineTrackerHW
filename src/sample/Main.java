package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;

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
    Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);

    // test constructor used when creating production records from user interface
    int numProduced = 3; // this will come from the combobox in the UI
    int itemCount = 0;

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
      // using the iterator as the product id for testing
      System.out.println(pr.toString());
    }
  }
}
