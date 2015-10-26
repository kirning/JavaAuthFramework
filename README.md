## JavaAuthFramework

这是一个带有token验证能力的框架，使用`struts2` `spring` `springJDBC` 完成
大致的过程是 

**请求->拦截->Token认证->身份认证->处理请求->更新Token->返回数据**

每一次请求都会更新一次Token,所以每次请求都必须携带最新的token数据，所有数据通过json格式封装提交，`token`为token关键字 比如

```json
	{
		"token":"321549asdf15a6sdf8589asdf",
		"username":"tom",
		"password":"123"
	}
```

这样的请求为一个合法请求。token的解析和加密都在TokenService类里面。

返回的数据也是json格式，token也包含在里面，struts2的Action继承，为了返回父类的属性数据，使用了

```java
	@Result(type="json", params={"ignoreHierarchy", "false"}
```

但是返回的数据冗余，除了自定义的一些需要返回的数据外，还有很多struts2自带的一些，不知道怎么取消，如有大神知道希望可以请教一下。

这个框架还配置好了Spring的单元测试，但是在测试中使用的applicationContext.xml无法读取jdbc.properties文件，于是我就另外写了一个applicationContext2.xml，在里面写死了jdbc的配置参数。

框架刚起步，主要还是为了方便自己以后使用，有兴趣的一起来完善吧，

这个框架可以跨域访问，方便前端后台脱离
