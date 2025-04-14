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
 * ✅ This test verifies that a user with ID = 2 can be fetched successfully
 * from the API, expecting a 200 OK response and a non-null email.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Get Existing User")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("✅ Get User - 200 OK when user exists")
public class GetUserTest extends TestBase {

    @Test
    @Description("Ensure the API returns 200 OK and valid email when fetching a user with ID = 2.")
    void shouldReturnUser_whenUserExists() {

        LoggerUtil.printSection("GET USER", "Testing user retrieval with ID = 2");

        LoggerUtil.printRequest("GET", "/api/users/2");
        Response response = UserService.getUser(2);

        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertNotNull(response.jsonPath().get("data.email"), "❌ Email should not be null");

        LoggerUtil.printSuccess("✅ 200 OK - User fetched successfully 🎉");
    }
}
