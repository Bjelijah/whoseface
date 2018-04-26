package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceDetectDeviceGroupStatus {
    @SerializedName("Id") String id;
    @SerializedName("LastUpdateTime") String lastUpdateTime;
    @SerializedName("PassingNumber") Integer passNumber;
    @SerializedName("LastResetTime") String lastResetTime;
    @SerializedName("LastNPassingNumber") Integer lastNPassingNumber;

    @Override
    public String toString() {
        return "FaceDetectDeviceGroupStatus{" +
                "id='" + id + '\'' +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", passNumber=" + passNumber +
                ", lastResetTime='" + lastResetTime + '\'' +
                ", lastNPassingNumber=" + lastNPassingNumber +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public String getLastResetTime() {
        return lastResetTime;
    }

    public void setLastResetTime(String lastResetTime) {
        this.lastResetTime = lastResetTime;
    }

    public Integer getLastNPassingNumber() {
        return lastNPassingNumber;
    }

    public void setLastNPassingNumber(Integer lastNPassingNumber) {
        this.lastNPassingNumber = lastNPassingNumber;
    }

    public FaceDetectDeviceGroupStatus() {

    }

    public FaceDetectDeviceGroupStatus(String id, String lastUpdateTime, Integer passNumber, String lastResetTime, Integer lastNPassingNumber) {

        this.id = id;
        this.lastUpdateTime = lastUpdateTime;
        this.passNumber = passNumber;
        this.lastResetTime = lastResetTime;
        this.lastNPassingNumber = lastNPassingNumber;
    }
}
