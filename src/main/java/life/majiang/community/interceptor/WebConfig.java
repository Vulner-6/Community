package life.majiang.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc   关闭这个注解，就不会拦截css样式了
public class WebConfig implements WebMvcConfigurer
{
    //需要在这里用Autowired调用SessionInterceptor，因为SessionInterceptor加了@Service注解，被spring接管了
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //哪些地址进行拦截，哪些地址略过
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/index");

    }
}