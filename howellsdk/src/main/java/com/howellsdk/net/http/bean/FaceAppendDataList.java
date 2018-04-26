package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceAppendDataList {
    @SerializedName("Page") Page page;
    @SerializedName("FaceAppendData") ArrayList<FaceAppendData> faceAppendData;

    @Override
    public String toString() {
        return "FaceAppendDataList{" +
                "page=" + page +
                ", faceAppendData=" + faceAppendData +
                '}';
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<FaceAppendData> getFaceAppendData() {
        return faceAppendData;
    }

    public void setFaceAppendData(ArrayList<FaceAppendData> faceAppendData) {
        this.faceAppendData = faceAppendData;
    }

    public FaceAppendDataList() {

    }

    public FaceAppendDataList(Page page, ArrayList<FaceAppendData> faceAppendData) {

        this.page = page;
        this.faceAppendData = faceAppendData;
    }
}
