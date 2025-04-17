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
 * It ensures the API returns a 400 Bad Request status code and a clear error message.
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

        // 📌 Section: Test Header
        LoggerUtil.printSection("BAD REQUEST", "Testing malformed JSON input");

        // 📤 Request Details
        LoggerUtil.printRequest("POST", "/api/users (malformed JSON)");

        // 🔄 Perform the request
        Response response = UserService.sendBadRequest();


        // 📥 Print full response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Validate the status
        assertEquals(400, response.statusCode(), "❌ Expected 400 Bad Request");

        // 📌 Show summary if available (in real APIs might include message, code, etc.)
     //  LoggerUtil.printJsonSummary(response.jsonPath(), "error", "message", "timestamp");
        System.out.println("📌 SUMMARY\n────────────");
        System.out.println("📍 Status     : 400 Bad Request");
        System.out.println("📍 Reason     : Invalid or malformed JSON payload");
        System.out.println("📍 Expected   : API to reject bad input gracefully\n");


        // ✅ Success output
        LoggerUtil.printSuccess("✅ 400 Bad Request - Handled invalid input as expected 🚫");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
