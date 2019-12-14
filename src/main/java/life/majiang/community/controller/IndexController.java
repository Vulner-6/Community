package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController
{
    @Autowired
    private UserMapper userMapper;
    //获取浏览器过来的请求名为user的参数值，存到变量name中
    @GetMapping("/")
    public String index(HttpServletRequest request)
    {
        //获取cookie中的token值
        Cookie[] cookies=request.getCookies();
        String token=null;
        User user;
        //判断浏览器中的cookie是否为Null
        if(cookies!=null&&cookies.length>0)
        {
            for(Cookie cookie : cookies)
            {
                if(cookie.getName().equals("token"))
                {
                    token=cookie.getValue();
                    //根据cookie的token值，查询user信息
                    user= userMapper.findByToken(token);
                    System.out.println(user.toString());
                    if(user != null)
                    {
                        //给前端模板设置user属性
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        else
        {
            //也可以什么也不做
            System.out.println(cookies+"deng yu null");
        }
        return "index";
    }
}
