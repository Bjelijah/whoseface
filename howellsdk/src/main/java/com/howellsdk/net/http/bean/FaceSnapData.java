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
}
