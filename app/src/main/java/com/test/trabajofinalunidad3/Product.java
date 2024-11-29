package com.test.trabajofinalunidad3;

// Clase que representa un producto
public class Product {
    private final String name;
    private final int imageResId;

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

