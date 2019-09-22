package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Name: Dory Mauretour
 * Date: 9/21/2019
 * Purpose: Class class that calls the table names
 */

public class Item {
    private final SimpleStringProperty productName = new SimpleStringProperty("");
    private final SimpleStringProperty manufacturer = new SimpleStringProperty("");
    private final SimpleStringProperty itemType = new SimpleStringProperty("");


    public Item() {
        this("", "","");

    }

    public Item(String productName, String manufacturer, String itemType) {
        setProductName(productName);
        setManufacturer(manufacturer);
        setItemType(itemType);
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(String fLine) {
        productName.set(fLine);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public void setManufacturer(String fLine) {
        manufacturer.set(fLine);
    }

    public String getItemType() {
        return itemType.get();
    }

    public void setItemType(String fLine) {
        itemType.set(fLine);
    }
}


