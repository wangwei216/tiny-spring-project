package com.example.wangwei.tinyspring.beans.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 * 实现 Resource 接口的资源类，通过 URL 获取资源。
 *
 */
public class UrlResource implements Resource{

	/*
	 * 通过这个 URL 获取资源
	 */
	private final URL url;
	
	public UrlResource(URL url) {
        this.url = url;
    }
	
	/*
	 * 通过URL获取资源
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
		System.out.println("这个是URL资源------->"+url.toString());
		System.out.println("这个是获取URL链接------->"+urlConnection.toString());
        return urlConnection.getInputStream();
	}

}
