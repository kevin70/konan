package com.weghst.konan;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.weghst.konan.internal.SpanCollectorImpl;

import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;

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

    @PostConstruct
    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(spanCollector)
                .build();
        server.start();
    }

    @PreDestroy
    public void stop() {
        if (server != null) {
            server.shutdownNow();
        }
    }

}
