package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🧾 JSON Schema Validation Test
 * Validates that the structure of the user response matches the expected schema.
 */
@Epic("API Testing")
@Feature("Schema Validation")
@Story("User schema matches the expected JSON structure")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("🧾 Validate user JSON schema (ID = 2)")
public class SchemaValidationTest extends TestBase {

    @Test
    @Description("Ensure the API response for GET /api/users/2 matches the defined JSON schema in user-schema.json")
    void shouldMatchExpectedUserSchema() {

        LoggerUtil.printSection("SCHEMA VALIDATION", "Checking response structure for user 2");
        LoggerUtil.printRequest("GET", "/api/users/2");

        Response response = UserService.getUser(2);

        LoggerUtil.printInfo("📥 Status Code: " + response.statusCode());
        assertEquals(200, response.statusCode(), "Expected 200 OK");

        LoggerUtil.printInfo("🧾 Validating JSON Schema against user-schema.json");

        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        LoggerUtil.printSuccess("✅ JSON Schema validation passed!");
    }
}
