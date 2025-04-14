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
 * ✅ This test verifies that a user can be updated successfully.
 * Expected: 200 OK status with updated name and job in response.
 */
@Epic("API Testing")
@Feature("User Update")
@Story("Update user information using PUT request")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("🛠️ Update existing user and verify response")
public class UpdateUserTest extends TestBase {

    @Test
    @Description("Update an existing user and verify the name and job fields match the request.")
    void shouldUpdateUserSuccessfully() {

        int userId = 2;
        String updatedName = "Al badri Updated";
        String updatedJob = " QA Engineer";

        LoggerUtil.printSection("UPDATE USER", "Testing update of user with ID: " + userId);
        LoggerUtil.printRequest("PUT", "/api/users/" + userId);

        Response response = UserService.updateUser(userId, updatedName, updatedJob);

        LoggerUtil.printResponse(response.statusCode(), response.asString());

        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertEquals(updatedName, response.jsonPath().getString("name"), "❌ Name mismatch");
        assertEquals(updatedJob, response.jsonPath().getString("job"), "❌ Job mismatch");

        LoggerUtil.printSuccess("✅ 200 OK - User updated successfully 🛠️");
    }
}
