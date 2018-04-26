package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

public class FaceDetectDevice {
    @SerializedName("Id") String id;
    @SerializedName("CreationTime") String createTime;
    @SerializedName("Name") String name;
    @SerializedName("Manufacturer") String manufacturer;
    @SerializedName("Model")String model;
    @SerializedName("Firmware")String firmware;
    @SerializedName("SerialNumber") String serialNumber;
    @SerializedName("PointOfSale") String pointOfSale;
    @SerializedName("Information")String information;
    @SerializedName("Username") String username;
    @SerializedName("Password") String password;
    @SerializedName("Uri") String uri;
    @SerializedName("ProtocolType") String protocolType;
    @SerializedName("ParentDeviceId") String parentDeviceId;
    @SerializedName("AccessId") String accessId;
    @SerializedName("TimeSynchronizing") Boolean timeSynchroniziog;
    @SerializedName("ResetTime") String resetTime;
    @SerializedName("StructuredAbilities")Integer structuredAbilities;
    @SerializedName("LastNSeconds") Integer lastNSeconds;
    @SerializedName("ExistedInDatabase") Boolean existedInDatabase;
    @SerializedName("DeviceStatus") FaceDetectDeviceStatus deviceStatus;
    @SerializedName("Threshold") FaceDetectThreshold threshold;
    @SerializedName("NetworkInterface") NetworkInterface networkInterface;

    @Override
    public String toString() {
        return "FaceDetectDevice{" +
                "id='" + id + '\'' +
                ", createTime='" + createTime + '\'' +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", firmware='" + firmware + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", pointOfSale='" + pointOfSale + '\'' +
                ", information='" + information + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", uri='" + uri + '\'' +
                ", protocolType='" + protocolType + '\'' +
                ", parentDeviceId='" + parentDeviceId + '\'' +
                ", accessId='" + accessId + '\'' +
                ", timeSynchroniziog=" + timeSynchroniziog +
                ", resetTime='" + resetTime + '\'' +
                ", structuredAbilities=" + structuredAbilities +
                ", lastNSeconds=" + lastNSeconds +
                ", existedInDatabase=" + existedInDatabase +
                ", deviceStatus=" + deviceStatus +
                ", threshold=" + threshold +
                ", networkInterface=" + networkInterface +
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getPointOfSale() {
        return pointOfSale;
    }

    public void setPointOfSale(String pointOfSale) {
        this.pointOfSale = pointOfSale;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getParentDeviceId() {
        return parentDeviceId;
    }

    public void setParentDeviceId(String parentDeviceId) {
        this.parentDeviceId = parentDeviceId;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public Boolean getTimeSynchroniziog() {
        return timeSynchroniziog;
    }

    public void setTimeSynchroniziog(Boolean timeSynchroniziog) {
        this.timeSynchroniziog = timeSynchroniziog;
    }

    public String getResetTime() {
        return resetTime;
    }

    public void setResetTime(String resetTime) {
        this.resetTime = resetTime;
    }

    public Integer getStructuredAbilities() {
        return structuredAbilities;
    }

    public void setStructuredAbilities(Integer structuredAbilities) {
        this.structuredAbilities = structuredAbilities;
    }

    public Integer getLastNSeconds() {
        return lastNSeconds;
    }

    public void setLastNSeconds(Integer lastNSeconds) {
        this.lastNSeconds = lastNSeconds;
    }

    public Boolean getExistedInDatabase() {
        return existedInDatabase;
    }

    public void setExistedInDatabase(Boolean existedInDatabase) {
        this.existedInDatabase = existedInDatabase;
    }

    public FaceDetectDeviceStatus getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(FaceDetectDeviceStatus deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public FaceDetectThreshold getThreshold() {
        return threshold;
    }

    public void setThreshold(FaceDetectThreshold threshold) {
        this.threshold = threshold;
    }

    public NetworkInterface getNetworkInterface() {
        return networkInterface;
    }

    public void setNetworkInterface(NetworkInterface networkInterface) {
        this.networkInterface = networkInterface;
    }

    public FaceDetectDevice() {

    }

    public FaceDetectDevice(String id, String createTime, String name, String manufacturer, String model, String firmware, String serialNumber, String pointOfSale, String information, String username, String password, String uri, String protocolType, String parentDeviceId, String accessId, Boolean timeSynchroniziog, String resetTime, Integer structuredAbilities, Integer lastNSeconds, Boolean existedInDatabase, FaceDetectDeviceStatus deviceStatus, FaceDetectThreshold threshold, NetworkInterface networkInterface) {

        this.id = id;
        this.createTime = createTime;
        this.name = name;
        this.manufacturer = manufacturer;
        this.model = model;
        this.firmware = firmware;
        this.serialNumber = serialNumber;
        this.pointOfSale = pointOfSale;
        this.information = information;
        this.username = username;
        this.password = password;
        this.uri = uri;
        this.protocolType = protocolType;
        this.parentDeviceId = parentDeviceId;
        this.accessId = accessId;
        this.timeSynchroniziog = timeSynchroniziog;
        this.resetTime = resetTime;
        this.structuredAbilities = structuredAbilities;
        this.lastNSeconds = lastNSeconds;
        this.existedInDatabase = existedInDatabase;
        this.deviceStatus = deviceStatus;
        this.threshold = threshold;
        this.networkInterface = networkInterface;
    }
}
