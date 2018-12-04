package com.example.wangwei.tinyspring.beans.io;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author yihua.huang@dianping.com
 */
public class ResourceLoaderTest {

	@Test
	public void test() throws IOException, IllegalAccessException, InstantiationException {
		ResourceLoader resourceLoader = new ResourceLoader();
		Resource resource = resourceLoader.getResource("tinyioc.xml");
		InputStream inputStream = resource.getInputStream();
		System.out.println("输出inputStream流----->"+inputStream.getClass());
		Assert.assertNotNull(inputStream);
	}
}
