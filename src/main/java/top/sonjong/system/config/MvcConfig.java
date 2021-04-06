package top.sonjong.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import top.sonjong.system.interceptor.LoginHandlerInterceptor;
import top.sonjong.system.utils.ImgUploadUtil.ImgUpload;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/login.html").setViewName("login");
            }
        };
        return adapter;
    }
    //消息格式转换
    //1.跳转中文乱码1
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //其中image表示访问的前缀。"file:X:/xxx/"是文件真实的存储路径
        registry.addResourceHandler("/uploadImgs/**").addResourceLocations("file:"+ ImgUpload.IMG_SAVE_LOCATION);
    }
    //2.跳转中文乱码2
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
    }
    //登录拦截路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/industry","/index_home",
                "/diseases","/farmer","/ponds",
                "/products","/farmerManage","/pondsManage",
                "/productList","/industryList","/diseaseList")
                .excludePathPatterns("/login","/");
    }

}
