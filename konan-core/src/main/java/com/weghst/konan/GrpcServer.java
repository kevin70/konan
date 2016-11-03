package com.weghst.konan;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ecwid.consul.v1.ConsulClient;
import com.weghst.konan.internal.SpanCollectorImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.health.v1.HealthCheckResponse;
import io.grpc.services.HealthStatusManager;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@Component
public class GrpcServer {

    @Value("${konan.grpc.port:50051}")
    private int port;
    private Server server;

    @Autowired
    private SpanCollectorImpl spanCollector;
    @Autowired
    private ConsulClient consulClient;

    @PostConstruct
    public void start() throws IOException {
        HealthStatusManager hsm = new HealthStatusManager();
        hsm.setStatus("konan", HealthCheckResponse.ServingStatus.SERVING);

        server = ServerBuilder.forPort(port)
                .addService(hsm.getHealthService())
                .addService(spanCollector)
                .build();
        server.start();

        //
//        NewService newService = new NewService();
//        newService.setName("konan-grpc");
//        newService.setAddress("localhost");
//        newService.setPort(port);
//        newService.setId("konan-grpc");
//
//        NewService.Check check = new NewService.Check();
//        check.setTimeout("5s");
//        check.setInterval("10s");
//        check.setScript("consul-health-checks grpc --address 192.168.1.13:50051 --service konan");
//        newService.setCheck(check);
//
//        Response<Void> resp = consulClient.agentServiceRegister(newService);
//        System.out.println(resp);
//        consulClient.agentServiceDeregister("konan-grpc");
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdownNow();
        }
    }

}
