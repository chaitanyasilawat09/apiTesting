import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {




    @org.testng.annotations.Test
    public void test212(){
        // count ad in string
        Pattern p =  Pattern.compile("hello");
        Matcher m = p.matcher("");
        int count1 = 0;

        while (m.find()){
            count1++;
        }
        System.out.println(count1);
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(input);



//        //reverce of String
//        String s ="chaitanya Silawat";
//        StringBuilder s1 = new StringBuilder();
//        StringBuilder s2 = new StringBuilder();
//        s1.append(s);
//        System.out.println(s1.reverse());
//        System.out.println(s.replace("chai","aayu"));
//        int count = 0;
//        char [] i1= s.toCharArray();
//        for (int j = i1.length-1;j>=0;j--){
//            System.out.println(i1[j]);
//            s2.append(i1[j]);
//            if (i1[j]=='a'){
//                count ++;
//            }
//        }
//        System.out.println(s2);
//        System.out.println(count);
    }


    @org.testng.annotations.Test
    public void test12() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String userDir = System.getProperty("user.dir");
        File outputJsonFile = new File(userDir + "/files/data.json");

        // read value from JSOM file
        Object emp = objectMapper.readValue(outputJsonFile, Object.class);
        // System.out.println(emp);
        String employeeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);

        JSONArray object = new JSONObject(employeeJson).getJSONObject("properties").getJSONObject("token").getJSONArray("examples");
        System.out.println(object);

//       List<Object> arr =new
//               ArrayList{"4","8","13","10"};
//      TODO read all Id value from JSON Array
        System.out.println(getValuesForGivenKey(object, "id"));
//       // assertThat(getValuesForGivenKey(object, "id"), hasItem(arr));

    }

    @org.testng.annotations.Test
    public void test() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();

        objectNode.put("Name", "Isha");
        objectNode.put("LastName", "Kumawat");
        objectNode.put("Class", "9th");
        objectNode.put("Age", 15);
        objectNode.put("IsMad", false);

        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode));
        System.out.println(objectMapper.writeValueAsString(objectNode));

        ObjectNode objectNode1 = objectMapper.createObjectNode();
        objectNode1.put("Father's Name", "Rajesh");
        objectNode1.put("Mother's Name", "Seema");
        objectNode1.put("Address", " Indore MP");

        objectNode.put("family Details", objectNode1);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode));

        Iterator<String> iterator = objectNode.fieldNames();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public List<String> getValuesForGivenKey(JSONArray jsonArrayStr, String key) {
        JSONArray jsonArray = jsonArrayStr;
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }
}
