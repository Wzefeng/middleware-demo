package demo.spring.data.redis.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Json Utils dependency on ObjectMapper
 *
 * @author wangzefeng
 */
@Component
public class JsonUtil {

    private static ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

    public static String writeValueAsString(Object value) {
        return ThrowableSupplier.execute(() -> objectMapper.writeValueAsString(value));
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        return ThrowableSupplier.execute(() -> objectMapper.readValue(content, valueType));
    }

}
