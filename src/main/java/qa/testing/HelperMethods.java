package qa.testing;
import io.restassured.http.ContentType;
import org.json.JSONObject;

import static com.sun.org.apache.bcel.internal.Repository.instanceOf;
import static io.restassured.RestAssured.*;
import static org.apache.commons.lang3.Validate.matchesPattern;
import static org.hamcrest.Matchers.*;

public class HelperMethods {

    public static void testInstantSearchExpectingSuccess(JSONObject payload) {
        given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .log().all()
        .when()
                .post("/api/v1/external/personal/instant-search")
        .then()
                .statusCode(200)
                .body("news", notNullValue())
                .body("social_media", notNullValue())

                // Validating first news object fields
                .body("news[0].id", instanceOf(Integer.class))
                .body("news[0].url", matchesPattern("^(https?://).+"))
                .body("news[0].title", not(emptyOrNullString()))
                .body("news[0].description", not(emptyOrNullString()))
                .body("news[0].source_name", not(emptyOrNullString()))
                .body("news[0].soc_media", instanceOf(Boolean.class))
                .body("news[0].news", instanceOf(Boolean.class))

                // Validating first social_media object fields
                .body("social_media[0].id", instanceOf(Integer.class))
                .body("social_media[0].url", matchesPattern("^(https?://).+"))
                .body("social_media[0].title", not(emptyOrNullString()))
                .body("social_media[0].description", not(emptyOrNullString()))
                .body("social_media[0].source_name", not(emptyOrNullString()))
                .body("social_media[0].soc_media", instanceOf(Boolean.class))
                .body("social_media[0].news", instanceOf(Boolean.class))
                .log().all();

    }

    public static void testInstantSearchExpectingFailure(JSONObject payload) {
        given()
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .log().all()
        .when()
                .post("/api/v1/external/personal/instant-search")
        .then()
                .log().all()
                .statusCode(greaterThanOrEqualTo(400))
                .statusCode(lessThan(500));
    }
}
