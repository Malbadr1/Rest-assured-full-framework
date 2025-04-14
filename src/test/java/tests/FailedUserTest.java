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
 * ❌ This test verifies the API behavior when trying to fetch a non-existing user.
 * Expected result: 404 Not Found.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Fetch non-existing user")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("❌ Get Invalid User - Should return 404 Not Found")
public class FailedUserTest extends TestBase {

    @Test
    @Description("Ensure the API returns 404 when requesting a user that does not exist (e.g., ID = 99999)")
    void shouldReturnNotFound_whenUserDoesNotExist() {

        LoggerUtil.printSection("NOT FOUND", "Testing non-existing user (ID = 99999)");
        LoggerUtil.printRequest("GET", "/api/users/99999");

        Response response = UserService.getInvalidUser(99999);

        LoggerUtil.printResponse(response.statusCode(), response.asString());

        assertEquals(404, response.statusCode(), "❌ Expected 404 Not Found");

        LoggerUtil.printSuccess("✅ 404 Not Found - User does not exist as expected ❗");
    }
}
