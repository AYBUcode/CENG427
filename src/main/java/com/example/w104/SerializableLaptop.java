package com.example.w104;

import java.io.Serializable;
import android.graphics.Bitmap;


public class SerializableLaptop implements Serializable {
    private int id;
    private String brand;
    private double price;
    private byte [] mByte;		// Byte array to store Bitmap

    // http://www.javapractices.com/topic/TopicAction.do?Id=45
    // The serialVersionUID is a universal version identifier for a Serializable
    // class. Deserialization uses this number to ensure that a loaded class
    // corresponds exactly to a serialized object. If no match is found, then an
    // InvalidClassException is thrown.
    private static final long serialVersionUID = 1L;


    public SerializableLaptop() {
        super();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte [] getByteArray() {
        return mByte;
    }

    public void setByteArray(byte [] mByte) {
        this.mByte = mByte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
