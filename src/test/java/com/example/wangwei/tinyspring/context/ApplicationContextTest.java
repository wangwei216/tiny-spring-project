package com.example.wangwei.tinyspring.context;

import com.example.wangwei.tinyspring.HelloWorldService;
import org.junit.Test;


/*
 * @author wangwei
 */
public class ApplicationContextTest {
	
	@Test
	public void test() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		System.out.println("----------------->"+applicationContext.getClass().toString());
//		 OutputService outputService = (OutputService) applicationContext.getBean("outputService");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		
		// Assert.assertNotNull(helloWorldService);
		helloWorldService.helloWorld();
	}

	@Test
	public void testPostBeanProcessor() throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc-postbeanprocessor.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}
}
