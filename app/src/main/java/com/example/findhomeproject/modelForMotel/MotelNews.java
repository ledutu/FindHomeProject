package com.example.findhomeproject.modelForMotel;

import java.io.Serializable;
import java.util.ArrayList;

public class MotelNews implements Serializable {
    private int id;
    private String motelCost;
    private String motelImage;
    private String motelName;
    private String motelAddress;
    private String phoneNumber;
    private String motelArea;
    private String timePosting;
    private String motelDetail;
    private int check;
    private String keyMotelAddress;
    private String user;

    public MotelNews() {
    }

    public MotelNews(
            int id,
            String motelCost,
            String motelImage,
            String motelName,
            String motelAddress,
            String phoneNumber,
            String motelArea,
            String timePosting,
            String motelDetail
    ) {
        this.id = id;
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.phoneNumber = phoneNumber;
        this.motelArea = motelArea;
        this.timePosting = timePosting;
        this.motelDetail = motelDetail;
    };

    //add User
    public MotelNews(int id, String motelCost, String motelImage, String motelName, String motelAddress, String phoneNumber, String motelArea, String timePosting, String motelDetail, String keyMotelAddress, String user) {
        this.id = id;
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.phoneNumber = phoneNumber;
        this.motelArea = motelArea;
        this.timePosting = timePosting;
        this.motelDetail = motelDetail;
        this.keyMotelAddress = keyMotelAddress;
        this.user = user;
    }

    //add addressKey
    public MotelNews(int id, String motelCost, String motelImage, String motelName, String motelAddress, String phoneNumber, String motelArea, String timePosting, String motelDetail, String keyMotelAddress) {
        this.id = id;
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.phoneNumber = phoneNumber;
        this.motelArea = motelArea;
        this.timePosting = timePosting;
        this.motelDetail = motelDetail;
        this.keyMotelAddress = keyMotelAddress;
    }

    public MotelNews(int id, String motelCost, String motelImage, String motelName, String motelAddress, String phoneNumber, String motelArea, String timePosting, String motelDetail, int check) {
        this.id = id;
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.phoneNumber = phoneNumber;
        this.motelArea = motelArea;
        this.timePosting = timePosting;
        this.motelDetail = motelDetail;
        this.check = check;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKeyMotelAddress() {
        return keyMotelAddress;
    }

    public void setKeyMotelAddress(String keyMotelAddress) {
        this.keyMotelAddress = keyMotelAddress;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotelCost() {
        return motelCost;
    }

    public void setMotelCost(String motelCost) {
        this.motelCost = motelCost;
    }

    public String getMotelImage() {
        return motelImage;
    }

    public void setMotelImage(String motelImage) {
        this.motelImage = motelImage;
    }

    public String getMotelName() {
        return motelName;
    }

    public void setMotelName(String motelName) {
        this.motelName = motelName;
    }

    public String getMotelAddress() {
        return motelAddress;
    }

    public void setMotelAddress(String motelAddress) {
        this.motelAddress = motelAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMotelArea() {
        return motelArea;
    }

    public void setMotelArea(String motelArea) {
        this.motelArea = motelArea;
    }

    public String getTimePosting() {
        return timePosting;
    }

    public void setTimePosting(String timePosting) {
        this.timePosting = timePosting;
    }

    public String getMotelDetail() {
        return motelDetail;
    }

    public void setMotelDetail(String motelDetail) {
        this.motelDetail = motelDetail;
    }
}
