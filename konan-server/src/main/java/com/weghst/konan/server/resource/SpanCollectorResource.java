package com.weghst.konan.server.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Kevin Zou (yong.zou@pilibaba.com)
 */
@Controller
@RequestMapping("/spans")
public class SpanCollectorResource {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeferredResult<ResponseEntity> uploadSpansJSON(@RequestHeader(value = "Content-Encoding", required = false) String encoding,
                                                          @RequestBody byte[] body) {
        DeferredResult result = new DeferredResult();
        if (Objects.equals("gzip", encoding)) {
            try {
                body = unzip(body);
            } catch (IOException e) {
                result.setResult(ResponseEntity.badRequest().body("Cannot gunzip spans: " + e.getMessage()));
                return result;
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(new String(body));

        result.setResult(ResponseEntity.accepted().build());
        return result;
    }


    private static final ThreadLocal<byte[]> GZIP_BUFFER = new ThreadLocal<byte[]>() {
        @Override
        protected byte[] initialValue() {
            return new byte[1024];
        }
    };

    byte[] unzip(byte[] input) throws IOException {
        GZIPInputStream in = new GZIPInputStream(new ByteArrayInputStream(input));
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(input.length)) {
            byte[] buf = GZIP_BUFFER.get();
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            return out.toByteArray();
        }
    }

}
