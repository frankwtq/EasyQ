# Easyq
Easyq(easy call)��һ���apache httpclient���з�װ��http����ӿڣ�ּ�ڸ���ݵĽ���http������������а���get post�Ĳ������ã�cookie�Ĳ������ã��ļ����ϴ��ȡ�

## һ����С����
###1.�������
����Ĵ�������˶���һ��[��վ](http://www.swjtu.edu.cn) ����򵥵�����(�޲�����cookie)�����ص���Ӧ��html���ݡ�
```Java
public void testGet() throws Exception {
	HttpResponse response = new HttpPostCaller().send("http://www.swjtu.edu.cn/");	//��ȡ��Ӧ��ַ����Ӧ
	System.out.println(response.getString());	//����Ӧ������ת��Ϊ�ַ�������ӡ
}
```
###2.Post����
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

###3.cookie����


## �����ӿ�˵��