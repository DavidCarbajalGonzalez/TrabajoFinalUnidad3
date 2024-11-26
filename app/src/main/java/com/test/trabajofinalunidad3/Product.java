package com.test.trabajofinalunidad3;

// Atributos
public class Product {
    private String name;
    private int imageResId;

    // Constructor
    public Product(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }
}
