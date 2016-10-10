package com.weghst.konan.server

import com.weghst.konan.Configs
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(Array("com.weghst.konan"))
class KonanApplication {

  @Bean
  @RefreshScope
  def configs(): Configs = {
    new Configs
  }
}

object KonanApplication {
  def main(args: Array[String]) {

    SpringApplication.run(classOf[KonanApplication], args: _*)
  }
}
