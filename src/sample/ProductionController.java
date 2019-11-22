package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

/**


 * @author : Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 *     facility that will keep track of what products are produced.
 */
public class ProductionController<statement> {

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

  private Connection conn = null;

  /** Initializing methods comboBox and choiceBox */
  public void initialize() throws SQLException { // Initializing
    initializeComboBox();
    initializeChoiceBox();
//    loadProductList();
//    loadProductionLog();
    

    // JDBC driver name and database URL
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/ProdDB";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    System.out.println("Attempting to connect to database");

    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      Statement stmt = conn.createStatement();
      System.out.println("Successfully connected to database");

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.show();
    }
    
  }

//  private void loadProductionLog() throws SQLException {
//    ObservableList <ProductionRecord> prodRecordList = FXCollections.observableArrayList();
//    String sql = "SELECT * FROM PRODUCTIONRECORD";
//    Statement stmt = conn.createStatement();
//    ResultSet rs = stmt.executeQuery(sql);
//    while(rs.next()){
//      Integer productionNum = rs.getInt(1);
//      Integer productID = rs.getInt(2);
//      String serialNum = rs.getString(3);
//      String dateProduced = rs.getString(4);
//
////    ProductionRecord tempRecord = new ProductionRecord(productionNum, productID, serialNum, new Date());
////      ArrayList <Product> tempList
//    }
//  }
//
//  private void loadProductList() throws SQLException {
//    ObservableList <Product> obserProductLine = FXCollections.observableArrayList();
//    String sql = "SELECT * FROM PRODUCT";
//    Statement stmt = conn.createStatement();
//    ResultSet rs = stmt.executeQuery(sql);
//    while(rs.next()){
//      Integer id = rs.getInt(1);
//      String name = rs.getString(2);
//      String manufacturer = rs.getString(3);
//      String type = rs.getString(4);
//      switch (type) {
//        case "AU":
//          obserProductLine.add(
//                  new AudioPlayer(
//                          name, manufacturer, "DSD/FLAC/ALAC/FF/MQA/Ogg-Vorbis/MP3/AAC", " M3U/PLS/WPL"));
//          break;
//        case "VI":
//          Screen newScreen = new Screen("720x480", 40, 22);
//          obserProductLine.add(new MoviePlayer(name, manufacturer, newScreen, MonitorType.LCD));
//          break;
//        case "AM":
//          System.out.println("Not Applicable");
//          break;
//        case "VM":
//          System.out.println("Not Applicable");
//        default:
//          break;
//      }
//    }
//  }

  /**
   * Method addProduct add the input from the name, manufacturer and item type to the Product
   * database
   *
   * @param event
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException {

    ObservableList<Product> productLine = FXCollections.observableArrayList();

    productIdCol.setCellValueFactory(new PropertyValueFactory("id"));
    productNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    itemTypeCol.setCellValueFactory(new PropertyValueFactory("type"));

    productionTable.setItems(productLine);
    ItemType type = itemtypeChoice.getValue();
    String manufacturer = manufacturerField.getText();
    String name = productNameField.getText();
    switch (type) {
      case AUDIO:
        productLine.add(
            new AudioPlayer(
                name, manufacturer, "DSD/FLAC/ALAC/FF/MQA/Ogg-Vorbis/MP3/AAC", " M3U/PLS/WPL"));
        break;
      case VISUAL:
        Screen newScreen = new Screen("720x480", 40, 22);
        productLine.add(new MoviePlayer(name, manufacturer, newScreen, MonitorType.LCD));
        break;
      case AUDIO_MOBILE:
        break;
      case VISUAL_MOBILE:
      default:
        break;
    }

    String query = "INSERT INTO PRODUCT " + "(NAME, MANUFACTURER, TYPE)" + "VALUES(?, ?, ?)";
    PreparedStatement stmnt = conn.prepareStatement(query);
    stmnt.setString(1, productNameField.getText());
    stmnt.setString(2, manufacturerField.getText());
    stmnt.setString(3, itemtypeChoice.getValue().code);
    stmnt.executeUpdate();


    chooseProduct.getItems().clear();
    chooseProduct.getItems().addAll(productLine);

    System.out.println(productNameField.getText());
    System.out.println(manufacturerField.getText());
    System.out.println(itemtypeChoice.getValue());
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

    itemtypeChoice.getItems().addAll(ItemType.values());
    itemtypeChoice.getSelectionModel().selectFirst(); // Display first selection
  }

  /** Method that will add input into production database */
  @FXML
  public void recordProduction() {
//    textRecordProduction.clear();
//    int quantity =
//        Integer.parseInt(String.valueOf(chooseQuantity.getSelectionModel().getSelectedItem()));
//    for (int i = 0; i < quantity; i++) {
//      textRecordProduction.appendText(chooseProduct.getSelectionModel().getSelectedItem() + "\n");0
//    }
      Product productProduced = chooseProduct.getSelectionModel().getSelectedItem();

      int numProduced = 3; // this will come from the combobox in the UI
      int itemCount = 0;

      for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
          ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
          // using the iterator as the product id for testing
          System.out.println(pr.toString());
          textRecordProduction.appendText(pr.toString() + "\n");
      }
  }
}
