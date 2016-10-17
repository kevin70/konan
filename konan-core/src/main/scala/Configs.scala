package com.weghst.konan

import org.springframework.beans.factory.annotation.Value

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
class Configs {

  @Value("${config.name}")
  var name = ""
}
