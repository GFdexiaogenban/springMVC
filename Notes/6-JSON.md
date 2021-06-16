## 1-JSON

### 1.1-基础知识

- JSON(JavaScript Object Notation, JS 对象标记) 是一种轻量级的数据交换格式，目前使用特别广泛。
- 采用完全独立于编程语言的文本格式来存储和表示数据。
- 简洁和清晰的层次结构使得 JSON 成为理想的数据交换语言。
- 易于人阅读和编写，同时也易于机器解析和生成，并有效地提升网络传输效率。

在 JavaScript 语言中，一切都是对象。因此，任何JavaScript 支持的类型都可以通过 JSON 来表示，例如字符串、数字、对象、数组等。他的要求和语法格式：

- 对象表示为键值对，数据由逗号分隔
- 花括号保存对象
- 方括号保存数组

```json
{"name": "QinJiang"}
{"age": "3"}
{"sex": "男"}
```

### 1.2-JSON和JS对象互转

JSON --> JS

```json
var obj = JSON.parse('{"a": "Hello", "b": "World"}');
```

JS --> JSON

```js
var json = JSON.stringify({a: 'Hello', b: 'World'});
```

## 2-Jackson

### 2.1-Maven依赖

```xml
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.12.3</version>
</dependency>
```

### 2.2-controller相关注解

1. `@ResponseBody`

   不会走视图解析器，会直接返回一个字符串

2. `@RestController`

   是`Controller`的变形

   该注解下的所有方法，均只返回字符串

### 2.3-实现方法

```java
ObjectMapper().writeValueAsString(userList);
```

### 2.4-测试代码

```java
@RestController
// @Controller
public class UserController {
    // @ResponseBody //不会走视图解析器，会直接返回一个字符串
    @RequestMapping("/json1")
    public String json1() throws JsonProcessingException {
        //jackson, ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user = new User("ckj", 3, "男");

        String str = mapper.writeValueAsString(user);
        return str;
    }

    @RequestMapping("/json2")
    public String json2() throws JsonProcessingException {
        //创建一个对象
        User user1 = new User("ckj1", 3, "男");
        User user2 = new User("ckj2", 3, "男");
        User user3 = new User("ckj3", 3, "男");
        User user4 = new User("ckj4", 3, "男");
        User user5 = new User("ckj5", 3, "男");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        return new ObjectMapper().writeValueAsString(userList);
    }

    @RequestMapping("/json3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();

        //自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return mapper.writeValueAsString(sdf.format(date));

        // mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // return mapper.writeValueAsString(date);
    }
}
```

### 2.5-改进

编写`Util`类`JsonUtil`

```java
package com.ckj.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JsonUtil {
    public static String getJson(Object object){
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object object,String dateFormat){
        ObjectMapper mapper = new ObjectMapper();
        //自定义日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        mapper.setDateFormat(sdf);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

此时，测试代码就可修改为

```java
@RestController
// @Controller
public class UserController {
    // @ResponseBody //不会走视图解析器，会直接返回一个字符串
    @RequestMapping("/json1")
    public String json1() throws JsonProcessingException {
        //创建一个对象
        User user = new User("ckj", 3, "男");

        return JsonUtil.getJson(user);
    }

    @RequestMapping("/json2")
    public String json2() {
        //创建一个对象
        User user1 = new User("ckj1", 3, "男");
        User user2 = new User("ckj2", 3, "男");
        User user3 = new User("ckj3", 3, "男");
        User user4 = new User("ckj4", 3, "男");
        User user5 = new User("ckj5", 3, "男");
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);

        return JsonUtil.getJson(userList);
    }

    @RequestMapping("/json3")
    public String json3() {

        Date date = new Date();
        return JsonUtil.getJson(date);
    }
}
```

这其中体现了一个复用的思想

## 3-fastjson

### 3.1-Maven依赖

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/fastjson -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
</dependency>
```

### 3.2-实现方法

```java
JSON.toJSONString()
```

## 4-spring中配置

```xml
<mvc:annotation-driven>
    <mvc:message-converters register-defaults="true">
        <bean class="org.springframework.http.converter.StringHttpMessageConverter">
            <constructor-arg value="UTF-8" />
        </bean>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper">
                <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                    <property name="failOnEmptyBeans" value="false" />
                </bean>
            </property>
        </bean>
    </mvc:message-converters>
</mvc:annotation-driven>
```
