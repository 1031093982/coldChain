package com.coldchain.demo.controller;

import com.coldchain.demo.dao.tbl_order;
import com.coldchain.demo.mapper.ItblOrder;
import com.coldchain.demo.mapper.ItblRecordHistory;
import com.coldchain.demo.mapper.Itblcar;
import com.coldchain.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/record")
public class HistemAndPathRecordController {
    @Autowired
    ItblOrder itblOrder;
    @Autowired
    ItblRecordHistory itblRecordHistory;
    @Autowired
    Itblcar itbl_car;
    //记录一条历史温度记录
    @RequestMapping("/recordTem")
    @ResponseBody
    public void recordTem(
            @RequestParam String orderNum,
            @RequestParam String tem
    ){
        String createdData = Long.toString(DateUtil.toTimestamp(DateUtil.getNow()));
        tbl_order tblOrder = itblOrder.queryOrderOne(orderNum);
        Map<String,Object> map = new HashMap<>();
        map.put("orderNum",orderNum);
        map.put("recordTime",createdData);
        map.put("recordTem",tem);
        map.put("tem",tem);
        map.put("number_plate",tblOrder.getCarNum());
        itblRecordHistory.addTemRecord(map);
        itbl_car.setCARtem(map);
    }
    //记录一条历史路径信息
    @RequestMapping("/recordPath")
    @ResponseBody
    public void recordPath(
            @RequestParam String orderNum,
            @RequestParam String path
    ){
        String createdData = Long.toString(DateUtil.toTimestamp(DateUtil.getNow()));
        tbl_order tblOrder = itblOrder.queryOrderOne(orderNum);
        Map<String,Object> map = new HashMap<>();
        map.put("orderNum",orderNum);
        map.put("recordTime",createdData);
        map.put("recordPath",path);
        map.put("nowPlace",path);
        map.put("number_plate",tblOrder.getCarNum());
        itblRecordHistory.addPathRecord(map);
        itbl_car.setCarNowplace(map);
    }
    //设置车辆当前坐标信息
    @RequestMapping("/carplace")
    @ResponseBody
    public void carplace(
            @RequestParam String orderNum,
            @RequestParam(required = false) String x,
            @RequestParam (required = false)String y
    ){
        tbl_order order = itblOrder.queryOrderOne(orderNum);
        Map<String,Object> map = new HashMap<>();
        map.put("x",x);
        map.put("y",y);
        map.put("number_plate",order.getCarNum());
        itbl_car.setCARxy(map);
    }


}
