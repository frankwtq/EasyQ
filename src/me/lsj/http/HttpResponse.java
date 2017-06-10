package me.lsj.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class HttpResponse {
	byte[] buffer = null;
	Map<String, Cookie> cookieMap = new HashMap<>();
	
	public HttpResponse(byte[] buffer, CookieStore cookieStore){
		this.buffer = buffer;
		List<Cookie> listCookie = cookieStore.getCookies();
		for(Cookie cookie : listCookie){
			cookieMap.put(cookie.getName(), cookie);
		}
	}
	
	public byte[] getBuffer(){
		return buffer;
	}
	
	public String getString() throws IOException{
		return IOUtils.toString(buffer, "UTF-8");
	}
	
	public Cookie getCookie(String key){
		return cookieMap.get(key);
	}
	
	public Set<String> getCookieKeys(){
		return cookieMap.keySet();
	}
}
