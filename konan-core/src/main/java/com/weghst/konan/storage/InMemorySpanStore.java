package com.weghst.konan.storage;

import java.util.*;

import org.springframework.util.CollectionUtils;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.weghst.konan.internal.Pair;
import com.weghst.konan.span.Annotation;
import com.weghst.konan.span.BinaryAnnotation;
import com.weghst.konan.span.Span;
import com.weghst.konan.span.Spans;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
public class InMemorySpanStore implements SpanStore {

    private final Object LOCK = new Object();
    private final Multimap<Long, Span> traceIdToSpans = LinkedListMultimap.create();
    private final Multimap<String, String> serviceToSpanNames = LinkedListMultimap.create();
    private final Set<Pair<Long>> traceIdTimestamps = new TreeSet<>(VALUE_2_DESCENDING);
    private final Multimap<String, Pair<Long>> serviceToTraceIdTimestamps = LinkedListMultimap.create();

    static final Comparator<Pair<Long>> VALUE_2_DESCENDING = (left, right) -> {
        int result = right._2.compareTo(left._2);
        if (result != 0) return result;
        return right._1.compareTo(left._1);
    };

    @Override
    public void collect(Span span) {
        Pair<Long> traceIdTimestamp = Pair.create(span.getTraceId(),
                span.getTimestamp() == 0 ? Long.MAX_VALUE : span.getTimestamp());
        synchronized (LOCK) {
            traceIdTimestamps.add(traceIdTimestamp);
            traceIdToSpans.put(span.getTraceId(), span);

            for (String serviceName : getServiceNames(span)) {
                serviceToTraceIdTimestamps.put(serviceName, traceIdTimestamp);
                serviceToSpanNames.put(serviceName, span.getName());
            }
        }
    }

    @Override
    public void collect(Spans spans) {
        spans.getSpansList().forEach(this::collect);
    }

    @Override
    public List<Spans> queryTraces(QueryRequest request) {
        return null;
    }

    @Override
    public Collection<Span> getTrace(long traceId) {
        Collection<Span> spans = getRawTrace(traceId);
        // FIXME 这里需要修改
        return spans;
    }

    @Override
    public Collection<Span> getRawTrace(long traceId) {
        Collection<Span> spans = traceIdToSpans.get(traceId);
        if (CollectionUtils.isEmpty(spans)) {
            return null;
        }
        return spans;
    }

    @Override
    public List<String> getServiceNames() {
        return new ArrayList<>(serviceToTraceIdTimestamps.keySet());
    }

    private Set<String> getServiceNames(Span s) {
        Set<String> set = new HashSet<>();
        for (Annotation a : s.getAnnotationsList()) {
            if (a.getEndpoint() == null) {
                continue;
            }
            set.add(a.getEndpoint().getServiceName());
        }
        for (BinaryAnnotation ba : s.getBinaryAnnotationsList()) {
            if (ba.getEndpoint() == null) {
                continue;
            }
            set.add(ba.getEndpoint().getServiceName());
        }
        return set;
    }
}
