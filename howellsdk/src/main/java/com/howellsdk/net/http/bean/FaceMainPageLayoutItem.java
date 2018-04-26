package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceMainPageLayoutItem {
    @SerializedName("Id") String id;
    @SerializedName("ItemType") String itemType;
    @SerializedName("DataType") String dataType;
    @SerializedName("SourceType") String sourceType;
    @SerializedName("SourceId") String sourceId;
    @SerializedName("Unit") String unit;
    @SerializedName("X") int x;
    @SerializedName("Y") int y;
    @SerializedName("Width") int width;
    @SerializedName("Height") int height;

    @Override
    public String toString() {
        return "FaceMainPageLayoutItem{" +
                "id='" + id + '\'' +
                ", itemType='" + itemType + '\'' +
                ", dataType='" + dataType + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", unit='" + unit + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public FaceMainPageLayoutItem() {

    }

    public FaceMainPageLayoutItem(String id, String itemType, String dataType, String sourceType, String sourceId, String unit, int x, int y, int width, int height) {

        this.id = id;
        this.itemType = itemType;
        this.dataType = dataType;
        this.sourceType = sourceType;
        this.sourceId = sourceId;
        this.unit = unit;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
