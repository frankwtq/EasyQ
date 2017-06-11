# *EasyQ*
*EasyQ* (Easy Call)是一款对apache httpclient进行封装的http请求接口，旨在更便捷的进行http请求操作，其中包括get post的参数设置，cookie的参数设置，文件的上传等。

## 一、*最小范例*
### 1.最简单请求
下面的代码给出了对于一个[网站](http://www.swjtu.edu.cn)的最简单的请求(无参数无cookie)，返回的响应是html数据。
```Java
public void testGet() throws Exception {
	HttpResponse response = new HttpPostCaller().send("http://www.swjtu.edu.cn/");	//获取对应网址的响应
	System.out.println(response.getString());	//将响应的内容转换为字符串并打印
}
```
### 2.Post请求
下面的代码给出了一个翻译post请求，返回的响应是json格式数据。
```Java
public void testPost() throws Exception {
	HttpResponse response = new HttpPostCaller()
			.put("from", "en")							//添加参数
			.put("to", "zh")
			.put("query", "hello world")
			.put("transtype", "translang")
			.put("simple_means_flag", "3")
			.send("http://fanyi.baidu.com/v2transapi");	//发送请求
	System.out.println(response.getString());			//获得响应内容字符串
}
```

### 3.cookie请求
下面的代码是设置cookie后进行请求
```Java
public void testPost() throws Exception {
	HttpResponse response = new HttpPostCaller()
			.cookie("JSESSIONID", "0000prZtRoPe2hQ9kfCJYn9YTEn")	//设置cookie
			.send("http://www.swjtu.edu.cn");	//发送请求
	System.out.println(response.getString());			//获得响应内容字符串
}
```

## 二、*类与接口说明*
*EasyQ*中有两个非常重要的类，现在先对最简单的类进行说明，再对另一个类进行阐述。
### 1.HttpResponse
这个类非常简单，就只是一个对Http响应的数据的保存类。
```Java
public class HttpResponse {
	public byte[] getBuffer();				//响应的类型由多种多样，有html/css/js/json/xml等以字符串内容，也可能为图像等二进制数据。该接口获得响应的十六进制数据。
	public String getString();				//将响应十六进制字节数据通过UTF-8进行解码得到String类型数据。(UTF8->Unicode)
	public Cookie getCookie(String key);				//获得响应里面对应的cookie。
	public Set<String> getCookieKeys();				//获得所有cookie的名字。
}

```
### 2.HttpCaller
这是一个接口，提供以下方法：
```Java
public interface HttpCaller {
	public HttpResponse send(String baseUrl) throws Exception;
	public HttpCaller put(String key, String value);
	public HttpCaller cookie(String key, String value);
	public HttpCaller cookie(String key, String value, String domain, String path);
}
```
该接口的实现都是线程非安全的，因此不能在线程之间共享该对象。每个线程都应该有自己的HttpCaller来负责自己线程的http请求。目前有以下几种实现:
* HttpGetCaller, 该实现类用于发送Get请求。
* HttpPostCaller，该实现类用于发送Post请求。
* HttpMimeCaller，该实现类用于发送Mime请求。
#### 1).send
该方法用于发送带cookie的Http请求到对应的url，并得到响应。具体是post请求，或是get请求，甚至是mime格式的post请求都由实现类决定。
需要注意的是，由于cookie的存在因此多次send之间并非完全独立的，本次请求所得到的cookie是可以直接用于下次请求的。例如下面的基于需要登录的请求代码:
```Java
HttpCaller caller = new HttpPostCaller()
				.put("userid", "2015200563")
				.put("userpwd", "xxxxxxxxxxx");
HttpResponse response = caller.send("http://gs.swjtu.edu.cn/pro/userscenter/login");		//登录，收到响应时cookie将会保存于caller中，后面的请求不需要额外再设置cookie。
response = caller.send("http://gs.swjtu.edu.cn/ucenter/student/home/index");		//直接访问，若没有登录所收到的cookie，将不会收到期望的响应。
```
#### 2).put
该方法用于添加请求参数。主要针对post、get和mime格式的请求参数。
#### 3).cookie
通常使用cookie(key, value)即可，这时候domain默认是send的url中的domain，而path默认是"/"。当然重载的cookie可以自行设置domain和path。