package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceSnapData {
    @SerializedName("FacePictureId") String facePictureId;
    @SerializedName("BackgroundPictureId") String backgroundPictureId;
    @SerializedName("Position") RectN position;
    @SerializedName("Score") Integer score;
    @SerializedName("ModelData") String modelData;
    @SerializedName("Feature") FaceFeature feature;

    public FaceSnapData() {
    }

    public FaceSnapData(String facePictureId, String backgroundPictureId, RectN position, Integer score, String modelData, FaceFeature feature) {

        this.facePictureId = facePictureId;
        this.backgroundPictureId = backgroundPictureId;
        this.position = position;
        this.score = score;
        this.modelData = modelData;
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "FaceSnapData{" +
                "facePictureId='" + facePictureId + '\'' +
                ", backgroundPictureId='" + backgroundPictureId + '\'' +
                ", position=" + position +
                ", score=" + score +
                ", modelData='" + modelData + '\'' +
                ", feature=" + feature +
                '}';
    }

    public String getFacePictureId() {
        return facePictureId;
    }

    public void setFacePictureId(String facePictureId) {
        this.facePictureId = facePictureId;
    }

    public String getBackgroundPictureId() {
        return backgroundPictureId;
    }

    public void setBackgroundPictureId(String backgroundPictureId) {
        this.backgroundPictureId = backgroundPictureId;
    }

    public RectN getPosition() {
        return position;
    }

    public void setPosition(RectN position) {
        this.position = position;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getModelData() {
        return modelData;
    }

    public void setModelData(String modelData) {
        this.modelData = modelData;
    }

    public FaceFeature getFeature() {
        return feature;
    }

    public void setFeature(FaceFeature feature) {
        this.feature = feature;
    }
}
