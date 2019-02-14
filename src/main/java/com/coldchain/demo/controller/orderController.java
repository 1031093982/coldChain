package com.coldchain.demo.controller;

import com.coldchain.demo.dao.tbl_order;
import com.coldchain.demo.dao.tbl_record_history;
import com.coldchain.demo.mapper.*;
import com.coldchain.demo.util.DateUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *订单相关接口
 */
@Controller
@RequestMapping("/order")
public class orderController {
    @Autowired
    ItblOrder itblOrder;
    @Autowired
    ItblOrderTem itblOrderTem;
    @Autowired
    ItblRecordHistory itblRecordHistory;
    @Autowired
    Itblcar itblcar;
    @Autowired
    ItblAccount itblAccount;
    //创建一个新的订单
    @ResponseBody
    @RequestMapping("/newOrder")
    public String newOrder(
            @RequestParam String departure,
            @RequestParam String destination,
            @RequestParam String tem_max,
            @RequestParam String car_num,
            @RequestParam String phone
    ){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;

        try{
            //订单生成时间
            String createdData = Long.toString(DateUtil.toTimestamp(DateUtil.getNow()));
            tbl_order Order = new tbl_order();
            Order.setDeparture(departure);
            Order.setDestination(destination);
            Order.setTemMax(Integer.parseInt(tem_max));
            Order.setOrderNum(createdData);
            Order.setCarNum(car_num);
            Order.setUserPhone(phone);
            itblOrder.insert(Order);
            //关联表tbl_orderTem插入创建时间
            itblOrderTem.insertorderID(createdData);
            tbl_order order = itblOrder.queryOrderOne(createdData);
            itblcar.setCarState(order.getCarNum());
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }

    //送货开始出发操作
    @RequestMapping("/startCZ")
    @ResponseBody
    public String startCZ(@RequestParam String createdData){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            String Data =Long.toString(DateUtil.toTimestamp( DateUtil.getNow()));
            Map<String,String> map = new HashMap<>();
            map.put("Data",Data);
            map.put("createdtime",createdData);

            itblOrderTem.insertorderStartTime(map);
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }

    //抵达目的地操作
    @RequestMapping("/arriveCZ")
    @ResponseBody
    public String arriveCZ(@RequestParam String createdData){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            String Data =Long.toString(DateUtil.toTimestamp( DateUtil.getNow()));
            Map<String,String> map = new HashMap<>();
            map.put("Data",Data);
            map.put("createdtime",createdData);
            itblOrderTem.insertorderArrivalTime(map);

        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }

    //卸货完成确认操作
    @RequestMapping("/finishCZ")
    @ResponseBody
    public String finishCZ(@RequestParam String createdData){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            String Data =Long.toString(DateUtil.toTimestamp( DateUtil.getNow()));
            Map<String,String> map = new HashMap<>();
            map.put("Data",Data);
            map.put("createdtime",createdData);
            itblOrderTem.insertorderFinishTime(map);
            //卸货完成时间插入
            tbl_order order = itblOrder.queryOrderOne(createdData);
            //设置车辆状态-闲置
            itblcar.setCarStateandTotald(order.getCarNum());
            //运送次数加一
            Map<String,Object > pararms = new HashMap<>();
            int total = itblcar.setCarTotaldeliver(order.getCarNum());
            pararms.put("total",total+1);
            pararms.put("number_plate",order.getCarNum());
            itblcar.setFinish(pararms);
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }

    //此订单运送货物的超限温度设置
    @RequestMapping("/settemMax")
    @ResponseBody
    public String settemMax(
            @RequestParam String createdData,
            @RequestParam double tem
    ){
        boolean isOK=true;
        String msg="";
        String body="";
        int statusCode=200;
        try{
            Map<String,Object> map = new HashMap<>();
            map.put("orderNum",createdData);
            map.put("temMax",tem);
            itblOrder.setMaxTme(map);
        }catch (Exception e){
            e.printStackTrace();
            isOK=false;
        }
        StringBuffer reStr = new StringBuffer();
        reStr.append("{\"status\":").append(isOK).append(",\"message\":\"").append(msg).append("\",\"statusCode\":").append(statusCode).append("}");
        return reStr.toString();
    }

    //订单列表
    @RequestMapping("/orderlist")
    @ResponseBody
    public String orderlist(
            @RequestParam int page,
            @RequestParam int limit,
            @RequestParam(required = false) String car_num ,
            @RequestParam String user ,
            @RequestParam(required = false) String user_phone ,
            @RequestParam(required = false) String order_num
    ){
        String phone = itblAccount.queryPhone(user);
        System.out.println("接收到的参数page"+page+"limit"+limit+"car_num"+car_num+"user_phone"+user_phone+"order_num"+order_num);
        Map<String,Object> map = new HashMap<>();
        map.put("order_num",order_num);
        map.put("car_num",car_num);
        map.put("user_phone",phone);
        map.put("page",(page-1)*limit);
        map.put("limit",limit);
        List<tbl_order> list = itblOrder.queryorderlist(map);
        int count = itblOrder.queryorderlistCount(map);
        JSONArray fromObject = JSONArray.fromObject(list);

        StringBuffer result=  new StringBuffer();
        result.append("{\"count\":").append(count).append(",\"msg\":\"\",\"code\":0,\"data\":");
        result.append(fromObject.toString());
        result.append("}");
        return result.toString();
    }
    //历史温度记录
    @RequestMapping("/temListHis")
    @ResponseBody
    public String temListHis(
            @RequestParam String orderNum
    ){
       StringBuffer sb = new StringBuffer();
       sb.append("[");
       List<tbl_record_history> list =  itblRecordHistory.queryTemHistoryByoid(orderNum);
       for(tbl_record_history o:list){
           sb.append("{\"value\":").append(o.getRecordTem()).append(",\"name\":").append("\"").append(o.getRecordTime()).append("\"").append("},");
       }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
    //历史路径查询
    @RequestMapping("/pathListHis")
    @ResponseBody
    public String pathListHis(
            @RequestParam String orderNum
    ){
        List<tbl_record_history> list =  itblRecordHistory.queryPathHistoryByoid(orderNum);
        JSONArray jsonArray = JSONArray.fromObject(list);
        return jsonArray.toString();
    }

}
