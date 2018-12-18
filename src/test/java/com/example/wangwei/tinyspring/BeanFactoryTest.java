package com.example.wangwei.tinyspring;

import java.util.Map;

import com.example.wangwei.tinyspring.beans.BeanDefinition;
import com.example.wangwei.tinyspring.beans.factory.AbstractBeanFactory;
import com.example.wangwei.tinyspring.beans.factory.AutowireCapableBeanFactory;
import com.example.wangwei.tinyspring.beans.io.ResourceLoader;
import com.example.wangwei.tinyspring.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;


/*
 * @author wangwei
 */
public class BeanFactoryTest {

	private AbstractBeanFactory beanFactory;

	@Test
	public void testLazy() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

		// 2.初始化BeanFactory并注册bean
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		//因为容量比较大，所以这里使用到了一Map。Entry来进行对这个Map集合进行遍历
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
		// 3. 然后把拿到的所有的Map集合中的bean的Name和BeanDefinition的都注册到AbstractBeanFactory工厂中
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

		// 4.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}

	@Test
	public void testPreInstantiate() throws Exception {
		// 1.读取配置
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

		// 2.初始化BeanFactory并注册bean
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}

		// 3.初始化bean
		beanFactory.preInstantiateSingletons();

		// 4.获取bean
		HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}
}
