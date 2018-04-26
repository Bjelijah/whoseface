package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceServiceVersion {
    @SerializedName("Version") String version;
    @SerializedName("BuildDate") String buildDate;
    @SerializedName("Company") String company;

    @Override
    public String toString() {
        return "FaceServiceVersion{" +
                "version='" + version + '\'' +
                ", buildDate='" + buildDate + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public FaceServiceVersion() {

    }

    public FaceServiceVersion(String version, String buildDate, String company) {

        this.version = version;
        this.buildDate = buildDate;
        this.company = company;
    }
}
