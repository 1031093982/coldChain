package com.coldchain.demo.dao;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("tbl_account")
public class tblAccount {
    private String account;
    private String pwd;
    private String phone;

    public tblAccount(String account, String pwd, String phone) {
        this.account = account;
        this.pwd = pwd;
        this.phone = phone;
    }

    public tblAccount() {

    }

    @Override
    public String toString() {
        return "tblAccount{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
