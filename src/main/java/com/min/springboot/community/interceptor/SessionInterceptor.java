package com.min.springboot.community.interceptor;

import com.min.springboot.community.model.User;
import com.min.springboot.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session中的用户
        User sessionUser =(User)request.getSession().getAttribute("user");

        //如果session中不存在用户,查找cookie中的token,然后根据token获取数据库中的用户,存入session
        if (sessionUser == null || sessionUser.getId().equals("")){
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        User user = userService.findByToken(token);
                        if (user != null) {
                            request.getSession().setAttribute("user", user);
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
