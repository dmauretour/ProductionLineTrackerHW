package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

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
  @FXML private TextField employeeName;
  @FXML private TextField employeePassword;
  @FXML private TextArea textAreaEmployee;


  private Connection conn;
  private Statement stmt;
  private ObservableList<Product> productLine;
  private int productLineIndex = 0;
  private ProductionRecord prodRecord;
  private Product productFromDB;
  private Employee employeeDetails;
  private ArrayList<ProductionRecord> pr = new ArrayList();
  ObservableList<ProductionRecord> prodRecordList = FXCollections.observableArrayList();



  /** Initializing methods comboBox and choiceBox */
  public void initialize() throws SQLException { // Initializing
    initializeComboBox();
    initializeChoiceBox();
    connection();
   // loadProductList();
    setupProductLineTable();
    showProduction();

  }

  public void connection(){
    try {
      // JDBC driver name and database URL
      String JDBC_DRIVER = "org.h2.Driver";
      Class.forName(JDBC_DRIVER);
      String DB_URL = "jdbc:h2:./res/ProdDB";
      //  Database credentials
      String USER = "";
      String PASS = "";
      System.out.println("Attempting to connect to database");
//      try {
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("res/properties"));
//        PASS = prop.getProperty("password");
//      } catch(Exception e){
//        System.out.println("Database error");
//      }
      // STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      System.out.println("Successfully connected to database");

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
    /**
     * The setupProductLineTable method sets up the table view.
     */
  private void setupProductLineTable() {

    productLine = FXCollections.observableArrayList();
    productNameCol.setCellValueFactory(new PropertyValueFactory("name"));
    manufacturerCol.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    itemTypeCol.setCellValueFactory(new PropertyValueFactory("type"));
    productionTable.setItems(productLine);
  }

    /**
     * The loadProductList method gets product items from the database and adds them to the
     * productLine observable list.
     *
     * @throws SQLException if sql statement is valid.
     */
  private void loadProductList() throws SQLException {
    String sql = "SELECT * FROM PRODUCT";
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      String name = rs.getString(2);
      ItemType type = ItemType.valueOf(rs.getString(3));
      String manufacturer = rs.getString(4);

      productFromDB = new Widget(name, manufacturer, type);
      productLine.add(productFromDB);

      chooseProduct.getItems().add(productLine.get(productLineIndex++));
    }
  }

  /**
   * addProduct method adds a new product into the product database table.
   *
   * @throws SQLException Checks if sql statement is valid
   */
  @FXML
  public void addProduct() throws SQLException {
    String name = productNameField.getText();
    String manufacturer = manufacturerField.getText();
    ItemType type = itemtypeChoice.getValue();
    String query = "INSERT INTO PRODUCT (NAME, TYPE,  MANUFACTURER) VALUES(?, ?, ?)";
    PreparedStatement prodToDB = conn.prepareStatement(query);
    prodToDB.setString(1, name);
    prodToDB.setString(2, type.toString());
    prodToDB.setString(3,  manufacturer);
    prodToDB.executeUpdate();
    productNameField.clear();
    manufacturerField.clear();
    itemtypeChoice.getSelectionModel().clearSelection();
    loadProductList();
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
  public void recordProduction() throws SQLException {

    Product productProduced = chooseProduct.getSelectionModel().getSelectedItem();
   String quantity = String.valueOf(chooseQuantity.getValue());
   int numProduced = Integer.parseInt(quantity);
   ArrayList<ProductionRecord> pr = new ArrayList();
    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      prodRecord = new ProductionRecord(productProduced, productionRunProduct);
      // using the iterator as the product id for testing
      pr.add(prodRecord);
    }
    prodRecordList.clear();
    addToProductionDB(pr);
    loadProductionLog();
    showProduction();
  }
  /**
   * loadProductionLog method stores record
   * production objects from the database into a record
   * production array.
   *
   * @throws SQLException Checks if the sql statement is valid.
   */
  private void loadProductionLog() throws SQLException {
    String prodSql = "SELECT * FROM PRODUCTIONRECORD";
    ResultSet rs = stmt.executeQuery(prodSql);
    while (rs.next()) {
      int productionNum = rs.getInt(1);
      String productName = rs.getString(2);
      String serialNum = rs.getString(3);
      Date dateProduced = rs.getDate(4);

      ProductionRecord tempRecord =
              new ProductionRecord(productionNum, productName, serialNum, dateProduced);
      prodRecordList.add(tempRecord);
    }
  }
  /**
   * showProduction method outputs record p
   * roduction items from the database to the production log
   * text area.
   */
  private void showProduction() {
    textRecordProduction.clear();

    for (int i = 0; i < prodRecordList.size(); i++) {
      textRecordProduction.appendText(prodRecordList.get(i).toString() + "\n");
    }


  }
  /**
   * The addToProdcutionDB adds record details into the production record table.
   *
   * @param prodArray receives an array list that carries record
   *                  production type objects.
   * @throws SQLException Checks if the sql statement is valid.
   */
  private void addToProductionDB(ArrayList<ProductionRecord> prodArray) throws SQLException {
    String prodQuery =
        "INSERT INTO PRODUCTIONRECORD(product_name, serial_Num, date_produced) VALUES (?, ?, ?)";
    PreparedStatement addRecord = conn.prepareStatement(prodQuery);
    for (int i = 0; i < prodArray.size(); i++) {
      addRecord.setString(1, prodArray.get(i).getName());
      addRecord.setString(2, prodArray.get(i).getSerialNum());
      addRecord.setTimestamp(
          3, new Timestamp(prodArray.get(i).getProdDate().getTime()));
//     addRecord.setString(4, employeeDetails.getUsername());
      addRecord.executeUpdate();
    }
  }
  /**
   * employeeAccount method adds employee objects
   * created into the employee table in the database.
   *
   * @throws SQLException Checks if the sql statement used is valid.
   */
    @FXML
    void createEmpoyeeAcc() throws SQLException {
    String employeeFullName = employeeName.getText();
    String employeePass = employeePassword.getText();
    employeeDetails = new Employee(employeeFullName, employeePass);

    String prodQuery = "INSERT INTO EMPLOYEE (NAME, PASSWORD, USERNAME, EMAIL) VALUES (?, ?, ?, ?)";
    PreparedStatement addEmployee = conn.prepareStatement(prodQuery);
    addEmployee.setString(1, employeeFullName);
    addEmployee.setString(2, employeePass);
    addEmployee.setString(3, employeeDetails.getUsername());
    addEmployee.setString(4, employeeDetails.getEmail());
    addEmployee.executeUpdate();

    employeeName.clear();
    employeePassword.clear();
    textAreaEmployee.appendText(employeeDetails.toString() + "\n");

    }
    @FXML
    void createEmpoyeeBtn(ActionEvent event) throws SQLException {
    createEmpoyeeAcc();
    }
}
