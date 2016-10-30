package com.weghst.konan.internal;

import org.springframework.stereotype.Component;

import com.google.protobuf.Empty;
import com.weghst.konan.span.Span;
import com.weghst.konan.span.SpanCollectorGrpc;
import com.weghst.konan.span.Spans;

import io.grpc.stub.StreamObserver;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
@Component
public class SpanCollectorImpl extends SpanCollectorGrpc.SpanCollectorImplBase {

    @Override
    public void collect(Span request, StreamObserver<Empty> responseObserver) {
        // FIXME
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }

    @Override
    public void collectBatch(Spans request, StreamObserver<Empty> responseObserver) {
        // FIXME
        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
