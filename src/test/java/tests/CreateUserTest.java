package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ✅ This test verifies that a user can be created successfully via POST request.
 * Expected: 201 Created status and correct response body fields.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Create a new user via POST /api/users")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("✅ Create User - Should return 201 Created with correct data")
public class CreateUserTest extends TestBase {

    @Test
    @Description("Ensure the API successfully creates a user and returns 201 status code with all expected fields")
    void shouldCreateUserSuccessfully() {

        // 🧑 Test user details
        String name = "Mohanad Albadri";
        String username = "moh.albadri";
        String email = "mohanad@example.com";
        String avatar = "https://avatars.githubusercontent.com/u/70086348";
        String status = "active";
        String role = "QA Tester";

        // 📌 Section Header
        LoggerUtil.printSection("CREATE USER", "Testing creation of user: " + name + " (" + role + ")");
        LoggerUtil.printRequest("POST", "/api/users");

        // 📦 Build request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("username", username);
        requestBody.put("email", email);
        requestBody.put("avatar", avatar);
        requestBody.put("status", status);
        requestBody.put("role", role);

        // 📤 Print payload

        LoggerUtil.printRequestBody(requestBody);

        // 🚀 Send POST request
        Response response = UserService.createUser(name, username, email, avatar, status, role);

        // 📥 Print response once

        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());


        // ✅ Assertions
        assertEquals(201, response.statusCode(), "❌ Expected 201 Created");
        assertEquals(name, response.jsonPath().get("name"), "❌ Name mismatch");
        assertEquals(username, response.jsonPath().get("username"), "❌ Username mismatch");
        assertEquals(email, response.jsonPath().get("email"), "❌ Email mismatch");
        assertEquals(role, response.jsonPath().get("role"), "❌ Role mismatch");

        // 📌 Summary once (no duplicate)
        LoggerUtil.printJsonSummary(response.jsonPath(), "id", "name", "username", "email", "role", "createdAt");

        // ✅ Print success message
        LoggerUtil.printSuccess("✅ 201 Created - User created successfully 🎉");
        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
