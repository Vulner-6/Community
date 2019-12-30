package life.majiang.community.controller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController
{
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private GithubProvider githubProvider; //这个注解就是@Component加载类到内存中，现在取出来使用
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response)  //这里的HttpServletRequest代表上下文的请求,同理response
    {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser =githubProvider.getUser(accessToken);
        //判断是否拿到user信息
        if(githubUser!=null)
        {
            //如果成功从github获取到用户信息，那么将数据插入到h2数据库中
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);
            //在cookie中多添加一个token字段，返回给前端
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }
        else
        {
            //登录失败，重新登录
            return "redirect:/";   //这样写就相当于重定向
        }

    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response)
    {
        //销毁会话
        request.getSession().removeAttribute("user");
        //覆盖cookie中的token凭证
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
