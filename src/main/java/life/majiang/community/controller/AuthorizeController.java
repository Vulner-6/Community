package life.majiang.community.controller;

import life.majiang.community.controller.dto.AccessTokenDTO;
import life.majiang.community.controller.dto.GithubUser;
import life.majiang.community.controller.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController
{
    @Autowired
    private GithubProvider githubProvider; //这个注解就是@Component加载类到内存中，现在取出来使用
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state)
    {
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id("90b80e0fb78768e4e286");
        accessTokenDTO.setClient_secret("474f34fd0c3ee0ee604f6a3fa376d0bef8f770be");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser =githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        return "index";
    }
}
