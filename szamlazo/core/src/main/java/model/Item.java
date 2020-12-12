package model;

import com.sun.istack.internal.NotNull;
import exception.*;

public class Item {

    public static int stockLow = 2;

    private String barcode;
    private String name;
    private String manufacturer;
    private String description;
    private double price;
    private ItemType type;
    private int stock;

    public Item() {
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@NotNull String barcode) throws EmptyStringException {
        if (barcode.isEmpty()){
            throw new EmptyStringException();
        }
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) throws EmptyStringException {
        if (name.isEmpty()){
            throw new EmptyStringException();
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) throws EmptyStringException {
        if (description.isEmpty()){
            throw new EmptyStringException();
        }
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(@NotNull Double price) throws InvalidValueException {
        if (price <= 0){
            throw new InvalidValueException(String.format("The price cannot be negative or 0. Input: %s", price.toString()));
        }
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(@NotNull ItemType type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(@NotNull Integer stock) throws InvalidValueException {
        if (stock < 0){
            throw new InvalidValueException(String.format("The stock cannot be negative. Input: %s", stock.toString()));
        }
        this.stock = stock;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotNull String manufacturer) throws EmptyStringException {
        if (manufacturer.isEmpty()){
            throw new EmptyStringException();
        }
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(@NotNull Object obj) {
        if (obj instanceof Item){
            if (((Item) obj).getBarcode().equals(this.barcode) &&
                    ((Item) obj).getName().equals(this.name) &&
                    ((Item) obj).getManufacturer().equals(this.manufacturer) &&
                    ((Item) obj).getPrice() == price &&
                    ((Item) obj).getStock() == stock &&
                    ((Item) obj).getType().equals(type)){
                return true;
            } else{
                return false;
            }
        } else {
            return false;
        }
    }
}
