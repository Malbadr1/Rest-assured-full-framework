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
 * 🔐 This test verifies the GET user with Basic Authentication.
 * It ensures that valid credentials return a 200 OK and proper user data.
 */
@Epic("API Testing")
@Feature("Authentication")
@Story("Access protected endpoint with Basic Auth")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.BLOCKER)
@DisplayName("🔐 GET User with Basic Auth - Should return 200 OK")
public class BasicAuthTest extends TestBase {

    @Test
    @Description("Ensure the API returns 200 when accessed with valid Basic Auth credentials")
    void testGetUserWithBasicAuth() {

        // 📌 Section header
        LoggerUtil.printSection("BASIC AUTH", "Testing GET with valid Basic Auth for user ID = 1");

        // 📤 Request
        LoggerUtil.printRequest("GET", "/api/users/1");
        System.out.println("🔐 Using credentials → Username: admin | Password: password123");

        // 🔄 Perform request
        Response response = UserService.getUserWithBasicAuth("admin", "password123", 1);



        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assertion
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");

        // 📌 Summary
        JsonPath json = response.jsonPath();
        LoggerUtil.printJsonSummary(json,
                "id", "name", "username", "email", "role", "createdAt");

        // ✅ Success
        LoggerUtil.printSuccess("✅ Basic authentication succeeded");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }

}
