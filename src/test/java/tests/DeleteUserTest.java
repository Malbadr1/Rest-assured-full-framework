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
 * ✅ This test verifies that a user can be deleted successfully.
 * Expected: 204 No Content status and empty response body.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Delete a user via DELETE /api/users/{id}")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("✅ Delete User - Should return 204 No Content")
public class DeleteUserTest extends TestBase {

    @Test
    @Description("Ensure the API successfully deletes a user and returns 204 status code with empty response body")
    void shouldDeleteUserSuccessfully() {

        int userId = 2;

        LoggerUtil.printSection("DELETE USER", "Testing deletion of user with ID: " + userId);
        LoggerUtil.printRequest("DELETE", "/api/users/" + userId);

        Response response = UserService.deleteUser(userId);

        LoggerUtil.printResponse(response.statusCode(), response.asString());

        assertEquals(204, response.statusCode(), "❌ Expected 204 No Content");
        assertTrue(response.asString().isEmpty(), "❌ Response should be empty");

        LoggerUtil.printSuccess("✅ 204 No Content - User deleted successfully 🗑️");
    }
}
