package com.coldchain.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//温度实时监测
@Component
public class temperatureCheck {
    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
    public void execute() {
        System.out.println("定时器");
    }
}
