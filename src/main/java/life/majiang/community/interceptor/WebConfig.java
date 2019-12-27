package life.majiang.community.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer
{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //哪些地址进行拦截，哪些地址略过
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**").excludePathPatterns("/admin/**");

    }
}