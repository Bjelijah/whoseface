package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceAppendData {
    @SerializedName("Id") String id;
    @SerializedName("Name") String name;
    @SerializedName("Sex") String sex;
    @SerializedName("Phone") String phone;
    @SerializedName("BirthDate") String birthDate;
    @SerializedName("Province") String province;
    @SerializedName("City") String city;
    @SerializedName("CardType") String cardType;
    @SerializedName("CardNumber") String cardNumber;
    @SerializedName("EmployeeId") String employeeId;
    @SerializedName("Extend") String extend;
    @SerializedName("PictureId")String pictureId;

    @Override
    public String toString() {
        return "FaceAppendData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cardType='" + cardType + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", extend='" + extend + '\'' +
                ", pictureId='" + pictureId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public FaceAppendData() {

    }

    public FaceAppendData(String id, String name, String sex, String phone, String birthDate, String province, String city, String cardType, String cardNumber, String employeeId, String extend, String pictureId) {

        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.birthDate = birthDate;
        this.province = province;
        this.city = city;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.employeeId = employeeId;
        this.extend = extend;
        this.pictureId = pictureId;
    }
}
