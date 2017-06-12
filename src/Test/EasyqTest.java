package Test;

import org.junit.Test;
import me.lsj.http.HttpCaller;
import me.lsj.http.HttpPostCaller;
import me.lsj.http.HttpResponse;


public class EasyqTest {

	@Test
	public void testLogin() throws Exception {
		HttpCaller caller = new HttpPostCaller()
				.put("userid", "2015200563")
				.put("userpwd", "XXXXXXXXX");
		
		HttpResponse response = caller.send("http://gs.swjtu.edu.cn/pro/userscenter/login");
		System.out.println(response.getString());
		for(String keys : response.getCookieKeys()){
			System.out.println(keys+" : "+response.getCookie(keys));
		}
		System.out.println("----------------------------------------------------------------");
		
		response = caller.send("http://gs.swjtu.edu.cn/ucenter/student/home/index");
		System.out.println(response.getString());
		for(String keys : response.getCookieKeys()){
			System.out.println(keys+" : "+response.getCookie(keys));
		}
	}
	
	//@Test
	public void testGet() throws Exception {
		HttpResponse response = new HttpPostCaller().send("http://www.swjtu.edu.cn/");
		System.out.println(response.getString());
	}
	
	//@Test
	public void testPost() throws Exception {
		HttpResponse response = new HttpPostCaller()
				.put("from", "en")
				.put("to", "zh")
				.put("query", "hello world")
				.put("transtype", "translang")
				.put("simple_means_flag", "3").send("http://fanyi.baidu.com/v2transapi");
		System.out.println(response.getString());
	}

}