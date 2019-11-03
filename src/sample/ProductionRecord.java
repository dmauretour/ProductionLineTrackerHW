package sample;

import java.util.Date;

public class ProductionRecord {

  private int productionNum;
  private int productID;
  private String serialNum;
  private Date dateProduced;

  public ProductionRecord(int productID) {
    this.productionNum = 0;
    this.productID = productID;
    this.serialNum = "0";
    this.dateProduced = new Date();
  }

  public ProductionRecord(int productionNum, int productID, String serialNum, Date dateProduced) {
    this.productionNum = productionNum;
    this.productID = productID;
    this.serialNum = serialNum;
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(Product product, int zeros) {
    String IdNumber = String.format("%05d", zeros);
    this.serialNum = product.getManufacturer().substring(0, 3) + product.getType() + IdNumber;

    this.dateProduced = new Date();
  }

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

  public Date getProdDate() {
    return dateProduced;
  }

  @Override
  public String toString() {
    String str =
        String.format(
            "Prod. Num: %s Product ID: %s Serial Num: %s Date: %s",
            this.productionNum, this.productID, this.serialNum, this.dateProduced);
    return str;
  }
}
