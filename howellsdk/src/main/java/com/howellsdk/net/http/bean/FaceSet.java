package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceSet {
    @SerializedName("Id") String id;
    @SerializedName("Type") String type;
    @SerializedName("Name") String name;
    @SerializedName("CreationTime") String creationTime;
    @SerializedName("Threshold") Integer threshold;
    @SerializedName("Description") String description;
    @SerializedName("Capacity") Integer capacity;
    @SerializedName("Priority") Integer priority;

    @Override
    public String toString() {
        return "FaceSet{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", creationTime='" + creationTime + '\'' +
                ", threshold=" + threshold +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", priority=" + priority +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public FaceSet() {

    }

    public FaceSet(String id, String type, String name, String creationTime, Integer threshold, String description, Integer capacity, Integer priority) {

        this.id = id;
        this.type = type;
        this.name = name;
        this.creationTime = creationTime;
        this.threshold = threshold;
        this.description = description;
        this.capacity = capacity;
        this.priority = priority;
    }
}
