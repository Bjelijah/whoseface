package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceThreshold {
    @SerializedName("Max") Integer max;
    @SerializedName("Min") Integer min;

    @Override
    public String toString() {
        return "FaceThreshold{" +
                "max=" + max +
                ", min=" + min +
                '}';
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public FaceThreshold() {

    }

    public FaceThreshold(Integer max, Integer min) {

        this.max = max;
        this.min = min;
    }
}
