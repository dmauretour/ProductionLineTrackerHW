package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/** */
public class Controller {

    /**
     * Name: Dory Mauretour
     * Date: 9/21/2019
     * Purpose: Create software for a media player production facility that will
     *          keep track of what products are produced.
     */

    @FXML private TableView<Item> existingProducts;
    @FXML private TextField productNameField;
    @FXML private TextField manufacturerField;
    private ComboBox<String> cboTableName = new ComboBox<>();


    @FXML
    private ChoiceBox<String> itemtypeChoice;
    @FXML
    private Button btnAddProduct;
    @FXML
    private TextArea recordProduction;

    void recordProduction(ActionEvent event){
    System.out.println("Record");
    }

    @FXML
    private ComboBox<Integer> chooseQuantity;
    void chooseQuantity(ActionEvent event){
        ObservableList<Integer> data = chooseQuantity.getItems();
        cboTableName.getItems().add(" 1 ");
        cboTableName.getItems().add(" 2 ");
    }

    @FXML
    void addProduct(ActionEvent event) {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/ProductionLineTracker";

        //  Database credentials
        final String USER = "";
        final String PASS = "";
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 3: Execute a query
            stmt = conn.createStatement();

            String sql = "INSERT INTO Product(itemType, manufacturer, productName) VALUES ( 'AUDIO', 'Apple', 'iPod' );";

            ResultSet rs = stmt.executeQuery(sql);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }






