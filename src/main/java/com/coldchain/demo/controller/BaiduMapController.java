package com.coldchain.demo.controller;

import com.coldchain.demo.dao.tbl_car;
import com.coldchain.demo.dao.tbl_order;
import com.coldchain.demo.mapper.ItblOrder;
import com.coldchain.demo.mapper.ItblRecordHistory;
import com.coldchain.demo.mapper.Itblcar;
import com.coldchain.demo.util.HttpUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 关于地图使用的接口
 */
@Controller
@RequestMapping("/map")
public class BaiduMapController {
    @Autowired
    ItblOrder itblOrder;
    @Autowired
    ItblRecordHistory itblRecordHistory;
    @Autowired
    Itblcar itbl_car;
    //获取起始地地址与目标地地址的坐标、以及目前位置
    @RequestMapping("/pathinfo")
    @ResponseBody
    public String getPathinfo(
            @RequestParam String orderId
    ){
       tbl_order tblOrder = itblOrder.queryOrderOne(orderId);
       //起始地
       String departure   =  HttpUtil.getaddress(tblOrder.getDeparture());
       //目标地
       String destination =  HttpUtil.getaddress(tblOrder.getDestination());
       //目前位置
       tbl_car car =itbl_car.queryonecar(tblOrder.getCarNum());
       //获取位置与温度
        String nowplace=car.getNow_place();
        System.out.println(nowplace);
       if(null==nowplace){
           nowplace=departure;
       }else{
           nowplace =HttpUtil.getaddress(car.getNow_place());
       }
       String tem = Double.toString(car.getGoodsTem());
        System.out.println(nowplace);
        List<String> path = new ArrayList<>();
        path.add(departure);
        path.add(nowplace);
        path.add(destination);
       JSONArray fromObject = JSONArray.fromObject(path);
       StringBuffer sb =new StringBuffer();
       sb.append("{");
       sb.append("\"tem\":");
       sb.append("\"").append(tem).append("\",");
       sb.append("\"arr\":").append(fromObject.toString());
       sb.append("}");
       return sb.toString();
    }
}
