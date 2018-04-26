package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceDetectDeviceGroupList {
    @SerializedName("Page") Page page;
    @SerializedName("FaceDetectDeviceGroup") ArrayList<FaceDetectDeviceGroup> faceDetectDeviceGroups;

    @Override
    public String toString() {
        return "FaceDetectDeviceGroupList{" +
                "page=" + page +
                ", faceDetectDeviceGroups=" + faceDetectDeviceGroups +
                '}';
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<FaceDetectDeviceGroup> getFaceDetectDeviceGroups() {
        return faceDetectDeviceGroups;
    }

    public void setFaceDetectDeviceGroups(ArrayList<FaceDetectDeviceGroup> faceDetectDeviceGroups) {
        this.faceDetectDeviceGroups = faceDetectDeviceGroups;
    }

    public FaceDetectDeviceGroupList() {

    }

    public FaceDetectDeviceGroupList(Page page, ArrayList<FaceDetectDeviceGroup> faceDetectDeviceGroups) {

        this.page = page;
        this.faceDetectDeviceGroups = faceDetectDeviceGroups;
    }
}
