package com.weghst.konan.server.resource

import com.weghst.konan.Configs
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
@RequestMapping(Array("/tests"))
@RestController
class TestResource @Autowired()(configs: Configs) {

  @RequestMapping
  def index() {
    println(configs.name)
    configs.name
  }

}
