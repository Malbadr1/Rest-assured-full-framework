package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 📂 This test sends a POST request using JSON data from an external file.
 * ✅ Verifies that the API accepts data from file and creates the user successfully.
 */
@Epic("API Testing")
@Feature("Data-driven Testing")
@Story("Create user using JSON file input")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("📂 POST From File - Should return 201 Created")
public class PostFromFileTest extends TestBase {

    @Test
    @Description("Ensure the API successfully creates a user using JSON file input")
    void testPostUserFromFile() {

        String filePath = "src/test/resources/data/newUser.json";

        // 📌 Section
        LoggerUtil.printSection("POST FROM FILE", "Testing user creation using file: " + filePath);
        LoggerUtil.printRequest("POST", "/api/users");

        // 📤 Send request
        Response response = UserService.postUserFromFile(filePath);

        // ⏱️ Time
        long time = response.time();
        LoggerUtil.printResponseTime(time);

        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assertion
        assertEquals(201, response.statusCode(), "❌ Expected 201 Created");

        // 📌 Summary
        JsonPath json = response.jsonPath();
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 Name       : " + json.getString("name"));
        System.out.println("📍 Username   : " + json.getString("username"));
        System.out.println("📍 Email      : " + json.getString("email"));
        System.out.println("📍 Role       : " + json.getString("role"));
        System.out.println("📍 ID         : " + json.getString("id"));
        System.out.println("📍 CreatedAt  : " + json.getString("createdAt"));

        LoggerUtil.printSuccess("✅ User created successfully from file 🎯");

    }
}
