## 1-介绍

控制器`Controller`

- 控制器复杂提供访问应用程序的行为，通常通过接口定义或注解定义两种方式实现
- 控制器负责解析用户的请求并将其转换为一个模型
- 在`SpringMVC`中一个控制器类可以包含多个方法
- 在`SpringMVC`中，对于`Controller`的配置方式有很多种

## 2-实现Controller接口

`Controller`是一个接口，在`org.springframework.web.servlet.mvc`包下，接口中只有一个方法

```java
@FunctionalInterface
public interface Controller {
    @Nullable
    ModelAndView handleRequest(HttpServletRequest var1, HttpServletResponse var2) throws Exception;
}
```

实现步骤参考 [2-Hello.md](2-Hello.md) 3-实现顺序

## 3-使用注解@Controller

参考  [2-Hello.md](2-Hello.md) 4-注解配置
