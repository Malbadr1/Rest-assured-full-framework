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
 * 🔐 This test verifies the GET user with Bearer Token authentication.
 */
@Epic("API Testing")
@Feature("Authentication")
@Story("Access protected endpoint with Bearer Token")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.BLOCKER)
@DisplayName("🔐 GET User with Bearer Token - Should return 200 OK")
public class AuthTokenTest extends TestBase {

    @Test
    @Description("Ensure the API returns 200 when accessed with valid Bearer token")
    void testGetUserWithBearerToken() {
        Response response = UserService.getUserWithBearerToken("your_token_here", 2);

        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        LoggerUtil.printSuccess("✅ Bearer token authentication succeeded");
    }
}
