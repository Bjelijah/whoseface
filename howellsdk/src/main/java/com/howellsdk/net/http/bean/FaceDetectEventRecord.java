package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceDetectEventRecord {
    @SerializedName("Id") String id;
    @SerializedName("ComponentId") String componentId;
    @SerializedName("Name") String name;
    @SerializedName("EventType") String eventType;
    @SerializedName("AlarmTime") String alarmTime;
    @SerializedName("Severity")Integer severity;
    @SerializedName("DisalarmTime") String disAlarmTime;
    @SerializedName("ProcessTime")String processTime;
    @SerializedName("ProcessDescription") String processDescription;
    @SerializedName("Description") String description;
    @SerializedName("ObjectType")String objectType;
    @SerializedName("TriggerValue")Double triggerValue;
    @SerializedName("Confidence") Integer confidence;
    @SerializedName("FaceSnapData") FaceSnapData faceSnapData;
    @SerializedName("FaceSet") FaceSet faceSet;
    @SerializedName("FaceAppendData") FaceAppendData faceAppendData;

    @Override
    public String toString() {
        return "FaceDetectEventRecord{" +
                "id='" + id + '\'' +
                ", componentId='" + componentId + '\'' +
                ", name='" + name + '\'' +
                ", eventType='" + eventType + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", severity=" + severity +
                ", disAlarmTime='" + disAlarmTime + '\'' +
                ", processTime='" + processTime + '\'' +
                ", processDescription='" + processDescription + '\'' +
                ", description='" + description + '\'' +
                ", objectType='" + objectType + '\'' +
                ", triggerValue=" + triggerValue +
                ", confidence=" + confidence +
                ", faceSnapData=" + faceSnapData +
                ", faceSet=" + faceSet +
                ", faceAppendData=" + faceAppendData +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public String getDisAlarmTime() {
        return disAlarmTime;
    }

    public void setDisAlarmTime(String disAlarmTime) {
        this.disAlarmTime = disAlarmTime;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Double getTriggerValue() {
        return triggerValue;
    }

    public void setTriggerValue(Double triggerValue) {
        this.triggerValue = triggerValue;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public FaceSnapData getFaceSnapData() {
        return faceSnapData;
    }

    public void setFaceSnapData(FaceSnapData faceSnapData) {
        this.faceSnapData = faceSnapData;
    }

    public FaceSet getFaceSet() {
        return faceSet;
    }

    public void setFaceSet(FaceSet faceSet) {
        this.faceSet = faceSet;
    }

    public FaceAppendData getFaceAppendData() {
        return faceAppendData;
    }

    public void setFaceAppendData(FaceAppendData faceAppendData) {
        this.faceAppendData = faceAppendData;
    }

    public FaceDetectEventRecord() {

    }

    public FaceDetectEventRecord(String id, String componentId, String name, String eventType, String alarmTime, Integer severity, String disAlarmTime, String processTime, String processDescription, String description, String objectType, Double triggerValue, Integer confidence, FaceSnapData faceSnapData, FaceSet faceSet, FaceAppendData faceAppendData) {

        this.id = id;
        this.componentId = componentId;
        this.name = name;
        this.eventType = eventType;
        this.alarmTime = alarmTime;
        this.severity = severity;
        this.disAlarmTime = disAlarmTime;
        this.processTime = processTime;
        this.processDescription = processDescription;
        this.description = description;
        this.objectType = objectType;
        this.triggerValue = triggerValue;
        this.confidence = confidence;
        this.faceSnapData = faceSnapData;
        this.faceSet = faceSet;
        this.faceAppendData = faceAppendData;
    }
}
