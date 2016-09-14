package com.weghst.konan.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.weghst.konan")
public class KonanServerApplication {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(KonanServerApplication.class)
                .run(args);

        KonanServerApplication application = context.getBean(KonanServerApplication.class);
        System.out.println("------------------------------------------------------------");
        // org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(ServletContext);
        System.out.println(application.applicationContext);
    }
}
