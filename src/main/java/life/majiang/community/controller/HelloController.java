package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController
{
    @GetMapping("/hello")
    //获取浏览器过来的请求名为user的参数值，存到变量name中
    public String hello(@RequestParam(name = "user", required=false, defaultValue="World") String name, Model model)
    {
        //将上面变量name中的值，作为model中名为"name"的key的值
        model.addAttribute("name",name);
        //调用templates中，名为hello的html文件，进行渲染
        return "hello";
    }
}
