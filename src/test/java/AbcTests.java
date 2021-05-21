import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class AbcTests {

    @Test
    public void test_1() {
        System.out.println("hello");
        String abc = System.getProperty("awskey");
        System.out.println(abc + ".........abc is ");
        System.out.println(System.getProperty("awskey") + "......aws key is this");
        System.out.println(System.getProperty("slacktocken") + "......slacktocken key is this");
//                given().
//                get("https://reqres.in/api/users?page=2").
//                then().
//                statusCode(200).
//                body("data.id[1]",equalTo(8)).
//                body ("data.first_name", hasItems("Michael","Lindsay"));

    }

    @Test
    public void test_2() {
        System.out.println("hello");
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]", equalTo(8)).
                body("data.first_name", hasItems("Michael", "Lindsay"));

    }

    @Test
    public void test_3() {
        System.out.println("hello");
//                given().
//                get("https://reqres.in/api/users?page=2").
//                then().
//                statusCode(200).
//                body("data.id[1]",equalTo(8)).
//                body ("data.first_name", hasItems("Michael","Lindsay"));

    }


    @Test
    public void test_4() {
        int i=5;
        int j=2;
        int k =i/j;
        System.out.println(k);
        System.out.println("hello");

//                given().
//                get("https://reqres.in/api/users?page=2").
//                then().
//                    statusCode(200).
//                body("data.id[1]",equalTo(8)).
//                body ("data.first_name", hasItems("Michael","Lindsay"));

    }

    @Test(priority = -899)
    public void test_6() {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                // body("data.id[1]",equalTo(8)).
                        body("data.first_name", hasItems("Michael", "Lindsay","George"));

//        System.out.println(jsonPath.getList("data.first_name"));
//        assertThat(jsonPath.getList("data.first_name"),hasItems("Michael", "Lindsay", "Tobias", "Byron"));

    }

    @Test
    public void test_5() throws SQLException {
        Response res = given().
                get("https://reqres.in/api/users?page=2").
                then().extract().response();
        System.out.println(res.asString());
        // get all column name inside Response
        JSONObject jsonObject = new JSONObject(res.asString());
        for (String key: jsonObject.keySet()) {
            System.out.println(key);
        }

        // get particular attribute from Jsom Array and verify by asserts
        List<Object> l = new ArrayList(res.jsonPath().get("data"));
        JSONObject jsonObject1 = new JSONObject(res.asString());

        System.out.println(jsonObject1.getJSONArray("data").get(0));
        assertThat(l, hasItems("michael.lawson@reqres.in", "george.edwards@reqres.in"));
    }
}
