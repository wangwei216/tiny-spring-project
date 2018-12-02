package com.example.wangwei.tinyspring.beans.xml;

import java.util.Map;

import com.example.wangwei.tinyspring.beans.BeanDefinition;
import com.example.wangwei.tinyspring.beans.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;


public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		System.out.println(registry.toString());
		System.out.println(registry.get("outputService"));
		System.out.println(registry.get("helloWorldService"));
		Assert.assertTrue(registry.size() > 0);
	}
}
