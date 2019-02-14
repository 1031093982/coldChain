package com.coldchain.demo.dao;

import lombok.Data;

@Data
public class tbl_order_tem {
    private String orderId;
    private String startTime;
    private String arrivalTime;
    private String finishTime;

    public tbl_order_tem() {
    }
    public tbl_order_tem(String orderId, String startTime, String arrivalTime, String finishTime) {
        this.orderId = orderId;
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
        this.finishTime = finishTime;
    }
    public String toJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"orderId\":").append("\"").append(this.orderId).append("\"").append(",");
        sb.append("\"startTime\":").append("\"").append(this.startTime).append("\"").append(",");
        sb.append("\"arrivalTime\":").append("\"").append(this.arrivalTime).append("\"").append(",");
        sb.append("\"finishTime\":").append("\"").append(this.finishTime).append("\"").append(",");
        sb.append("}");
        return sb.toString();
    }
    @Override
    public String toString() {
        return "tbl_order_tem{" +
                "orderId='" + orderId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
