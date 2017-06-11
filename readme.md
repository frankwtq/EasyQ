# *EasyQ*
*EasyQ* (Easy Call)��һ���apache httpclient���з�װ��http����ӿڣ�ּ�ڸ���ݵĽ���http������������а���get post�Ĳ������ã�cookie�Ĳ������ã��ļ����ϴ��ȡ�

## һ��*��С����*
### 1.�������
����Ĵ�������˶���һ��[��վ](http://www.swjtu.edu.cn)����򵥵�����(�޲�����cookie)�����ص���Ӧ��html���ݡ�
```Java
public void testGet() throws Exception {
	HttpResponse response = new HttpPostCaller().send("http://www.swjtu.edu.cn/");	//��ȡ��Ӧ��ַ����Ӧ
	System.out.println(response.getString());	//����Ӧ������ת��Ϊ�ַ�������ӡ
}
```
### 2.Post����
����Ĵ��������һ������post���󣬷��ص���Ӧ��json��ʽ���ݡ�
```Java
public void testPost() throws Exception {
	HttpResponse response = new HttpPostCaller()
			.put("from", "en")							//��Ӳ���
			.put("to", "zh")
			.put("query", "hello world")
			.put("transtype", "translang")
			.put("simple_means_flag", "3")
			.send("http://fanyi.baidu.com/v2transapi");	//��������
	System.out.println(response.getString());			//�����Ӧ�����ַ���
}
```

### 3.cookie����
����Ĵ���������cookie���������
```Java
public void testPost() throws Exception {
	HttpResponse response = new HttpPostCaller()
			.cookie("JSESSIONID", "0000prZtRoPe2hQ9kfCJYn9YTEn")	//����cookie
			.send("http://www.swjtu.edu.cn");	//��������
	System.out.println(response.getString());			//�����Ӧ�����ַ���
}
```

## ����*����ӿ�˵��*
*EasyQ*���������ǳ���Ҫ���࣬�����ȶ���򵥵������˵�����ٶ���һ������в�����
### 1.HttpResponse
�����ǳ��򵥣���ֻ��һ����Http��Ӧ�����ݵı����ࡣ
```Java
public class HttpResponse {
	public byte[] getBuffer();				//��Ӧ�������ɶ��ֶ�������html/css/js/json/xml�����ַ������ݣ�Ҳ����Ϊͼ��ȶ��������ݡ��ýӿڻ����Ӧ��ʮ���������ݡ�
	public String getString();				//����Ӧʮ�������ֽ�����ͨ��UTF-8���н���õ�String�������ݡ�(UTF8->Unicode)
	public Cookie getCookie(String key);				//�����Ӧ�����Ӧ��cookie��
	public Set<String> getCookieKeys();				//�������cookie�����֡�
}

```
### 2.HttpCaller
����һ���ӿڣ��ṩ���·�����
```Java
public interface HttpCaller {
	public HttpResponse send(String baseUrl) throws Exception;
	public HttpCaller put(String key, String value);
	public HttpCaller cookie(String key, String value);
	public HttpCaller cookie(String key, String value, String domain, String path);
}
```
�ýӿڵ�ʵ�ֶ����̷߳ǰ�ȫ�ģ���˲������߳�֮�乲��ö���ÿ���̶߳�Ӧ�����Լ���HttpCaller�������Լ��̵߳�http����Ŀǰ�����¼���ʵ��:
* HttpGetCaller, ��ʵ�������ڷ���Get����
* HttpPostCaller����ʵ�������ڷ���Post����
* HttpMimeCaller����ʵ�������ڷ���Mime����
#### 1).send
�÷������ڷ��ʹ�cookie��Http���󵽶�Ӧ��url�����õ���Ӧ��������post���󣬻���get����������mime��ʽ��post������ʵ���������
��Ҫע����ǣ�����cookie�Ĵ�����˶��send֮�䲢����ȫ�����ģ������������õ���cookie�ǿ���ֱ�������´�����ġ���������Ļ�����Ҫ��¼���������:
```Java
HttpCaller caller = new HttpPostCaller()
				.put("userid", "2015200563")
				.put("userpwd", "xxxxxxxxxxx");
HttpResponse response = caller.send("http://gs.swjtu.edu.cn/pro/userscenter/login");		//��¼���յ���Ӧʱcookie���ᱣ����caller�У������������Ҫ����������cookie��
response = caller.send("http://gs.swjtu.edu.cn/ucenter/student/home/index");		//ֱ�ӷ��ʣ���û�е�¼���յ���cookie���������յ���������Ӧ��
```
#### 2).put
�÷���������������������Ҫ���post��get��mime��ʽ�����������
#### 3).cookie
ͨ��ʹ��cookie(key, value)���ɣ���ʱ��domainĬ����send��url�е�domain����pathĬ����"/"����Ȼ���ص�cookie������������domain��path��