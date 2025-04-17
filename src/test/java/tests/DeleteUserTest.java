package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🗑️ This test verifies that a user can be deleted successfully via DELETE request.
 * Note: The MockAPI returns 200 OK and user JSON (instead of 204 No Content).
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Delete a user via DELETE /api/users/{id}")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("🗑️ Delete User - Should return 200 OK with confirmation")
public class DeleteUserTest extends TestBase {

    @Test
    @Description("Ensure the API returns 200 OK and deleted user data when a user is deleted (MockAPI behavior)")
    void shouldDeleteUserSuccessfully() {

        System.out.print("🔢 Enter user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();


        // 📌 Section Header
        LoggerUtil.printSection("DELETE USER", "Testing deletion of user with ID: " + userId);

        // 📤 Request Info
        LoggerUtil.printRequest("DELETE", "/api/users/" + userId);
        System.out.println("🗑️ Sending DELETE request to /api/users/" + userId);

        // 🔄 Call the DELETE endpoint
        Response response = UserService.deleteUser(userId);


        // 📥 Print full response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assert actual behavior of MockAPI (200 + JSON)
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK from MockAPI");
        assertFalse(response.asString().isEmpty(), "❌ Response should contain deleted user info");

        // 📌 Print Summary
        LoggerUtil.printJsonSummary(response.jsonPath(), "id", "name", "username", "email", "role", "createdAt");

        // ✅ Final success
        LoggerUtil.printSuccess("✅ 200 OK - User deleted successfully 🗑️ (MockAPI behavior)");
        // ⏱️ Measure & print response time
        LoggerUtil.printResponseTime(response.time());
    }
}
