package me.lsj.http;

public interface HttpCaller {
	public HttpResponse send(String baseUrl) throws Exception;
	
	public HttpCaller put(String key, String value);
	public HttpCaller cookie(String key, String value);
	public HttpCaller cookie(String key, String value, String domain, String path);
}
