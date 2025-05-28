package qa.testing;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HelperMethods {

    public static void testInstantSearchExpectingSuccess(JSONObject payload) {
        given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
        .when()
                .post("/api/v1/external/personal/instant-search")
        .then()
                .statusCode(201)
                .body("news", notNullValue())
                .body("social_media", notNullValue());
    }

    public static void testInstantSearchExpectingFailure(JSONObject payload) {
        given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
        .when()
                .post("/api/v1/external/personal/instant-search")
        .then()
                .statusCode(greaterThanOrEqualTo(400))
                .statusCode(lessThan(500));
    }
}
