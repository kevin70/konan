package com.weghst.konan;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by kevin on 16/10/15.
 */
@Data
public class Test {

    private int id;
    private String name;
    private String userAddress;
    private String userPhone;
    private List list;
    private Map map;

    public static void main(String[] args) throws IOException {
        // jackson 基础配置项
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        objectMapper.setDateFormat(new ISO8601DateFormat());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Test t = new Test();
        t.userAddress = "宜昌";
        t.userPhone = "139";
        t.list = Lists.newArrayList();
        t.map = Maps.newHashMap();
        System.out.println(objectMapper.writeValueAsString(t));

        String json = "{\"name\":null,\"user_address\":\"宜昌\",\"user_phone\":\"139\",\"a\":55}";
        System.out.println(objectMapper.readValue(json, Test.class));

        System.out.println(Math.pow(10, 8));
        System.out.println(Math.pow(2, 2));

    }
}
