package com.example.wangwei.tinyspring.beans.factory;

import com.example.wangwei.tinyspring.BeanReference;
import com.example.wangwei.tinyspring.beans.BeanDefinition;
import com.example.wangwei.tinyspring.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactoryAware;


import java.lang.reflect.Field;
import java.lang.reflect.Method;



/*
 * 可实现自动装配的BeanFactory
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{
	
	/*
	 * 通过反射自动装配bean的所有属性
	 */
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory((org.springframework.beans.factory.BeanFactory) this);
		}
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValuesList()) {
			Object value = propertyValue.getValue();
			// 如果属性是ref而不是value类型就先实例化那个ref的bean，然后装载到这个value里
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				// 先实例化ref的bean再装配进去
				value = getBean(beanReference.getName());
			}
			
			try{
				/*
				 * 例如：<bean id="dog" class="com.ysj.entity.Dog">
							<property name="Id" value="101"></property>
						</bean>
					先获取dog对象的setId方法，然后通过反射调用该方法将value设置进去
					getDeclaredMethod方法的第一个参数是方法名，第二个参数是该方法的参数列表
				 */
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						// 拼接方法名
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
							  + propertyValue.getName().substring(1), 
						value.getClass());

				System.out.println("这个是在AutowireCapableBeanFactory方法中拼接方法名----->"+"set" + propertyValue.getName().substring(0, 1).toUpperCase()
							  + propertyValue.getName().substring(1));
				
				declaredMethod.setAccessible(true);
				declaredMethod.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				// 如果该bean没有setXXX的类似方法，就直接将value设置到相应的属性域内
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
	}
	
}
