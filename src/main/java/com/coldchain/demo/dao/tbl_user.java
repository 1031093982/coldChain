package com.coldchain.demo.dao;

import lombok.Data;

import java.util.UUID;

@Data
public class tbl_user {
    private String uuId;
    private String name;
    private String idCard;
    private String phone;
    private String age;
    private String sex;
    private String userType;
    private int isDeleted;
    public String toJson(){
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"uuId\":").append("\"").append(this.uuId).append("\"").append(",");
        sb.append("\"name\":").append("\"").append(this.name).append("\"").append(",");
        sb.append("\"idCard\":").append("\"").append(this.idCard).append("\"").append(",");
        sb.append("\"phone\":").append("\"").append(this.phone).append("\"").append(",");
        sb.append("\"age\":").append("\"").append(this.age).append("\"").append(",");
        sb.append("\"sex\":").append("\"").append(this.sex).append("\"").append(",");
        sb.append("\"userType\":").append("\"").append(this.userType).append("\"").append(",");
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "tbl_user{" +
                "uuId='" + uuId + '\'' +
                ", name='" + name + '\'' +
                ", idCard='" + idCard + '\'' +
                ", phone=" + phone +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", userType='" + userType + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public tbl_user(String uuId, String name, String idCard, String phone, String age, String sex, String userType, int isDeleted) {
        this.uuId = UUID.randomUUID().toString();
        this.name = name;
        this.idCard = idCard;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.userType = userType;
        this.isDeleted = isDeleted;
    }

    public tbl_user() {
        this.uuId = UUID.randomUUID().toString();
    }
}
