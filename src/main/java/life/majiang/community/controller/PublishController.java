package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PublishController
{
    //处理get请求
    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }

    @Autowired
    private Question question;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private User user=null;
    @Autowired
    private QuestionService questionService;
    //处理post请求。我在前端写的是post提交表单
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name ="title" ) String title,
            @RequestParam(name="description") String description,
            @RequestParam(name = "tag") String tag,
            @RequestParam(name="id") Integer id,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
            )
    {
        User user=(User) request.getSession().getAttribute("user");
        if(user==null)
        {
            model.addAttribute("error","用户没有登录，点击这里进行登录！");
            return "publish";
        }
        else
        {
            //判断用户是否有权限修改当前问题
            if(id!=null)
            {
                Question tempQuestion=questionMapper.getById(id);
                if(user.getAccountId()!=tempQuestion.getCreator())
                {
                    return "redirect:/noEditAuthorityError";
                }
            }
            //判断用户提交的参数是否为空
            if(title==null||title==""||description==null||description==""||tag==null||tag=="")
            {
                model.addAttribute("nullError","参数不能为空！");
                return "/publish";
            }
            //如果用户登录了
            question.setId(id);
            question.setTitle(title);
            question.setDescription(description);
            question.setTag(tag);
            question.setCreator(user.getAccountId());
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCreatorId(user.getId());

            questionService.createOrUpdate(question);

            model.addAttribute("success","发布成功！点击这里返回首页！");
            return "publish";
        }

    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Integer id,Model model)
    {
        Question question=questionMapper.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        //再添加一个隐藏的问题id属性，方便编辑完发布的时候实现覆盖。
        model.addAttribute("id",question.getId());


        return "publish";
    }
}
