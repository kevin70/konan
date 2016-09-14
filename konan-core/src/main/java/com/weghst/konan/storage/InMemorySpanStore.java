package com.weghst.konan.storage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.weghst.konan.span.Annotation;
import com.weghst.konan.span.Remark;
import com.weghst.konan.span.Span;
import com.weghst.konan.span.Spans;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
public class InMemorySpanStore implements SpanStore {

    private final Multimap<Long, Span> traceIdToSpans = ArrayListMultimap.create();

    @Override
    public void submit(Spans spans) {
        for (Span span : spans.getSpansList()) {

        }
    }

    @Override
    public List<Spans> queryTraces(QueryRequest request) {
        return null;
    }

    @Override
    public Spans getTrace(long id) {
        return null;
    }

    @Override
    public Spans getRawTrace(long traceId) {
        return null;
    }

    @Override
    public List<String> getServiceNames() {
        return null;
    }

    private void submit0(Span s) {
        synchronized (this) {
            traceIdToSpans.put(s.getTraceId(), s);

            for (String sn : getServiceNames(s)) {

            }
        }
    }

    private Set<String> getServiceNames(Span s) {
        Set<String> set = new HashSet<>();
        for (Annotation a : s.getAnnotationsList()) {
            if (a.getEndpoint() == null) {
                continue;
            }
            set.add(a.getEndpoint().getServiceName());
        }
        for (Remark r : s.getRemarksList()) {
            if (r.getEndpoint() == null) {
                continue;
            }
            set.add(r.getEndpoint().getServiceName());
        }
        return set;
    }
}
