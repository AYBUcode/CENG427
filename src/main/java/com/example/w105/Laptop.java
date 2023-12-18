package com.example.w105;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Laptop implements Parcelable {
    private int id;
    private String brand;
    private double price;
    private Bitmap imageBitmap;

    public Laptop() {
        super();
    }

    public Laptop(int id, String brand, double price) {
        super();
        this.id = id;
        this.brand = brand;
        this.price = price;
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

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(brand);
        dest.writeDouble(price);
        dest.writeParcelable(imageBitmap, PARCELABLE_WRITE_RETURN_VALUE);
    }
    private Laptop(Parcel in) {
        id = in.readInt();
        brand = in.readString();
        price = in.readDouble();
        imageBitmap = (Bitmap) in.readParcelable(Bitmap.class.getClassLoader());
    }
	/*
	 public static final Parcelable.Creator<Laptop> CREATOR =
	            new Parcelable.Creator<Laptop>() {
	        public Laptop createFromParcel(Parcel in) {
	            return new Laptop(in);
	        }

	        public Laptop[] newArray(int size) {
	            return new Laptop[size];
	        }
	    };
	    */

    public static final Parcelable.Creator<Laptop> CREATOR =
            new Creator<Laptop>() {

        @Override
        public Laptop[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Laptop[size];
        }

        @Override
        public Laptop createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Laptop(source);
        }
    };
}
