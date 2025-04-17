package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🛠️ Update User Test
 * ✅ This test verifies that a user can be updated successfully via PUT request.
 * Expected: 200 OK with updated name and job in response.
 */
@Epic("API Testing")
@Feature("User Update")
@Story("Update user information using PUT request")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("🛠️ Update existing user and verify response")
public class UpdateUserTest extends TestBase {

    @Test
    @Description("Update an existing user (ID = 2) and verify name and job are correctly updated in response.")
    void shouldUpdateUserSuccessfully() {

        System.out.print("🔢 Enter user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();

        String updatedName = "Al badri Updated";
        String updatedJob = "QA Engineer";

        // 📌 Section
        LoggerUtil.printSection("UPDATE USER", "Testing update of user with ID: " + userId);

        // 📤 Request
        LoggerUtil.printRequest("PUT", "/api/users/" + userId);

        // 🔸 Payload
        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("name", updatedName);
        requestBody.put("job", updatedJob);
        LoggerUtil.printRequestBody(requestBody);

        // 🕒 Timing
        long start = System.currentTimeMillis();
        Response response = UserService.updateUser(userId, updatedName, updatedJob);
        long end = System.currentTimeMillis();
        long responseTime = end - start;
        LoggerUtil.printResponseTime(responseTime);

        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assertion
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertEquals(updatedName, response.jsonPath().getString("name"), "❌ Name mismatch");
        assertEquals(updatedJob, response.jsonPath().getString("job"), "❌ Job mismatch");

        // 📌 Summary
        LoggerUtil.printJsonSummary(response.jsonPath(), "id", "name", "username", "email", "job", "createdAt");

        // 🎉 Success
        LoggerUtil.printSuccess("✅ 200 OK - User updated successfully 🛠️");

    }
}
