package com.example.wangwei.tinyspring.beans.factory;

public class test {

    public static void main(String[] args){

        AbstractBeanFactory abstractBeanFactory = new AbstractBeanFactory();
        System.out.println(abstractBeanFactory.getClass().getName());
        System.out.println(abstractBeanFactory.getClass());

    }
}
