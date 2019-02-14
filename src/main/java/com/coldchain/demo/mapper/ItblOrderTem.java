package com.coldchain.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldchain.demo.dao.tbl_order_tem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface ItblOrderTem extends BaseMapper<tbl_order_tem> {
    @Insert("insert into tbl_order_tem (order_id) values (#{createdtime})")
    int insertorderID(String createdtime);
    @Update("update  tbl_order_tem set start_time =#{Data} where order_id=#{createdtime}")
    int insertorderStartTime(Map<String,String> map);
    @Update("update  tbl_order_tem set arrival_time =#{Data} where order_id=#{createdtime}")
    int insertorderArrivalTime(Map<String,String> map);
    @Update("update  tbl_order_tem set finish_time =#{Data} where order_id=#{createdtime}")
    int insertorderFinishTime(Map<String,String> map);
}

