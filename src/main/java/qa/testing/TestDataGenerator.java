package qa.testing;

import com.github.javafaker.Faker;
import org.json.JSONObject;

import java.util.List;

public class TestDataGenerator {
    static Faker faker = new Faker();

    public static JSONObject generatePayload(String fieldValidation) {
        JSONObject body = new JSONObject();
        body.put("first_name", faker.name().firstName());
        body.put("last_name", faker.name().lastName());
        body.put("date_of_birth", "1987-02-21");
        body.put("email", faker.internet().emailAddress());
        body.put("phone_number", faker.phoneNumber().cellPhone());
        body.put("country_ids", List.of("49", "50"));
        body.put("soc_media", true);
        body.put("news", true);
        body.put("additions_words", fieldValidation);
        body.put("social_media_ids", List.of("1", "2"));

        return body;
    }
}
