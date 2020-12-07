import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class AbcTests {

    @Test
    public void test_1()
    {

                given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }

    @Test
    public void test_2()
    {
                 given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }
    @Test
    public void test_3()
    {
                given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }


    @Test
    public void test_4()
    {
                given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }
}
