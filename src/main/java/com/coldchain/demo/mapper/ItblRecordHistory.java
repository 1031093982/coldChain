package com.coldchain.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldchain.demo.dao.tbl_record_history;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ItblRecordHistory extends BaseMapper<tbl_record_history> {
    //增加一条历史温度记录
    @Insert("insert into tbl_record_history (own_order_id,record_time,record_tem) values(#{orderNum},#{recordTime},#{recordTem})")
    int addTemRecord(Map<String,Object> map);
    //增加一条历史路径记录
    @Insert("insert into tbl_record_history (own_order_id,record_time,record_path) values(#{orderNum},#{recordTime},#{recordPath})")
    int addPathRecord(Map<String,Object> map);
    //查询某订单编号的历史温度记录
    @Select("select record_time,record_tem from  tbl_record_history where own_order_id = #{orderNum} AND record_tem!=''")
    @Results(id = "orderMap", value = {
            @Result(column = "record_tem", property = "recordTem"),
            @Result(column = "record_time", property = "recordTime")
    })
    List<tbl_record_history> queryTemHistoryByoid(String orderNum);
    //查询某订单编号的历史路径记录
    @Select("select record_time,record_path from tbl_record_history where own_order_id = #{orderNum} AND record_path!=''")
    @Results(id = "orderMapp", value = {
            @Result(column = "record_path", property = "recordPath"),
            @Result(column = "record_time", property = "recordTime")
    })
    List<tbl_record_history> queryPathHistoryByoid(String orderNum);
}
