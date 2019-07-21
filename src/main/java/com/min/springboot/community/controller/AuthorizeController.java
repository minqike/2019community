package com.min.springboot.community.controller;

import com.min.springboot.community.dto.AccessTokenDTO;
import com.min.springboot.community.dto.GithubUser;
import com.min.springboot.community.mapper.UserMapper;
import com.min.springboot.community.model.User;
import com.min.springboot.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect_url}")
    private String redirectUrl;

    /**
     * 第一步,获取重定向github的地址,直接重定向,
     * 实现Get调用https://github.com/login/oauth/authorize,获取code,并直接回调callback接口
     */
    @GetMapping("/github/login")
    public String  githubLogin(){
        return "redirect:"+ githubProvider.getRedirectUrl();
    }

    /**
     * github第一步,点击网页的登录按钮后跳转到github授权页面https://github.com/login/oauth/authorize
     * 传递的参数为https://github.com/login/oauth/authorize?client_id=06ee2de97bc15500c303&redirect_uri=http://localhost:8887/callback&scope=user&state=1
     * github会回调接口,这个接口就是github中定义的回调接口.
     * 这个回调接口中会做接下来的事情:
     * 第二步.根据code获取token
     * 第三步.根据token获取用户信息
     */
    @GetMapping("/github/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                            HttpServletResponse response) {
        //进入到这里时候表示,第一步已经走完,github已经回传code到这里了

        //第二步.根据code获取token
        //2.1构建post的参数
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        //2.2调用接口获取token
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
//        System.out.println(accessToken);

        //第三步 根据token获取用户
        //3.1 调用接口获取参数
        GithubUser githubUser = githubProvider.getUser(accessToken);
        //3.2 如果获取到用户
        System.out.println(githubUser);
        if (githubUser != null && githubUser.getId() != null) {
            // 登录成功

            //3.2.2 把数据存储到数据库
            User user = new User();
            String token=UUID.randomUUID().toString();
            user.setAccountId(githubUser.getId().toString());
            user.setName(githubUser.getName());
            user.setToken(token);
            user.setBio(githubUser.getBio());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            // 3.2.3 追加cookie
            Cookie cookie = new Cookie("token", token);
            System.out.println("token="+token);
            cookie.setMaxAge(-1);
            cookie.setPath("/"); //不加这个cookie无法保存,不知道为什么? 看视频教程中是不需要的
            response.addCookie(cookie);
            return "redirect:/";
        }else{
            // 登录失败
            return "redirect:/";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
