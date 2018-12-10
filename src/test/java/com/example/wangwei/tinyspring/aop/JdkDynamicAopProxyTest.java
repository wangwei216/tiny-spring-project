package com.example.wangwei.tinyspring.aop;

import com.example.wangwei.tinyspring.HelloWorldService;
import com.example.wangwei.tinyspring.HelloWorldServiceImpl;
import com.example.wangwei.tinyspring.context.ApplicationContext;
import com.example.wangwei.tinyspring.context.ClassPathXmlApplicationContext;
import org.junit.Test;


/**
 * @author wnagwei
 */
public class JdkDynamicAopProxyTest {

	@Test
	public void testInterceptor() throws Exception {
		/*
		* 第一大步：加载文件，获取类
		* 	1. 先先去XML文件中去获取所在路径的类文件信息，
		* 	2. 然后在AutowireCapableBeanFactory中自动装配到BeanFactory工厂中
		* 	3. 然后再从BeanFactory中通过getBean（）方法拿到bean的实例对象
		* */
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();


		/*
		* 第二大步：设置被代理对象设置被代理对象(Joinpoint)
		* 	1. 需要先拿到AdvisedSupport对象，它里面组合了TargetSource对象（TargetSource对象中包含了原对象信息、目标类信息，目标接口类信息）
		* 	2. 所以需要把被代理的对象通过TargetSource构造器给注入进来
		* 	3. 然后通过AdvisedSupport组合的TargetSource对象给set进来
		* */
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class,
				HelloWorldService.class);
		advisedSupport.setTargetSource(targetSource);

		// 第三大步：设置拦截器(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);

		// 补：由于用户未设置MethodMatcher，所以通过代理还是调用的原方法(JdkDynamicAopProxy中的invoke方法最后
		// 返回method.invoke(...)而不是methodInterceptor.invoke(...) )
		// 第四大步： 创建代理(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		System.out.println("jdkDynamicAopProxy的地址为——————>"+jdkDynamicAopProxy.getProxy().toString());
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();


		// 4. 基于AOP的调用
		helloWorldServiceProxy.helloWorld();

	}
}
