package com.min.springboot.community.provider;

import com.alibaba.fastjson.JSON;
import com.min.springboot.community.dto.AccessTokenDTO;
import com.min.springboot.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.redirect_url}")
    private String redirectUrl;
    /**
     * 获取重定向github的字符串
     *https://github.com/login/oauth/authorize?client_id=06ee2de97bc15500c303&redirect_uri=http://localhost:8887/github/callback&scope=user&state=1
     */
    public String getRedirectUrl() {
        return "https://github.com/login/oauth/authorize?client_id=" + clientId
                        + "&redirect_uri=" + redirectUrl
                        + "&scope=user&state=1";
    }


    /**
     * 使用okhttp3来调用接口,还是用到了JSON,
     * 导入依赖.这里是使用的http3和fastjson.
     */

    /**
     * 根据code获取accesstoken
     * POST调用接口 https://github.com/login/oauth/access_token
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s=response.body().string();
            String token = s.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更加token获取用户信息
     * get方式调用接口 https://api.github.com/user?access_token=" + token
     * @param token
     * @return
     */
    public GithubUser getUser(String token) {
        GithubUser user = new GithubUser();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + token)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            user = JSON.parseObject(s, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}
