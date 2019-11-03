package sample;

import javafx.collections.ObservableList;

public class Product implements Item {
  private int id;
  private ObservableList<ItemType> type;
  private String manufacturer;
  private String name;

  public Product(String name, String manufacturer, ObservableList<ItemType> type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public Product(String name, String manufacturer, ItemType audio) {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public ObservableList<ItemType> getType() {
    return type;
  }

  public void setType(ObservableList<ItemType> type) {
    this.type = type;
  }

  @Override
  public String toString() {
    String str =
        String.format(
            "Name: %s\nManufacturer: %s\nType: %s", this.name, this.manufacturer, this.type);
    return str;
  }
}

class Widget extends Product {
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}
