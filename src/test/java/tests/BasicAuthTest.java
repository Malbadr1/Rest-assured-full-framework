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
 * 🔐 This test verifies the GET user with Basic Authentication.
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
        Response response = UserService.getUserWithBasicAuth("admin", "password123", 1);

        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        LoggerUtil.printSuccess("✅ Basic authentication succeeded");
    }
}
