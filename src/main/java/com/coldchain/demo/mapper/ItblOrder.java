package com.coldchain.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldchain.demo.dao.tbl_order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface ItblOrder extends BaseMapper<tbl_order> {
    //根据订单编号查询超限温度
    @Select("select tem_max from tbl_order where order_num=#{orderNum} and is_deleted=0")
    double queryMaxTem(String orderNum);

    //根据订单编号查询手机号
    @Select("select user_phone from tbl_order where order_num=#{orderNum} and is_deleted=0")
    String queryPhone(String orderNum);

    //设置超限温度
    @Update("update tbl_order set tem_max = #{temMax} where order_num = #{orderNum}")
    int setMaxTme(Map<String,Object> map);

    /**
     * 查询订单列表
     * @param map
     * @return
     */
    @SelectProvider(type = com.coldchain.demo.mappersql.ItblOrdersql.class,method ="queryorderlist")
    @Results( value = {
            @Result(column = "order_num", property = "orderNum", javaType = String.class),
            @Result(column = "car_num", property = "carNum", javaType = String.class),
            @Result(column = "user_phone", property = "userPhone", javaType = String.class),
            @Result(column = "tem_max", property = "temMax", javaType = Double.class) ,
            @Result(column = "tem_status", property = "temStatus") })
    List<tbl_order> queryorderlist(Map<String,Object> map);

    //查询订单列表总数
    @SelectProvider(type = com.coldchain.demo.mappersql.ItblOrdersql.class,method ="queryorderlistCount")
    int queryorderlistCount(Map<String,Object> map);

    //获取一个订单的信息
    @Select("select * from tbl_order where order_num =#{orderId}")
    @Results(id = "orderMap", value = {
            @Result(column = "order_num", property = "orderNum", javaType = String.class),
            @Result(column = "car_num", property = "carNum", javaType = String.class),
            @Result(column = "user_phone", property = "userPhone", javaType = String.class),
            @Result(column = "tem_max", property = "temMax", javaType = Double.class) ,
            @Result(column = "tem_status", property = "temStatus") })
    tbl_order queryOrderOne(String orderId);
}
