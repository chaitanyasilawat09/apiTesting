import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

public class AbcTests {

    @Test(priority = 10)
    public void test_1()
    {
        RestAssured.registerParser("application/text", Parser.JSON);
            Response response=  given().
                get("https://reqres.in/api/users?page=2").
                then().extract().response();
        System.out.println(response.asString());
                assertThat(response.body().jsonPath().get("data.id"),hasItems(7,8,9,10));
                assertThat(response.asString().isBlank(),is(false));
            assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));;

//        int lottoId = with(response.asString()).getInt("data.id");
//                List winnerIds = with(response.asString()).get("data.email");


        System.out.println(response.body().jsonPath().get("data.id").toString());
//        System.out.println(lottoId);
//        System.out.println(winnerIds);
//                statusCode(200).
//                body("data.id",hasItems(7,8,9,10,17)).
//                body ("data.first_name", hasItems("Michael","Lindsay"));

    }
    RequestSpecification requestSpecification;

    @Test(priority = -1000)
    public void test_5()
    {
        requestSpecification.with().basePath("https://reqres.in/api/users?page=2")
//                get("https://reqres.in/api/users?page=2").
                .then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }

    @Test(priority = -100)
    public void test_2()
    {
                 given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));
                 //    assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));;

    }
    @Test(priority = -1)
    public void test_3()
    {
                given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[1]",equalTo(8)).
                body ("data.first_name", hasItems("Michael","Lindsay"));

    }


    @Test(priority = -1000)
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
