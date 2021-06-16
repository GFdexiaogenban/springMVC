package com.ckj.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //放行：判断什么情况下是登录了的
        //1.session有内容
        //2.登录页也放行
        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }
        if (request.getRequestURI().contains("goLogin")) {
            return true;
        }
        if (request.getRequestURI().contains("login")) {
            return true;
        }
        //第一次登录也没有session
        //判断什么情况下没有登录
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        return false;
    }
}
