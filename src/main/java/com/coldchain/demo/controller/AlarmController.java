package com.coldchain.demo.controller;

import com.coldchain.demo.dao.tbl_order;
import com.coldchain.demo.mapper.ItblOrder;
import com.coldchain.demo.mapper.Itblcar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    ItblOrder itblOrder;
    @Autowired
    Itblcar itblcar;
    //传感器端每隔一段时间调用接口，将电池与温度信息录入数据库，服务器端反馈信息
    //监测电池电量，若电量不足，则报警
    @ResponseBody
    @RequestMapping("/elcAlarm")
    public String alarmElc(@RequestParam double ele) {
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            if(ele<0.2)
            {
                body="您的货厢电量已不足20%！！";
            }else{
                body="电量大于20%";
            }
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"body\":").append(body).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }
    //监测货厢温度，若超过上限，则报警
    @ResponseBody
    @RequestMapping("/temAlarm")
    public String temAlarm(@RequestParam double tem,
                           @RequestParam String orderNum){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            tbl_order order = itblOrder.queryOrderOne(orderNum);
            //更新车辆货厢温度
            Map<String,Object> map = new HashMap<>();
            map.put("tem",tem);
            map.put("number_plate",order.getCarNum());
            itblcar.setCARtem(map);
            Double MAX_TEM = itblOrder.queryMaxTem(orderNum);
            if(tem>MAX_TEM)
            {
                body="您的货厢温度较高，已超过上限温度"+MAX_TEM+"C°,不适宜存储！";
            }else{
                body="货厢温度："+tem+"未超过上限温度";
            }
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"body\":").append(body).append("\"\",statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }
    //预抵达目的地提醒
    @RequestMapping("/arrAlarm")
    @ResponseBody
    public String arrAlarm(
            @RequestParam String orderNum
    ){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            //根据orderNum查询订单用户
            String phone = itblOrder.queryPhone(orderNum);
            //发送给用户信息
            //todo
            // 将抵达信息发送给了用户phone
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
            msg="发送信息失败，请重试";
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"body\":").append(body).append("\"\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }
}
