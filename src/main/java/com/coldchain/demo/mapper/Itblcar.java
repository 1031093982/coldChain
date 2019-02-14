package com.coldchain.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coldchain.demo.dao.tbl_car;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface Itblcar extends BaseMapper<tbl_car> {
    //分页查询车辆
    @Select("select * from tbl_car where is_deleted= 0 limit #{page},#{limit}")
    @Results(id="carys",value = {
            @Result(column = "car_id",property = "carId"),
            @Result(column = "number_plate",property = "numberPlate"),
            @Result(column = "car_gps_x",property = "carGpsX"),
            @Result(column = "car_gps_x",property = "carGpsY"),
            @Result(column = "goods_tem",property = "goodsTem"),
            @Result(column = "delivery_total",property = "deliveryTotal"),
            @Result(column = "is_deleted",property = "isDeleted"),
            @Result(column = "car_type",property = "carType")
    } )
    List<tbl_car>  queryCar(Map<String,Object> map);
    //车辆总数
    @Select("select count(1) from tbl_car  where is_deleted= 0 ")
   int queryCarCount();
    //删除车辆
    @Update("update  tbl_car set is_deleted=1 where car_id=#{carId}")
    int delCar(String carId);
    //用车牌号查找车辆信息
    @Select("select * from tbl_car where is_deleted= 0 and number_plate=#{number_plate} ")
    @Results(value = {
            @Result(column = "car_id",property = "carId"),
            @Result(column = "number_plate",property = "numberPlate"),
            @Result(column = "car_gps_x",property = "carGpsX"),
            @Result(column = "car_gps_y",property = "carGpsY"),
            @Result(column = "goods_tem",property = "goodsTem"),
            @Result(column = "delivery_total",property = "deliveryTotal"),
            @Result(column = "is_deleted",property = "isDeleted"),
            @Result(column = "car_type",property = "carType")
    } )
    tbl_car queryonecar(String number_plate );
    //设置车辆状态-》运送中
    @Update("update tbl_car set state=\'运送中\' where number_plate=#{number_plate}")
    int setCarState(String number_plate);
    //设置车辆状态-》闲置
    @Update("update tbl_car set  state=\'闲置\' where number_plate=#{number_plate}")
    int setCarStateandTotald(String number_plate);
    //车辆运输次数+1
    @Select("select delivery_total from tbl_car where number_plate=#{number_plate}" )
    int setCarTotaldeliver(String number_plate);
    @Update("update tbl_car set delivery_total = #{total} where number_plate=#{number_plate} ")
    int setFinish(Map<String,Object> map);
    //设置车辆x,y坐标
    @Update("update tbl_car set car_gps_x = #{x},car_gps_y = #{y} where number_plate = #{number_plate} ")
    int setCARxy(Map<String,Object> map );
    //更新车辆货厢温度
    @Update("update tbl_car set goods_tem = #{tem} where number_plate=#{number_plate}")
    int setCARtem(Map <String,Object> map);
    //设置车辆现在所在地
    @Update("update tbl_car set now_place =#{nowPlace} where number_plate=#{number_plate}")
    int setCarNowplace(Map<String,Object> map);
}
