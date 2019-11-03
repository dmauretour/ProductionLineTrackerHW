package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

/**
 * @author : Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 *     facility that will keep track of what products are produced.
 */
public class ProductionController {

  @FXML private TextField productNameField;
  @FXML private TextField manufacturerField;
  @FXML private ChoiceBox<ItemType> itemtypeChoice;
  @FXML private ComboBox<Integer> chooseQuantity;
  @FXML private ListView<Product> chooseProduct;
  @FXML private TableColumn<?, ?> productIdCol;
  @FXML private TableColumn<?, ?> productNameCol;
  @FXML private TableColumn<?, ?> manufacturerCol;
  @FXML private TableColumn<?, ?> itemTypeCol;
  @FXML private TableView<Product> productionTable;
  @FXML private TextArea textRecordProduction;

  // public String code;
  private Connection conn = null;

  /** Initializing methods comboBox and choiceBox */
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

    } catch (ClassNotFoundException | SQLException e) {
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

    ObservableList<Product> productLine = FXCollections.observableArrayList();

    productIdCol.setCellValueFactory(new PropertyValueFactory("id"));
    productNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    itemTypeCol.setCellValueFactory(new PropertyValueFactory("type"));

    productionTable.setItems(productLine);
    productLine.add(
        new Product(
            productNameField.getText(), manufacturerField.getText(), itemtypeChoice.getItems()));

    ObservableList<Product> productChoice = FXCollections.observableArrayList();
    chooseProduct.setItems(productChoice);
    productChoice.add(
        new Product(
            productNameField.getText(), manufacturerField.getText(), itemtypeChoice.getItems()));

    System.out.println(productNameField.getText());
    System.out.println(manufacturerField.getText());
    System.out.println(itemtypeChoice.getItems());
  }

  /** Method that add quantity to combo box */
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

  /** Method that adds value to the choice box */
  // Inputs entered value and add them in item type choice box
  @FXML
  private void initializeChoiceBox() {
    ObservableList<ItemType> list = itemtypeChoice.getItems(); // Get item value input
    list.clear();
    list.add(ItemType.AUDIO);
    list.add(ItemType.VISUAL);
    list.add(ItemType.AUDIO_MOBILE);
    list.add(ItemType.VISUAL_MOBILE);
  }

  /** Method that will add input into production database */
  @FXML
  public void recordProduction() {
    textRecordProduction.setText(
        "Name: "
            + productNameField.getText()
            + "\n Manufacturer: "
            + manufacturerField.getText()
            + "\n Type: "
            + itemtypeChoice.getItems());
  }
}
