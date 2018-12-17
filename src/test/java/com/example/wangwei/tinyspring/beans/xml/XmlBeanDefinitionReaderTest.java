package com.example.wangwei.tinyspring.beans.xml;

import java.util.Map;

import com.example.wangwei.tinyspring.beans.BeanDefinition;

import com.example.wangwei.tinyspring.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;


public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception {
		//第一步首先去获取XmlBeanDefinitionReader类的实例
			//1. 然后去XmlBeanDefinitionReader构造器中去构造ResourceLoader对象
			//2. 再去调用XmlBeanDefinitionReader的父类AbstractBeanDefinitionReader类的构造器方法
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		//第二大步：调用XmlBeanDefinitionReader的方法去获取资源类的二进制输入流
		//第三大步：这里会再去调用doLoadBeanDefinitions（）方法
			//1. 然后会先去获取获取DocumentBuilderFactory工厂
			//2. 然后会获取生成器DocumentBuilder，然后解析这个inputStream的文件流
			//3. 然后注册到registerBeanDefinitions（)中，然后遍历获取根标签<beans>
			//4. 获取处理(解析)bean标签，拿到bean的id和class ，最后是通过获取所有property标签的值
			//5. 然后看是不是有bean中的ref依赖，拿到所有的class信息、beanName、PropertyValues键值对，bean的Object对象信息都注册到BeanDefinition中
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		//第四大步：把这个bean的名字当做key，把BeanDefinition对象作为value放到一个大Map集合中尽心保存。
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		//第五大步：直接看我们xml文件中注入的bean是不是都被保存到这个大的Map集合中去了
		System.out.println(registry.toString());
		System.out.println(registry.get("outputService"));
		System.out.println(registry.get("helloWorldService"));
		Assert.assertTrue(registry.size() > 0);
	}
}
