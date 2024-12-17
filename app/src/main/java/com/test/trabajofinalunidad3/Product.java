package com.test.trabajofinalunidad3;

import java.io.Serializable;

public class Product implements Serializable {
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



