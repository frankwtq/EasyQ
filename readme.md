# Easyq
Easyq(easy call)是一款对apache httpclient进行封装的http请求接口，旨在更便捷的进行http请求操作，其中包括get post的参数设置，cookie的参数设置，文件的上传等。

## 一、最小范例
###1.最简单请求
下面的代码给出了对于一个[网站](http://www.swjtu.edu.cn) 的最简单的请求(无参数无cookie)，返回的响应是html数据。
```Java
public void testGet() throws Exception {
	HttpResponse response = new HttpPostCaller().send("http://www.swjtu.edu.cn/");	//获取对应网址的响应
	System.out.println(response.getString());	//将响应的内容转换为字符串并打印
}
```
###2.Post请求
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

###3.cookie请求


## 二、接口说明