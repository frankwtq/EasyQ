package Test;

import org.junit.Test;

import me.lsj.http.HttpCaller;
import me.lsj.http.HttpGetCaller;
import me.lsj.http.HttpResponse;


public class EasyqTest {

	@Test
	public void test() throws Exception {
		HttpCaller caller = new HttpGetCaller().cookie("JSESSIONID", "0000-4UpEjccnc_Z2jmulclzyhL:18qpv6drt");
		HttpResponse response = caller.send("http://www.swjtu.edu.cn/");
		for(String keys : response.getCookieKeys()){
			System.out.println(keys+" : "+response.getCookie(keys));
		}
		
		caller = new HttpGetCaller().cookie("JSESSIONID", "0000-4UpEjccnc_Z2jmulclzyhL:18qpv6drt");
		response = caller.send("http://www.swjtu.edu.cn");
		for(String keys : response.getCookieKeys()){
			System.out.println(keys+" : "+response.getCookie(keys));
		}
		
		caller = new HttpGetCaller().cookie("JSESSIONID", "0000-4UpEjccnc_Z2jmulclzyhL:18qpv6drt");
		response = caller.send("http://www.swjtu.edu.cn");
		for(String keys : response.getCookieKeys()){
			System.out.println(keys+" : "+response.getCookie(keys));
		}
		System.out.println(response.getString());
	}

}