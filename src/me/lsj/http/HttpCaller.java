package me.lsj.http;

import java.io.InputStream;

public interface HttpCaller {
	public String send2String(String baseUrl) throws Exception;
	public InputStream send2InputStream(String baseUrl) throws Exception;
	
	public HttpCaller put(String key, String value);
	//public HttpCaller cookie(String key, String value);
}
