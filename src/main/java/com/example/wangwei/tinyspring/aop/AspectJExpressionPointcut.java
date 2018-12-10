package com.example.wangwei.tinyspring.aop;



import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;



/*
 * 通过AspectJ表达式识别切点
 *
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

	private PointcutParser pointcutParser;

	private String expression;

	private PointcutExpression pointcutExpression;

	private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

	static {
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
	}

	public AspectJExpressionPointcut() {
		this(DEFAULT_SUPPORTED_PRIMITIVES);
	}

	public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
		pointcutParser = PointcutParser
				.getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
	}
	//确保生成表达式解析的Context环境
	protected void checkReadyToMatch() {
		if (pointcutExpression == null) {
			pointcutExpression = buildPointcutExpression();
		}
	}

	private PointcutExpression buildPointcutExpression() {
		return pointcutParser.parsePointcutExpression(expression);
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public ClassFilter getClassFilter() {
		System.out.println("在AspectJExpressionPointcut类中调用了getClassFilter方法中----->"+this.toString());
		return this;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		System.out.println("在AspectJExpressionPointcut类中调用了getMethodMatcher方法中----->"+this.toString());
		return this;
	}

	@Override
	public boolean matches(Class targetClass) {
		checkReadyToMatch();	//确保生成表达式解析的Context环境
		return pointcutExpression.couldMatchJoinPointsInType(targetClass);
	}

	@Override
	public boolean matches(Method method, Class targetClass) {
		checkReadyToMatch();	//确保生成表达式解析的Context环境
		ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
		if (shadowMatch.alwaysMatches()) {
			return true;
		} else if (shadowMatch.neverMatches()) {
			return false;
		}
		// TODO:其他情况不判断了！见org.springframework.aop.aspectj.RuntimeTestWalker
		return false;
	}
	
}
