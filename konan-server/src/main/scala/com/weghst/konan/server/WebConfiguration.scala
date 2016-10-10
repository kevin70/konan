package com.weghst.konan.server

import java.util.concurrent.TimeUnit

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.core.annotation.Order
import org.springframework.core.io.Resource
import org.springframework.http.{CacheControl, ResponseEntity}
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.servlet.DispatcherServlet
import org.springframework.web.servlet.config.annotation.{ResourceHandlerRegistry, WebMvcConfigurerAdapter}

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
@Configuration
@RestController
class WebConfiguration extends WebMvcConfigurerAdapter {

  @Value("classpath:/konan-ui/index.html")
  var indexHtml: Resource = _

  override def addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry.addResourceHandler("/**")
      .addResourceLocations("classpath:/konan-ui/")
      .setCachePeriod(TimeUnit.DAYS.toSeconds(365).asInstanceOf[Int])
  }

  @Bean
  @Order(org.springframework.core.Ordered.HIGHEST_PRECEDENCE)
  def characterEncodingFilter() = {
    new CharacterEncodingFilter("UTF-8")
  }

  @Bean
  def apiV1ServletBean(wac: WebApplicationContext) = {
    val ds = new DispatcherServlet(wac)
    val bean = new ServletRegistrationBean(ds, "/api/v1/*")
    bean.setName("api-v1")
    bean
  }

  @RequestMapping(Array("/"))
  def index() = ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES)).body(indexHtml)

}
