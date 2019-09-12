package com.example.findhomeproject.modelForMotel;

import android.media.Image;

import java.io.Serializable;

public class MotelNews implements Serializable {
    private int motelCost;
    private String motelName;
    private String motelAddress;
    private String motelArea;
    private String motelDetail;
    private String timePosting;
    private int motelImage;
    public MotelNews(int motelCost, int motelImage ,String motelAddress, String motelArea) {
        this.motelCost = motelCost;
        this.motelImage = motelImage;
        this.motelAddress = motelAddress;
        this.motelArea = motelArea;
    }

    public MotelNews(String motelName, String motelAddress) {
        this.motelName = motelName;
        this.motelAddress = motelAddress;
    }

    public MotelNews(String motelName, String motelAddress, int motelImage) {
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.motelImage = motelImage;
    }

    public MotelNews(int motelCost, String motelName, String motelAddress, String motelArea, String motelDetail, String timePosting, int motelImage) {
        this.motelCost = motelCost;
        this.motelName = motelName;
        this.motelAddress = motelAddress;
        this.motelArea = motelArea;
        this.motelDetail = motelDetail;
        this.timePosting = timePosting;
        this.motelImage = motelImage;
    }

    public int getMotelCost() {
        return motelCost;
    }

    public void setMotelCost(int motelCost) {
        this.motelCost = motelCost;
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

    public String getMoteArea() {
        return motelArea;
    }

    public void setMoteArea(String moteArea) {
        this.motelArea = moteArea;
    }

    public String getMotelDetail() {
        return motelDetail;
    }

    public void setMotelDetail(String motelDetail) {
        this.motelDetail = motelDetail;
    }

    public String getTimePosting() {
        return timePosting;
    }

    public void setTimePosting(String timePosting) {
        this.timePosting = timePosting;
    }

    public int getMotelImage() {
        return motelImage;
    }

    public void setMotelImage(int motelImage) {
        this.motelImage = motelImage;
    }
}
