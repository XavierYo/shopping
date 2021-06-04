package com.xavier.domain;

public class Item {
    private int item_id;
    private String item_name;
    private double price;
    private int category_id;
    private String description;
    private int num;

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() { return category_id;  }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item [ID=" + item_id + ", name=" + item_name + "]";
    }

    public int getNum() {
        return num;
    }
    public void setNum(int number){
        this.num =number;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (item_id != other.item_id)
            return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        final int prime = 37;
        int result = 1;
        result = prime * result + item_id;
        return result;
    }
}
