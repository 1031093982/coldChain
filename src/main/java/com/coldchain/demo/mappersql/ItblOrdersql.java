package com.coldchain.demo.mappersql;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
public class ItblOrdersql {
    public String queryorderlist(Map<String, Object> param) {
        String sql =  new SQL() {
            {
                SELECT("order_num,car_num,departure,destination,user_phone,tem_max");
                FROM("tbl_order");
                if (param.get("user_phone")!=null && !param.get("user_phone").toString().isEmpty() ) {
                    WHERE("user_phone=#{user_phone}");
                }
                if(param.get("order_num")!=null&& !param.get("order_num").toString().isEmpty()) {
                    WHERE("order_num=#{order_num}");
                }
                if(param.get("car_num")!=null&& !param.get("car_num").toString().isEmpty()) {
                    WHERE("car_num like #{car_num} ");
                }
            }
        }.toString();
        StringBuffer SQL = new StringBuffer();
        SQL.append(sql).append(" limit #{page},#{limit}");
        return SQL.toString();
    }
    public String queryorderlistCount(Map<String, Object> param) {
        String sql = new SQL() {
            {
                SELECT("count(1)");
                FROM("tbl_order");
                if (param.get("user_phone")!=null && !param.get("user_phone").toString().isEmpty() ) {
                    WHERE("user_phone=#{user_phone}");
                }
                if(param.get("order_num")!=null&& !param.get("order_num").toString().isEmpty()) {
                    WHERE("order_num=#{order_num}");
                }
                if(param.get("car_num")!=null&& !param.get("car_num").toString().isEmpty()) {
                    WHERE("car_num=#{car_num}");
                }
            }
        }.toString();
        return sql;
    }

}
