import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class DefaultHostAndPortExample {
@Test
    public  void Tests() {

        // Hitting a GET request without setting any base URI and Path
        RestAssured
                .given()
                // Logging all details
                .log()
                .all()
                .when()
                .get();


    }


}
