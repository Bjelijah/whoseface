package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceDetectDeviceGroup {
    @SerializedName("Id") String id;
    @SerializedName("CreationTime") String createTime;
    @SerializedName("Name") String name;
    @SerializedName("ResetTime") String resetTime;
    @SerializedName("IconType") Integer iconType;
    @SerializedName("Information") String information;
    @SerializedName("LastNSeconds") Integer lastNSeconds;
    @SerializedName("FaceDetectDevice") ArrayList<FaceDetectDevice> faceDetectDevices;
    @SerializedName("GroupStatus") FaceDetectDeviceGroupStatus groupStatus;
    @SerializedName("Threshold") FaceDetectThreshold threshold;

    @Override
    public String toString() {
        return "FaceDetectDeviceGroup{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", resetTime='" + resetTime + '\'' +
                ", iconType=" + iconType +
                ", information='" + information + '\'' +
                ", lastNSeconds=" + lastNSeconds +
                ", faceDetectDevices=" + faceDetectDevices +
                ", groupStatus=" + groupStatus +
                ", threshold=" + threshold +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResetTime() {
        return resetTime;
    }

    public void setResetTime(String resetTime) {
        this.resetTime = resetTime;
    }

    public Integer getIconType() {
        return iconType;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getLastNSeconds() {
        return lastNSeconds;
    }

    public void setLastNSeconds(Integer lastNSeconds) {
        this.lastNSeconds = lastNSeconds;
    }

    public ArrayList<FaceDetectDevice> getFaceDetectDevices() {
        return faceDetectDevices;
    }

    public void setFaceDetectDevices(ArrayList<FaceDetectDevice> faceDetectDevices) {
        this.faceDetectDevices = faceDetectDevices;
    }

    public FaceDetectDeviceGroupStatus getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(FaceDetectDeviceGroupStatus groupStatus) {
        this.groupStatus = groupStatus;
    }

    public FaceDetectThreshold getThreshold() {
        return threshold;
    }

    public void setThreshold(FaceDetectThreshold threshold) {
        this.threshold = threshold;
    }

    public FaceDetectDeviceGroup() {

    }

    public FaceDetectDeviceGroup(String id, String createTime, String name, String resetTime, Integer iconType, String information, Integer lastNSeconds, ArrayList<FaceDetectDevice> faceDetectDevices, FaceDetectDeviceGroupStatus groupStatus, FaceDetectThreshold threshold) {

        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.resetTime = resetTime;
        this.iconType = iconType;
        this.information = information;
        this.lastNSeconds = lastNSeconds;
        this.faceDetectDevices = faceDetectDevices;
        this.groupStatus = groupStatus;
        this.threshold = threshold;
    }
}
