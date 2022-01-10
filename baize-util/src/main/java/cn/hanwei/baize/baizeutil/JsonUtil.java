package cn.hanwei.baize.baizeutil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * json 工具类
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJson(Object o) {
        try {
            return    objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int index = -1;
        while((index = is.read(buffer, 0, buffer.length)) != -1) {
            os.write(buffer,0, index);
        }
        byte[] rs = os.toByteArray();
        String json = new String(rs, "utf-8");
        return  json;
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fromJson(Object json, TypeReference<T> typeReference) {
        return objectMapper.convertValue(json, typeReference);
    }


    public static Map<String,  Object> json2map(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> maps = null;
        try {
            maps = objectMapper.readValue(
                    json, LinkedHashMap.class);
            /*Set<String> key = maps.keySet();
            Iterator<String> iter = key.iterator();
            while (iter.hasNext()) {

            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return maps;
    }
    static {
        objectMapper = new ObjectMapper();

        // 设置FAIL_ON_EMPTY_BEANS属性，当序列化空对象不要抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 设置FAIL_ON_UNKNOWN_PROPERTIES属性，当JSON字符串中存在Java对象没有的属性，忽略
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Convert Object to JsonString
     *
     * @param jsonObj
     * @return
     */
    public static String jsonObj2Sting(Object jsonObj) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(jsonObj);
        } catch (IOException e) {
            log.error("pasre json Object[{}] to string failed.", jsonObj);
        }

        return jsonString;
    }

    /**
     * Convert JsonString to Simple Object
     *
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> T jsonString2SimpleObj(String jsonString, Class<T> cls) {
        T jsonObj = null;

        try {
            jsonObj = objectMapper.readValue(jsonString, cls);
        } catch (IOException e) {
            log.error("pasre json string[{}] to Object failed.", jsonString);
        }

        return jsonObj;
    }
}
