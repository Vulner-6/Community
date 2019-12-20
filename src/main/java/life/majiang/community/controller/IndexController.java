package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size)
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
                //判断是否有登录凭证token
                if(cookie.getName().equals("token"))
                {
                    token=cookie.getValue();
                    //根据cookie的token值，查询user信息
                    user= userMapper.findByToken(token);
                    if(user != null)
                    {
                        //给前端模板设置user属性
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        //从数据库中读取数据，显示列表
        PaginationDTO paginationDTO =questionService.list(page,size);
        model.addAttribute("questions",paginationDTO);
        return "index";
    }
}
