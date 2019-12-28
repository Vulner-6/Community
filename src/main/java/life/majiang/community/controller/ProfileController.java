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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action") String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name="page",defaultValue = "1") Integer page,
            @RequestParam(name="size",defaultValue = "5") Integer size)
    {
        //判断用户是否登录，有没有访问权限
        User user=(User)request.getSession().getAttribute("user");
        //判断浏览器中的cookie是否为Null
        if(user==null)
        {
            return "redirect:/";
        }

        //判断用户访问的是“我的问题” 还是 “最新回复”
        if("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            //查找我的问题相关数据，并返回前端，同时需要兼顾分页
            paginationDTO=questionService.myQuestionsList(user,page,size);
            model.addAttribute("paginationDTO",paginationDTO);


        }
        else if("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
            //查找最新回复相关数据，并返回前端，同时需要兼顾分页
            //这里回复还没开发对应的功能。不这样写，前端each渲染会报错，因为没有paginationDTO的值
            paginationDTO=questionService.myQuestionsList(user,page,size);
            model.addAttribute("paginationDTO",paginationDTO);
        }

        return "profile";
    }
}
