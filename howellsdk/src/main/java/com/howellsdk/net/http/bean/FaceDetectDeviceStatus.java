package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceDetectDeviceStatus {
    @SerializedName("Id") String id;
    @SerializedName("IsOnline") Boolean isOnline;
    @SerializedName("LastUpdateTime") String lastUpdateTime;
    @SerializedName("PassingNumber") Integer passingNumber;
    @SerializedName("LastResetTime") String lastRestTime;
    @SerializedName("LastNPassingNumber")Integer lastNPassingNumber;

    @Override
    public String toString() {
        return "FaceDetectDeviceStatus{" +
                "id='" + id + '\'' +
                ", isOnline=" + isOnline +
                ", lastUpdateTime='" + lastUpdateTime + '\'' +
                ", passingNumber=" + passingNumber +
                ", lastRestTime='" + lastRestTime + '\'' +
                ", lastNPassingNumber=" + lastNPassingNumber +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getPassingNumber() {
        return passingNumber;
    }

    public void setPassingNumber(Integer passingNumber) {
        this.passingNumber = passingNumber;
    }

    public String getLastRestTime() {
        return lastRestTime;
    }

    public void setLastRestTime(String lastRestTime) {
        this.lastRestTime = lastRestTime;
    }

    public Integer getLastNPassingNumber() {
        return lastNPassingNumber;
    }

    public void setLastNPassingNumber(Integer lastNPassingNumber) {
        this.lastNPassingNumber = lastNPassingNumber;
    }

    public FaceDetectDeviceStatus() {

    }

    public FaceDetectDeviceStatus(String id, Boolean isOnline, String lastUpdateTime, Integer passingNumber, String lastRestTime, Integer lastNPassingNumber) {

        this.id = id;
        this.isOnline = isOnline;
        this.lastUpdateTime = lastUpdateTime;
        this.passingNumber = passingNumber;
        this.lastRestTime = lastRestTime;
        this.lastNPassingNumber = lastNPassingNumber;
    }
}
