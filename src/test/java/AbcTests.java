import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
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
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

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
        System.out.println("hello");

//                given().
//                get("https://reqres.in/api/users?page=2").
//                then().
//                    statusCode(200).
//                body("data.id[1]",equalTo(8)).
//                body ("data.first_name", hasItems("Michael","Lindsay"));

    }
    @Test(priority = -899)
    public void test_6()
    {
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
               // body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }
}
