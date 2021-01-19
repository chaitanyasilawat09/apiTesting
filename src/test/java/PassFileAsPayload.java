import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;

public class PassFileAsPayload {


    @Test
    public void passFileAsPayload()
    {
        // Creating a File instance
        File jsonDataInFile = new File("files/AuthPayload.json");

        //GIVEN
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .contentType(ContentType.JSON)
                .body(jsonDataInFile)
                // WHEN
                .when()
                .post()
                // THEN
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", Matchers.notNullValue())
                .body("token.length()", Matchers.is(15));
               // .body("token", Matchers.matchesRegex("^[a-z0-9]+$"));
    }
}
