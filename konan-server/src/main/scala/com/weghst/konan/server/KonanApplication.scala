package com.weghst.konan.server

import com.weghst.konan.Configs
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.client.RestTemplate

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@Configuration
@ComponentScan(Array("com.weghst.konan"))
class KonanApplication {

  @Bean
  @RefreshScope
  def configs(): Configs = {
    new Configs()
  }

  @Bean
  @LoadBalanced
  def restTemplate(): RestTemplate = {
    new RestTemplate()
  }
}

object KonanApplication {
  def main(args: Array[String]) {

    SpringApplication.run(classOf[KonanApplication], args: _*)
  }
}
