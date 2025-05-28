package api.tests;

import qa.testing.HelperMethods;
import org.json.JSONObject;
import qa.testing.TestDataGenerator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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

    @Test(groups = "positive", priority = 2)
    public void testSingleKeyword() {
        JSONObject payload = TestDataGenerator.generatePayload("testing");
        HelperMethods.testInstantSearchExpectingSuccess(payload);
    }

    @Test (groups = "negative", priority = 3)
    public void testEmptyString() {
        JSONObject payload = TestDataGenerator.generatePayload("");
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

}
