package com.zisu.springmvc.interceptor;

import com.zisu.springmvc.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url=request.getRequestURI();
        if(url.indexOf("userlogin")>0){
            return true;
        }
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null){
            return true;
        }
        else{
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
            return false;
        }
    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("———postHandle———");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("———postHandle———");
    }
}
