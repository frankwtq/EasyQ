package me.lsj.http;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

public abstract class AbstractHttpCaller implements HttpCaller{
	protected final Map<String, String> params = new HashMap<>();
	protected final Map<String, Cookie> cookies = new HashMap<>();
	CookieStore cookieStore =  new BasicCookieStore();

	@Override
	public HttpCaller put(String key, String value){
		params.put(key, value);
		return this;
	}
	
	@Override
	public HttpCaller cookie(String key, String value) {
		return cookie(key, value, "", "/");
	}
	
	@Override
	public HttpCaller cookie(String key, String value, String domain, String path) {
		BasicClientCookie  cookie = new BasicClientCookie(key, value);
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookieStore.addCookie(cookie);
		return this;
	}
	
	@Override
	public HttpResponse send(String baseUrl) throws Exception {
		cookieLoad(baseUrl);
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		try{
			CloseableHttpResponse response = send(httpClient, baseUrl);
			return new HttpResponse(IOUtils.toByteArray(response.getEntity().getContent()), cookieStore);
		}finally{
			params.clear();
			httpClient.close();
		}
	}
	
	private void cookieLoad(String url){
		List<Cookie> listCookie = cookieStore.getCookies();
		String domain = parseDomain(url);
		for(Cookie cookie : listCookie){
			if(cookie.getDomain() == null || cookie.getDomain().equals("")){
				((BasicClientCookie) cookie).setDomain(domain);	
			}
		}
	}
	
	private String parseDomain(String url){
		URL u;
		try {
			u = new URL(url);
			return u.getHost();
		} catch (MalformedURLException e) {
			return url;
		}
	}
	
	abstract protected CloseableHttpResponse send(CloseableHttpClient httpClient, String baseUrl) throws Exception ;
}
