package Test;

import org.junit.Test;

import me.lsj.http.HttpCaller;
import me.lsj.http.HttpPostCaller;
import me.lsj.http.HttpResponse;


public class EasyqTest {

	@Test
	public void test() throws Exception {
		HttpCaller caller = new HttpPostCaller()
				.put("userid", "2015200563")
				.put("userpwd", "xxxxxxxxxxx");
		
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

}