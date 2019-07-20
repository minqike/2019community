# springboot2 实现一个简单的论坛项目

## 资料


## github oauth 登录
### 流程
https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/
    
1. GET 定向链接到  https://github.com/login/oauth/authorize
   github返回: code    
2. 获取code后 POST https://github.com/login/oauth/access_token
   github返回: access_token
3. 根据access_token GET方式 https://api.github.com/user 获取用户消息 
    github返回: 用户信息