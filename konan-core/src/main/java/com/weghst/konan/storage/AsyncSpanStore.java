package com.weghst.konan.storage;

import java.util.List;

import com.google.common.util.concurrent.ListenableFuture;
import com.weghst.konan.span.Spans;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
public interface AsyncSpanStore {

    /**
     * @param span
     * @return
     */
    ListenableFuture<Void> collect(Spans span);

    /**
     * @param request
     * @return
     */
    ListenableFuture<List<Spans>> queryTraces(QueryRequest request);

    ListenableFuture<Spans> getTrace(long id);

    ListenableFuture<Spans> getRawTrace(long traceId);

    ListenableFuture<List<String>> getServiceNames();

}
