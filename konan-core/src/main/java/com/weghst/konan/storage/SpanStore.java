package com.weghst.konan.storage;

import java.util.Collection;
import java.util.List;

import com.weghst.konan.span.Span;
import com.weghst.konan.span.Spans;

/**
 * @author Kevin Zou (kevinz@weghst.com)
 */
public interface SpanStore {

    /**
     * @param span
     */
    void collect(Span span);

    /**
     * @param spans
     * @return
     */
    void collect(Spans spans);

    /**
     * @param request
     * @return
     */
    List<Spans> queryTraces(QueryRequest request);

    Collection<Span> getTrace(long traceId);

    Collection<Span> getRawTrace(long traceId);

    List<String> getServiceNames();
}
