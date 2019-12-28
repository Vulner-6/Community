package life.majiang.community.interceptor;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Service
public class SessionInterceptor implements HandlerInterceptor
{
    //如果Autowired不工作，说明这个类不是spring接管的javabean，因此加了@Service注解，表示这是为业务提供服务的注解，@Comment也可以
    @Autowired
    private UserMapper userMapper;
    //重写三个方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        //程序处理之前，先判断一下用户是否登录
        //获取cookie中的token值
        Cookie[] cookies=request.getCookies();
        String token=null;
        User user=new User();
        //判断浏览器中的cookie是否为Null
        if(cookies!=null&&cookies.length>0)
        {
            for(Cookie cookie : cookies)
            {
                //判断是否有登录凭证token,如果有，则判断token是否正确
                if(cookie.getName().equals("token"))
                {
                    token=cookie.getValue();
                    //根据cookie的token值，查询user信息
                    if(token==null){
                        continue;
                    }
                    user= userMapper.findByToken(token);
                    //如果获取到user信息，就跳出循环
                    if(user!=null)
                    {
                        request.getSession().setAttribute("user",user);
                        break;
                    }
                }
            }
            //如果有user信息，就返回true，否则返回false
        }
        if(user==null)
        {
            System.out.println("user 为 空！ 请注意检查！");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
    {

    }
}
