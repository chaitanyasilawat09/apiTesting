import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class GetAllKeysFromJsonObject {

    @Test
    public void getAllKeysFromNestedJsonObjectUsingMap() throws JsonMappingException, JsonProcessingException
    {

        String jsonObject = "{\r\n" +
                "  \"firstName\": \"Animesh\",\r\n" +
                "  \"lastName\": \"Prashant\",\r\n" +
                "  \"address\": {\r\n" +
                "    \"city\": \"Katihar\",\r\n" +
                "    \"State\": \"Bihar\"\r\n" +
                "  }\r\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        // Deserialize into Map
        Map<String,Object> parsedJsonObject = objectMapper.readValue(jsonObject, new TypeReference<Map<String, Object>>(){});
        // Get all keys
        Set<String> allKeys = parsedJsonObject.keySet();
        // Iterate keys
        allKeys.stream().forEach(key -> {
            Object value = parsedJsonObject.get(key);
            // If value is a String. You may need to add more if value is of different types
            if(value instanceof String)
                System.out.println(key);
                // If value is another JSON Object which will be LinkedHashMap. You can see this while debugging
            else if(value instanceof LinkedHashMap<?, ?>)
            {
                @SuppressWarnings("unchecked")
                Set<String> allKeysOfNestedJsonObject  = ((LinkedHashMap<String, ?>)value).keySet();
                allKeysOfNestedJsonObject.stream().forEach(k->System.out.println(k));
            }
        });

    }
}
