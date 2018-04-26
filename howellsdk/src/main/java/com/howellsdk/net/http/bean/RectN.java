package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;


public class RectN {
    @SerializedName("X") Double x;
    @SerializedName("Y") Double y;
    @SerializedName("Width") Double width;
    @SerializedName("Height") Double height;

    @Override
    public String toString() {
        return "RectN{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public RectN() {

    }

    public RectN(Double x, Double y, Double width, Double height) {

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
