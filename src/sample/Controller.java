package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Name: Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 * facility that will keep track of what products are produced.
 */
public class Controller {

  // @FXML private TableView<Item> existingProducts;
  @FXML private TextField productNameField;
  @FXML private TextField manufacturerField;
  @FXML private ChoiceBox<String> itemtypeChoice;
  @FXML private ComboBox<Integer> chooseQuantity;

  Connection conn = null;

  public void initialize() { // Initializing
    initializeComboBox();
    initializeChoiceBox();

    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/res";

    //  Database credentials
    final String USER = "";
    final String PASS = "";

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method addProduct add the input from the name, manufacturer and item type to the Product
   * database
   *
   * @param event
   */
  @FXML
  void addProduct(ActionEvent event) {

    try {
      String sql2 = "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER) " + "VALUES (?,?,?)";

      PreparedStatement state = conn.prepareStatement(sql2);

      state.setString(1, productNameField.getText());
      state.setString(2, manufacturerField.getText());
      state.setString(3, itemtypeChoice.getValue());

      state.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Inputs numbers 1 through 10 in quantity combobox
  @FXML
  private void initializeComboBox() {
    ObservableList<Integer> data = chooseQuantity.getItems();
    for (int i = 1; i <= 10; i++) { // compute values rom 1 to 10
      data.add(i);
    }

    chooseQuantity.getSelectionModel().selectFirst(); // Choose first selection
    chooseQuantity.setEditable(true);
  }

  // Inputs entered value and add them in item type choice box
  @FXML
  private void initializeChoiceBox() {
    ObservableList<String> data = itemtypeChoice.getItems(); // Get item value input
    data.clear();
    data.add("Audio");
    data.add("Visual");
    data.add("Audio Mobile");
    data.add("Visual Mobile");
  }

  /**
   * Method that will add input into production database
   *
   * @param actionEvent
   */
  @FXML
  public void recordProduction(ActionEvent actionEvent) {
    System.out.println(chooseQuantity + " ");
  }
}
