package com.example.wangwei.tinyspring.aop;

import com.example.wangwei.tinyspring.HelloWorldService;
import com.example.wangwei.tinyspring.HelloWorldServiceImpl;
import org.junit.Assert;
import org.junit.Test;


/*
 * 这个是测试通过自己实现的AspectJ的面向切面编程实现AOP
 * @author wangwei
 */
public class AspectJExpressionPointcutTest {

	@Test
	public void testClassFilter() throws Exception {
		String expression = "execution(* com.example.wangwei.tinyspring.*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
		Assert.assertTrue(matches);
	}

	@Test
	public void testMethodInterceptor() throws Exception {
		String expression = "execution(* com.example.wangwei.tinyspring.*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getMethodMatcher()
				.matches(HelloWorldServiceImpl.class.getDeclaredMethod("helloWorld"), HelloWorldServiceImpl.class);
		Assert.assertTrue(matches);
	}
}
