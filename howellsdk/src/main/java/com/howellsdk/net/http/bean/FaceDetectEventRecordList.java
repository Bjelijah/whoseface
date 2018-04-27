package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceDetectEventRecordList {
    @SerializedName("Page") Page page;
    @SerializedName("FaceDetectEventRecord") ArrayList<FaceDetectEventRecord> eventRecords;

    @Override
    public String toString() {
        return "FaceDetectEventRecordList{" +
                "page=" + page +
                ", eventRecords=" + eventRecords +
                '}';
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public ArrayList<FaceDetectEventRecord> getEventRecords() {
        return eventRecords;
    }

    public void setEventRecords(ArrayList<FaceDetectEventRecord> eventRecords) {
        this.eventRecords = eventRecords;
    }

    public FaceDetectEventRecordList() {

    }

    public FaceDetectEventRecordList(Page page, ArrayList<FaceDetectEventRecord> eventRecords) {

        this.page = page;
        this.eventRecords = eventRecords;
    }
}
