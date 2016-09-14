package com.weghst.konan.storage;

import java.util.List;

import com.weghst.konan.span.Spans;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
public interface SpanStore {

    /**
     * @param spans
     * @return
     */
    void submit(Spans spans);

    /**
     * @param request
     * @return
     */
    List<Spans> queryTraces(QueryRequest request);

    Spans getTrace(long id);

    Spans getRawTrace(long traceId);

    List<String> getServiceNames();
}
