package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.LoggerUtil;

import java.util.Scanner;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 🧾 This test validates the structure of the response against a predefined JSON schema.
 * The schema must match the actual JSON returned by the API (without "data" wrapper).
 */
@Epic("API Testing")
@Feature("Schema Validation")
@Story("Ensure API response matches expected schema")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("🧾 Validate JSON schema for /api/users/{id}")
public class SchemaValidationTest extends TestBase {

    @Test
    @Description("Validates that the GET /api/users/{id} response matches the defined schema in user-schema.json")
    void shouldMatchUserSchema() {
        System.out.print("🔢 Enter user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();


        // 📌 Section header
        LoggerUtil.printSection("SCHEMA VALIDATION", "Validating JSON schema of user with ID: " + userId);
        LoggerUtil.printRequest("GET", "/api/users/" + userId);

        // 🔄 Send request
        Response response = given()
                .baseUri("https://67ff96c958f18d7209f1dac6.mockapi.io")
                .when()
                .get("/api/users/" + userId);

        // ⏱️ Log response time
        long responseTime = response.time();
        LoggerUtil.printResponseTime(responseTime);

        // 📥 Print response
        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        // ✅ Assert status code is 200
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");

        // 📊 Schema validation
        LoggerUtil.printInfo("🧾 Validating JSON Schema against schemas/user-schema.json");
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));

        // 📌 Summary
        System.out.println("📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 ID           : " + userId);
        System.out.println("📍 Status Code  : " + response.statusCode());
        System.out.println("📍 Time Taken   : " + responseTime + "ms");

        // ✅ Success message
        LoggerUtil.printSuccess("✅ JSON Schema validation passed successfully! 🎉");
    }
}
