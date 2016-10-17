package com.weghst.konan.server.resource

import com.weghst.konan.Configs
import io.grpc.ManagedChannelBuilder
import io.grpc.internal.DnsNameResolverProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.client.RestTemplate

/**
  * @author Kevin Zou (yong.zou@pilibaba.com)
  */
@RequestMapping(Array("/tests"))
@RestController
class TestResource @Autowired()(configs: Configs, discoveryClient: DiscoveryClient, restTemplate: RestTemplate,
                                loadBalancerClient: LoadBalancerClient) {

  println("=========================================================================")
  println(discoveryClient)
  println(loadBalancerClient)

  @RequestMapping
  def index() {

    val builder = ManagedChannelBuilder.forTarget("dns://")
    builder.nameResolverFactory(new DnsNameResolverProvider)

    val managedChannel = builder.build()


    println(restTemplate.getForEntity("http://konan/configprops", classOf[String]))

    println(configs.name)
    configs.name
  }

}
