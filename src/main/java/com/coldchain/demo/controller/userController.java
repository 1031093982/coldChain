package com.coldchain.demo.controller;

import com.coldchain.demo.dao.tblAccount;
import com.coldchain.demo.dao.tbl_car;
import com.coldchain.demo.dao.tbl_user;
import com.coldchain.demo.mapper.ItblAccount;
import com.coldchain.demo.mapper.ItblUser;
import com.coldchain.demo.mapper.Itblcar;
import net.sf.json.JSONArray;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    Itblcar itblcar;
    @Autowired
    ItblAccount itblAccount;
    @Autowired
    ItblUser itblUser;
    @RequestMapping("/checklogin")
    public ModelAndView checkLogin(
            @RequestParam String username,
            @RequestParam String password
           ){
        Subject subject = SecurityUtils.getSubject();
        System.out.println("contro密码"+password);
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        ModelAndView mav = new ModelAndView();
        mav.addObject("username",username);
        mav.setViewName("index");
        try{
            if(!subject.isAuthenticated()){
                subject.login(token);
            }else {
                System.out.println("您已经登陆过了！");
            }
            //只要不报异常就登陆成功
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            mav.setViewName("login");
        }catch (UnknownAccountException e){
           e.printStackTrace();
            mav.setViewName("login");
        }catch (AuthenticationException e){
            e.printStackTrace();
            mav.setViewName("login");
        }
        return mav;
    }
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping("/registerUser")
    @ResponseBody
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String phone

            ){
        try {
            tblAccount tblAccount = new tblAccount();
            tblAccount.setAccount(username);
            tblAccount.setPhone(phone);
            tblAccount.setPwd(password);
            int result= itblAccount.addAccount(tblAccount);
            if(result!=1){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println(e);
            return "false";
        }
        return "true";
    }
    //信息认证
    @RequestMapping("/UserAuth")
    @ResponseBody
    public String UserAuth(
            tbl_user tblUser
    ){
        System.out.println(tblUser.toString());
        itblUser.insert(tblUser);
        return "认证成功";
    }
    //增加设备车辆
    @RequestMapping("/addCar")
    @ResponseBody
    public String addCar(
            @RequestParam String numPlate,
            @RequestParam String carType
    ){
        try{
            tbl_car car = new tbl_car();
            car.setNumberPlate(numPlate);
            car.setCarType(carType);
            itblcar.insert(car);
        }catch (Exception e){
            return "增加失败";
        }
        return "增加成功";
    }
    //查询所有设备车辆
    @RequestMapping("/queryCar")
    @ResponseBody
    public String queryCAR(
            @RequestParam int page,
            @RequestParam int limit
    ){
            Map<String,Object> map= new HashMap<>();
            map.put("page",(page-1)*limit);
            map.put("limit",limit);
          List<tbl_car> list =  itblcar.queryCar(map);
          int count = itblcar.queryCarCount();
          JSONArray fromObject = JSONArray.fromObject(list);
            StringBuffer result=  new StringBuffer();
            result.append("{\"count\":").append(count).append(",\"msg\":\"\",\"code\":0,\"data\":");
            result.append("[");
            int i =0;
            for (tbl_car car:list){
                if(i!=0){
                    result.append(",");
                }
                result.append(car.toJson());
                i++;
            }
            result.append("]}");
        System.out.println(fromObject.toString());
            return result.toString();
}
    //删除某个设备车辆
    @RequestMapping("/delCar")
    @ResponseBody
    public String delCar(
            @RequestParam String carid
    ){
        int i =itblcar.delCar(carid);
        if(i!=1){
            return "删除失败！";
        }
        return "删除成功！";
    }
}


