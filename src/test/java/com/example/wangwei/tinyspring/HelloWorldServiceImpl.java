package com.example.wangwei.tinyspring;

/*
 * @author wangwei
 */
public class HelloWorldServiceImpl implements HelloWorldService {

	private String text;

	private OutputService outputService;

	@Override
	public void helloWorld() {
		outputService.output("我是HelloWorldServiceImpl类中的helloWorld方法---->"+text);
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setOutputService(OutputService outputService) {
		this.outputService = outputService;
	}

}
