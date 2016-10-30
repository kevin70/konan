package com.weghst.konan.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import io.undertow.UndertowOptions;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.weghst.konan")
public class KonanServerApplication {

    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
        return factory;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(KonanServerApplication.class)
                .run(args);
    }
}
