package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceDetectThreshold {
    @SerializedName("Enable") Boolean enable;
    @SerializedName("PassingNumber") FaceThreshold faceThreshold;

    @Override
    public String toString() {
        return "FaceDetectThreshold{" +
                "enable=" + enable +
                ", faceThreshold=" + faceThreshold.toString() +
                '}';
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public FaceThreshold getFaceThreshold() {
        return faceThreshold;
    }

    public void setFaceThreshold(FaceThreshold faceThreshold) {
        this.faceThreshold = faceThreshold;
    }

    public FaceDetectThreshold() {

    }

    public FaceDetectThreshold(Boolean enable, FaceThreshold faceThreshold) {

        this.enable = enable;
        this.faceThreshold = faceThreshold;
    }
}
