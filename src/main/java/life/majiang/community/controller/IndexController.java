package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController
{
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
        //利用拦截器进行访问权限控制
        User user=(User) request.getSession().getAttribute("user");

        //从数据库中读取数据，显示列表
        PaginationDTO paginationDTO =questionService.list(page,size);
        model.addAttribute("questions",paginationDTO);
        return "index";
    }
}
