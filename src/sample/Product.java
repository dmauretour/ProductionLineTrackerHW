package sample;

/** @author Dory Mauretour Porduct implements Item */
public abstract class Product implements Item {
  private int id;
  private ItemType type;
  private String manufacturer;
  private String name;

  /**
   * @param name pass a string
   * @param manufacturer pass a string
   * @param type pass an ItemType
   */
  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Id
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * String name
   *
   * @return name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * String name
   *
   * @param name pass string
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * String manufacturer
   *
   * @param manufacturer pass string
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * ItemType type
   *
   * @return type
   */
  public ItemType getType() {
    return type;
  }

  public void setType(ItemType type) {
    this.type = type;
  }

  /** @return str */
  @Override
  public String toString() {
    String str2 =
        String.format(
            "Name: %s\nManufacturer: %s\nType: %s", this.name, this.manufacturer, this.type);
    return str2;
  }
}

/** widget extends Product */
class Widget extends Product {
  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }
}
