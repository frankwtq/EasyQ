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