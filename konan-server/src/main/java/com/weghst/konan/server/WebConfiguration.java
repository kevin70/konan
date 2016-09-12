package com.weghst.konan.server;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@Configuration
@RestController
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Value("classpath:/konan-ui/index.html")
    private Resource indexHtml;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/konan-ui/")
                .setCachePeriod((int) TimeUnit.DAYS.toSeconds(365));
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8", true);
        return filter;
    }

    @RequestMapping("/index.html")
    public Object index() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(indexHtml);
    }
}
