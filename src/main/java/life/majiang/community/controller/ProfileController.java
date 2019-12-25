package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController
{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private PaginationDTO paginationDTO;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name="page",defaultValue = "1") Integer page,
            @RequestParam(name="size",defaultValue = "5") Integer size)
    {
        //判断用户是否登录，有没有访问权限
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
                    user= userMapper.findByToken(token);
                    //如果能根据登录凭证token获取user信息，就在会话中设置user属性
                    if(user != null)
                    {
                        //给会话设置user属性
                        request.getSession().setAttribute("user",user);

                    }
                    break;
                }
            }
            //如果cookie中多次遍历都没有token字段,session中也就没有user属性
            if(request.getSession().getAttribute("user")==null)
            {
                return "redirect:/";
            }
        }
        //如果cookie为null
        else{
            return "redirect:/";
        }

        //判断用户访问的是“我的问题” 还是 “最新回复”
        if("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            //查找我的问题相关数据，并返回前端，同时需要兼顾分页
            Integer myQuestionsCount=questionMapper.myQuestionsCount(user.getAccountId());
            paginationDTO.setPagination(myQuestionsCount,page,size);
        }
        else if("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            //查找最新回复相关数据，并返回前端，同时需要兼顾分页
        }

        return "profile";
    }
}
