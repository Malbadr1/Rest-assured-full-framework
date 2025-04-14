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
 * ❌ This test verifies the API behavior when receiving malformed JSON.
 * Expected result: 400 Bad Request response code.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Send malformed JSON to POST /api/users")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("❌ Malformed JSON should return 400 Bad Request")
public class BadRequestTest extends TestBase {

    @Test
    @Description("Verify that the API returns a 400 Bad Request when provided with invalid JSON payload")
    void shouldReturnBadRequest_onMalformedJson() {

        LoggerUtil.printSection("BAD REQUEST", "Testing malformed JSON input");
        LoggerUtil.printRequest("POST", "/api/users (malformed JSON)");

        Response response = UserService.sendBadRequest();

        LoggerUtil.printResponse(response.statusCode(), response.asString());

        assertEquals(400, response.statusCode(), "❌ Expected 400 Bad Request");

        LoggerUtil.printSuccess("✅ 400 Bad Request - Handled invalid input as expected 🚫");
    }
}
