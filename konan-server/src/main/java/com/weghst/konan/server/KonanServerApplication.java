package com.weghst.konan.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.weghst.konan")
public class KonanServerApplication {

    public static void main(String[] args) {
        Map<String, Object> properties = new HashMap<>();
        // properties.put("spring.application.name", "konan");
        // properties.put("spring.cloud.config.uri", "file://${user.home}/config-repo");
        // properties.put("spring.cloud.config.discovery.enabled", true);

        new SpringApplicationBuilder(KonanServerApplication.class)
                .properties(properties)
                .run(args);
    }
}
