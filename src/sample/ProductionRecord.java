package sample;

import java.util.Date;

public class ProductionRecord {

  private int productionNum;
  private int productID;
  private String serialNum;
  private Date dateProduced;
  private String productName;
    /**
     * A class constructor that accepts one argument.
     *
     * @param productID Pass an integer value as the argument.
     */
  public ProductionRecord(int productID) {
    this.productionNum = 0;
    this.productID = productID;
    this.serialNum = "0";
    this.dateProduced = new Date();
  }
    /**
     * A class constructor that has four parameters.
     *
     * @param productionNum pass an integer value as an argument.
     * @param productName         Pass a string value as an argument.
     * @param serialNum     pass a string as an argument
     * @param dateProduced     pass a date type argument.
     */
  public ProductionRecord(int productionNum, String productName, String serialNum, Date dateProduced) {
    this.productionNum = productionNum;
    this.productName = productName;
    this.serialNum = serialNum;
    this.dateProduced = dateProduced;
  }
    /**
     * A class constructor that accepts two arguments.
     *
     * @param product   pass a Product object as an argument.
     * @param zeros pass an integer value as an argument.
     */

  public ProductionRecord(Product product, int zeros) {
    String IdNumber = String.format("%05d", zeros);
    this.serialNum = product.getManufacturer().substring(0, 3) + product.getType() + IdNumber;
    this.productName = product.getName();

    this.dateProduced = new Date();
  }

/**
 * Setters and getters
 */

  public void setProductionNum(int productionNum) {
    this.productionNum = productionNum;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public void setSerialNum(String serialNum) {
    this.serialNum = serialNum;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public int getProductionNum() {
    return productionNum;
  }

  public int getProductID() {
    return productID;
  }

  public String getSerialNum() {
    return serialNum;
  }

  public String getName(){return productName; }

  public Date getProdDate() {
    return dateProduced;
  }

  @Override
  public String toString() {
    String str =
        String.format(
            "Prod. Num: %s Product Name: %s Serial Num: %s Date: %s",
            this.productionNum, this.productName, this.serialNum, this.dateProduced);
    return str;
  }
}
