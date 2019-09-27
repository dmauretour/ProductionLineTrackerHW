package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Name: Dory Mauretour Date: 9/21/2019 Purpose: Create software for a media player production
 * facility that will keep track of what products are produced.
 */
public class Controller {

  @FXML private TableView<Item> existingProducts;
  @FXML private TextField productNameField;
  @FXML private TextField manufacturerField;
  @FXML private ChoiceBox<String> itemtypeChoice;
  @FXML private ComboBox<Integer> chooseQuantity;

  public Controller() {}

  @FXML
  void addProduct(ActionEvent event) {
    ObservableList<Item> data = existingProducts.getItems(); // Collects entry data
    data.add( // Add data to existings products
        new Item(
            productNameField.getText(), manufacturerField.getText(), itemtypeChoice.getValue()));

    productNameField.setText("");
    manufacturerField.setText("");
    itemtypeChoice.setValue("");

    // prints statements for values
    System.out.println(productNameField + " ");
    System.out.println(manufacturerField + " ");
    System.out.println(itemtypeChoice + " ");
  }

  public void initialize() { // Initializing
    initializeComboBox();
    initializeChoiceBox();
  }

  @FXML
  private void initializeComboBox() {
    ObservableList<Integer> data = chooseQuantity.getItems();
    for (int i = 1; i <= 10; i++) { // compute values rom 1 to 10
      data.add(i);
    }

    chooseQuantity.getSelectionModel().selectFirst(); // Choose first selection
    chooseQuantity.setEditable(true);
  }

  @FXML
  private void initializeChoiceBox() {
    ObservableList<String> data = itemtypeChoice.getItems(); // Get item value input
    data.clear();
    data.add("Audio");
    data.add("Visual");
    data.add("Audio Mobile");
    data.add("Visual Mobile");
  }

  @FXML
  public void recordProduction(ActionEvent actionEvent) {
    System.out.println(chooseQuantity + " ");
  }
}
