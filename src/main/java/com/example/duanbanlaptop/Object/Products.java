package com.example.duanbanlaptop.Object;

public class Products {
    private int idProduct;
    private String nameProduct;
    private String describe;
    private String unit;
    private String image;
    private double price;
    private int stock;


    public Products(int idProduct, String nameProduct, String describe, String unit, String image, double price, int stock) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.describe = describe;
        this.unit = unit;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
