package com.coldchain.demo.util;

public class data {
    public static void main(String[] args) {
      String a = DateUtil.getNow();
        System.out.println(a);
        System.out.println(DateUtil.toTimestamp(a));

    }
}
