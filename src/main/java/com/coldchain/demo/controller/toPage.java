package com.coldchain.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class toPage {
    @RequestMapping("/")
    public String toIndex(){return "index"; }
    @RequestMapping("/register")
    public String toHello(){return "register";}
    @RequestMapping("/equipmanage")
    public String toUpdate(){return "equipmanage";}
    @RequestMapping("/login")
    public String toLogin(){return "login";}
    @RequestMapping("/logout")
    public String toLogout(){return "login";}
    @RequestMapping("/usercenter")
    public String toLoginx(){return "usercenter";}
    @RequestMapping("/historyorder")
    public String tounicode(){return "historyorder";}
    @RequestMapping("/temLineChart")
    public String totemLineChart(){return "temLineChart";}
    @RequestMapping("/pathHistory")
    public String topathHistory(){return "pathHistory";}
}
