package com.example.wangwei.tinyspring.beans.factory;

/*
 * 首先这里使用到了是抽象工厂的模式，对外提供了一个或者bean实例的接口
 * 
 * 以 BeanFactory 接口为核心发散出的几个类，都是用于解决 IoC 容器在 已经获取 Bean 的 定义 的情况下，
 * 如何装配、获取 Bean 实例 的问题。
 *
 */
public interface BeanFactory {
	
	/*
	 * 通过 getBean(String) 方法来 获取bean实例
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Object getBean(String name) throws Exception;
	
}
