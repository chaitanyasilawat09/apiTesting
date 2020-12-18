import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class VerifyJsonSchema {

    @Test
    public void verifyJsonSchema() {

        String jsonStringPayload = "{\"username\" : \"admin\",\"password\" : \"password123\"}";

        // GIVEN
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .contentType(ContentType.JSON)
                .body(jsonStringPayload)
                // WHEN
                .when()
                .post()
                // THEN
                .then()
                .assertThat()
                .statusCode(200)
                .body("token", Matchers.notNullValue())
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("AuthJsonSchema.json"));
    }

}
