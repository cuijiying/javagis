package cjy.aigis.javagis.config;

import cjy.aigis.javagis.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cjy
 * @description 注册拦截器
 * @date 2023/08/03 16:01:01
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/logout")
                .excludePathPatterns("/tiles/*/*/*/*") //排除[/tiles]下的矢量切片路径
                ;
    }
}
