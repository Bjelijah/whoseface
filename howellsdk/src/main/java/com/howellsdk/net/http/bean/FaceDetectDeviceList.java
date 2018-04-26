package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceDetectDeviceList {
    @SerializedName("Page") Page page;
    @SerializedName("FaceDetectDevice") ArrayList<FaceDetectDevice> faceDetectDevices;

    @Override
    public String toString() {
        return "FaceDetectDeviceList{" +
                "page=" + page +
                ", faceDetectDevices=" + faceDetectDevices +
                '}';
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<FaceDetectDevice> getFaceDetectDevices() {
        return faceDetectDevices;
    }

    public void setFaceDetectDevices(ArrayList<FaceDetectDevice> faceDetectDevices) {
        this.faceDetectDevices = faceDetectDevices;
    }

    public FaceDetectDeviceList() {

    }

    public FaceDetectDeviceList(Page page, ArrayList<FaceDetectDevice> faceDetectDevices) {

        this.page = page;
        this.faceDetectDevices = faceDetectDevices;
    }
}
