package org.wemeet.portal.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil {

    private JsonUtil() {}

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    }

    public static String convertToString(Object object) {
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String result = null;

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("failed to convert object to string", e);
        }

        return result;
    }

    public static <T> T convertToObject(String json, Class<T> type) {
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error("failed to convert string to object", e);
        }

        return result;
    }

    public static <T> T convertToObject(String json, TypeReference<T> type) {
        T result = null;
        try {
            result = objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Error during deserialization Response: " + e.getMessage(), e);
        }

        return result;
    }
}
