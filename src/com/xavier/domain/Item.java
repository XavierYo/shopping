package com.xavier.domain;

public class Item {
    private int itemID;
    private String itemName;
    private double price;
    private int categoryID;
    private String itemDesc;
    private int number;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() { return categoryID;  }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    @Override
    public String toString() {
        return "Item [ID=" + itemID + ", name=" + itemName + "]";
    }

    public int getItemNumber() {
        return number;
    }
    public void setItemNumber(int number){
        this.number=number;
    }
}
