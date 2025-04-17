package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ⏱️ Performance Test
 * ✅ Verifies that the API responds within the expected time limit (e.g., < 3000ms).
 */
@Epic("API Testing")
@Feature("Performance")
@Story("Response Time Validation")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("⏱️ API should respond within 3000ms")
public class PerformanceTest extends TestBase {

    @Test
    @Description("Check if the GET request to /api/users/{id} responds within 3 seconds.")
    void shouldRespondWithinTimeLimit() {

        // 🎯 STEP 1: Create a new user to ensure the ID exists before testing GET
        LoggerUtil.printSection("CREATE USER", "Creating user before measuring GET performance");

        String name = "Mohanad Albadri";
        String username = "moh.albadri";
        String email = "mohanad@example.com";
        String avatar = "https://avatars.githubusercontent.com/u/70086348";
        String status = "active";
        String role = "Performance Tester";

        // 📤 Log the request details for creating the user
        LoggerUtil.printRequest("POST", "/api/users");
        LoggerUtil.printRequestBody(name, username, email, avatar, status, role);

        // 🔄 Send the POST request to create user
        Response postResponse = UserService.createUser(name, username, email, avatar, status, role);

        // ⏱️ Log the time taken to create the user
        LoggerUtil.printResponseTime(postResponse.time());

        // 📥 Print the response from the POST request
        LoggerUtil.printResponse(postResponse.statusCode(), postResponse.getBody().asPrettyString());

        // 🔎 Extract the newly created user's ID for performance test
        String userId = postResponse.jsonPath().getString("id");

        // 📌 Print a quick summary of created user
        LoggerUtil.printJsonSummary(postResponse.jsonPath(), "id", "name", "username", "email", "role", "createdAt");
        LoggerUtil.printSuccess("✅ 201 Created - User created successfully 🎉");

        // 🎯 STEP 2: Test performance of GET /api/users/{id}
        LoggerUtil.printSection("PERFORMANCE", "Measuring response time for GET /api/users/" + userId);
        LoggerUtil.printRequest("GET", "/api/users/" + userId);

        // ⏱️ Send GET request and measure response time
        Response getResponse = UserService.getUser(Integer.parseInt(userId));
        long responseTime = getResponse.time();

        // 🕒 Log response time
        LoggerUtil.printResponseTime(responseTime);

        // 📥 Print GET response
        LoggerUtil.printResponse(getResponse.statusCode(), getResponse.getBody().asPrettyString());

        // ✅ Assert that the response status is 200 OK
        assertEquals(200, getResponse.statusCode(), "❌ Expected 200 OK");

        // ⚠️ Fail if response time exceeds 3000ms
        assertTrue(responseTime < 3000, "❌ API response time too slow: " + responseTime + "ms (Expected < 3000ms)");

        // 📌 Final summary block
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 ID           : " + userId);
        System.out.println("📍 Status Code  : " + getResponse.statusCode());
        System.out.println("📍 Time Taken   : " + responseTime + "ms");

        // ✅ Print final success message
        LoggerUtil.printSuccess("✅ Performance OK - Response time: " + responseTime + "ms ⚡");
    }
}
