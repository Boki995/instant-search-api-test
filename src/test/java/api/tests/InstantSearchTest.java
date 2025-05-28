package api.tests;

import qa.testing.HelperMethods;
import org.json.JSONObject;
import qa.testing.TestDataGenerator;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class InstantSearchTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api-domain.com";
    }

    @Test(groups = "positive", priority = 1)
    public void testMultipleValidKeywords() {
        JSONObject payload = TestDataGenerator.generatePayload("developer,programming,java");
        HelperMethods.testInstantSearchExpectingSuccess(payload);
    }

    @Test(groups = "negative", priority = 4)
    public void testMissingAdditionsWords() {
        JSONObject payload = TestDataGenerator.generatePayload(null); // excludes additions_words
        payload.remove("additions_words");
        HelperMethods.testInstantSearchExpectingFailure(payload);
    }

    @Test(groups = "negative", priority = 3)
    public void testInvalidEmailFormat() {
        JSONObject payload = TestDataGenerator.generatePayload("developer,programming");
        payload.put("email", "losemail.com");
        HelperMethods.testInstantSearchExpectingFailure(payload);
    }

    @Test (groups = "negative", priority = 1)
    public void testInvalidCharacters() {
        JSONObject payload = TestDataGenerator.generatePayload("vdscsdvds,тестирование,1234,!@#$");
        HelperMethods.testInstantSearchExpectingFailure(payload);
    }

    @Test (groups = "negative", priority = 2)
    public void testBadSeparator() {
        JSONObject payload = TestDataGenerator.generatePayload("developer:programming.java");
        HelperMethods.testInstantSearchExpectingFailure(payload);
    }
    @Test(groups = "negative", priority = 5)
    public void testBooleansAsString() {
        JSONObject payload = TestDataGenerator.generatePayload("developer");
        payload.put("soc_media", "true");
        HelperMethods.testInstantSearchExpectingFailure(payload);
    }

}
