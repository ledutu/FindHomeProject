package com.example.findhomeproject.modelForMotel;

import android.media.Image;

import java.io.Serializable;

public class MotelNews implements Serializable {
    private int motelCost;
    private int motelImage;
    private String motelName;
    private String motelAddress;
    private String phoneNumber;
    private String motelArea;
    private String timePosting;
    private String motelDetail;

    public MotelNews() {
    }

    public MotelNews(int motelCost, int motelImage, String motelName, String motelAddress, String phoneNumber, String motelArea, String timePosting, String motelDetail) {
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.phoneNumber = phoneNumber;
        this.motelArea = motelArea;
        this.timePosting = timePosting;
        this.motelDetail = motelDetail;
    }

    public int getMotelCost() {
        return motelCost;
    }

    public void setMotelCost(int motelCost) {
        this.motelCost = motelCost;
    }

    public int getMotelImage() {
        return motelImage;
    }

    public void setMotelImage(int motelImage) {
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
