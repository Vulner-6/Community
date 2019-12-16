package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController
{
    //处理get请求
    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }

    @Autowired Question question;
    @Autowired UserMapper userMapper;
    @Autowired QuestionMapper questionMapper;
    @Autowired User user=null;
    //处理post请求。我在前端写的是post提交表单
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name ="title" ) String title,
            @RequestParam(name="description") String description,
            @RequestParam(name = "tag") String tag,
            HttpServletRequest request,
            Model model
            )
    {
        //先判断用户是否登录
        Cookie[] cookies=request.getCookies();
        if(cookies!=null)
        {
            //遍历cookie中的字段
            for(Cookie cookie:cookies)
            {
                //如果cookies中当前字段为token字段，就去数据库中查询user，返回User对象
                if(cookie.getName().equals("token"))
                {
                    String token=cookie.getValue();
                    user=userMapper.findByToken(token);
                    //如果能获取到用户信息，也就是用户登录了
                    if(user!=null)
                    {
                        request.getSession().setAttribute("user",user);
                        //如果用户登录了
                        question.setTitle(title);
                        question.setDescription(description);
                        question.setTag(tag);
                        question.setCreator(user.getId());
                        question.setGmtCreate(System.currentTimeMillis());
                        question.setGmtModified(question.getGmtCreate());
                        questionMapper.create(question);

                        return "redirect:/";
                    }
                    //如果有token字段，但是值是错的，无法获取到用户信息
                    else {
                        model.addAttribute("error","用户没有登录");
                        return "publish";
                    }

                }
                //如果cookies中当前字段不是token字段，就什么都不做，下一轮循环
            }
            //遍历全部cookie，发现没有token字段
            model.addAttribute("error","用户没有登录！！！");
            return "publish";
        }
        //如果客户端的cookie值为null
        else
        {
            model.addAttribute("error","用户没有登录");
            return "publish";
        }

    }
}
