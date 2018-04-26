package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceFeature {
    @SerializedName("Age") Integer age;
    @SerializedName("AgeConfidence") Integer ageConfidence;
    @SerializedName("Sex") String sex;
    @SerializedName("SexConfidence") Integer sexConfidence;
    @SerializedName("EyeGlass") Boolean eyeGlass;

    @Override
    public String toString() {
        return "FaceFeature{" +
                "age=" + age +
                ", ageConfidence=" + ageConfidence +
                ", sex='" + sex + '\'' +
                ", sexConfidence=" + sexConfidence +
                ", eyeGlass=" + eyeGlass +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeConfidence() {
        return ageConfidence;
    }

    public void setAgeConfidence(Integer ageConfidence) {
        this.ageConfidence = ageConfidence;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getSexConfidence() {
        return sexConfidence;
    }

    public void setSexConfidence(Integer sexConfidence) {
        this.sexConfidence = sexConfidence;
    }

    public Boolean getEyeGlass() {
        return eyeGlass;
    }

    public void setEyeGlass(Boolean eyeGlass) {
        this.eyeGlass = eyeGlass;
    }

    public FaceFeature() {

    }

    public FaceFeature(Integer age, Integer ageConfidence, String sex, Integer sexConfidence, Boolean eyeGlass) {

        this.age = age;
        this.ageConfidence = ageConfidence;
        this.sex = sex;
        this.sexConfidence = sexConfidence;
        this.eyeGlass = eyeGlass;
    }
}
