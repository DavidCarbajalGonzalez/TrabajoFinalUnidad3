package com.test.trabajofinalunidad3;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
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

    // Constructor para Parcelable
    protected Product(Parcel in) {
        name = in.readString();
        imageResId = in.readInt();
    }

    // Creator para Parcelable
    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(imageResId);
    }
}


