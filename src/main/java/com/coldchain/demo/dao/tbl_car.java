package com.coldchain.demo.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class tbl_car {
    private String carId;
    private String numberPlate="";
    private double carGpsX;
    private double carGpsY;
    private double goodsTem;
    private int deliveryTotal;
    private int isDeleted;
    private String carType;
    private String state="闲置";
    private String now_place;

    public tbl_car() {
        this.carId = UUID.randomUUID().toString();
    }

    public tbl_car(String numberPlate, double carGpsX, double carGpsY, double goodsTem, int deliveryTotal, int isDeleted, String carType, String state, String now_place) {
        this.carId = UUID.randomUUID().toString();
        this.numberPlate = numberPlate;
        this.carGpsX = carGpsX;
        this.carGpsY = carGpsY;
        this.goodsTem = goodsTem;
        this.deliveryTotal = deliveryTotal;
        this.isDeleted = isDeleted;
        this.carType = carType;
        this.state = state;
        this.now_place = now_place;
    }

    public String toJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"carId\":").append("\"").append(this.carId).append("\"").append(",");
        sb.append("\"numberPlate\":").append("\"").append(this.numberPlate).append("\"").append(",");
        sb.append("\"carType\":").append("\"").append(this.carType).append("\"").append(",");
        sb.append("\"state\":").append("\"").append(this.state).append("\"").append(",");
        sb.append("\"carGpsX\":").append("\"").append(this.carGpsX+","+this.carGpsY).append("\"").append(",");
        sb.append("\"now_place\":").append("\"").append(this.now_place).append("\"").append(",");
        sb.append("\"goodsTem\":").append("\"").append(this.goodsTem).append("\"").append(",");
        sb.append("\"deliveryTotal\":").append("\"").append(this.deliveryTotal).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
