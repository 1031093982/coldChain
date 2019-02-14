package com.coldchain.demo.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class tbl_order {
//    @TableField("user_phone")
    private String userPhone;
//    @TableField("order_id")
    private String orderId;
//    @TableField("order_num")
    private String orderNum="";
    private String departure;
    private String destination;
//    @TableField("tem_status")
    private int temStatus=0;
//    @TableField("tem_max")
    private double temMax=0;
//    @TableField("is_deleted")
    private int isDeleted=0;
//    @TableField("car_num")
    private String carNum;

    public String toJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"orderId\":").append("\"").append(this.orderId).append("\"").append(",");
        sb.append("\"orderNum\":").append("\"").append(this.orderNum).append("\"").append(",");
        sb.append("\"departure\":").append("\"").append(this.departure).append("\"").append(",");
        sb.append("\"destination\":").append("\"").append(this.destination).append("\"").append(",");
        sb.append("\"temMax\":").append("\"").append(this.temMax).append("\"").append(",");
        sb.append("\"temStatus\":").append("\"").append(this.temStatus).append("\"");
        sb.append("\"carNum\":").append("\"").append(this.carNum).append("\"");
        sb.append("\"userPhone\":").append("\"").append(this.userPhone).append("\"");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "tbl_order{" +
                "userPhone='" + userPhone + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", temStatus=" + temStatus +
                ", temMax=" + temMax +
                ", isDeleted=" + isDeleted +
                ", carNum='" + carNum + '\'' +
                '}';
    }

    public tbl_order(String userPhone, String orderNum, String departure, String destination, int temStatus, double temMax, int isDeleted, String carNum) {
        this.userPhone = userPhone;
        this.orderId =  UUID.randomUUID().toString();
        this.orderNum = orderNum;
        this.departure = departure;
        this.destination = destination;
        this.temStatus = temStatus;
        this.temMax = temMax;
        this.isDeleted = isDeleted;
        this.carNum = carNum;
    }


    public tbl_order() {
        this.orderId =  UUID.randomUUID().toString();
    }
}
