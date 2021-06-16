package com.ckj.controller;

import com.alibaba.fastjson.JSON;
import com.ckj.pojo.User;
import com.ckj.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public String json2(){
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

    @RequestMapping("/json4")
    public String json4() throws JsonProcessingException {
        User user1 = new User("ckj1", 3, "男");
        User user2 = new User("ckj2", 3, "男");

        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);

        return JSON.toJSONString(userList);
    }

}
