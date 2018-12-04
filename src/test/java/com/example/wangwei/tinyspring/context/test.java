package com.example.wangwei.tinyspring.context;

import java.util.Date;

public class test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Date date = new Date();
        Class aClass = date.getClass();
        System.out.println(aClass.toString());
        System.out.println("--------->"+aClass.newInstance().toString());

    }
}
