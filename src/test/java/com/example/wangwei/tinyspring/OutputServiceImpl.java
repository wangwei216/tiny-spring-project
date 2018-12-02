package com.example.wangwei.tinyspring;

/**
 * @author yihua.huang@dianping.com
 */
public class OutputServiceImpl implements OutputService {

	@Override
	public void output(String text) {
		System.out.println(text);
	}

}
