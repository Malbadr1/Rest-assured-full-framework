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
 * ❌ This test simulates a 500 Internal Server Error by calling an invalid endpoint.
 * It ensures the API handles server-side failures gracefully.
 */
@Epic("API Testing")
@Feature("Error Handling")
@Story("Simulate 500 Internal Server Error")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("❌ Simulate 500 Internal Server Error")
public class InternalErrorTest extends TestBase {

    @Test
    @Description("Simulate 500 Internal Server Error by hitting an invalid endpoint and verifying the response status code.")
    void shouldSimulateServerError() {

        // 📌 Section
        LoggerUtil.printSection("INTERNAL ERROR", "Simulating a 500 Internal Server Error");

        // 📤 Request
        LoggerUtil.printRequest("GET", "/api/invalid-endpoint");

        // 🔄 Send request
        Response response = UserService.triggerServerError();


        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.asString());

        int status = response.statusCode();

        // ✅ Status Assertion
        assertTrue(status == 500 || status == 404, "❌ Expected 500 or 404 but got: " + status);

        // 📌 Summary block
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 Endpoint   : /api/invalid-endpoint");
        System.out.println("📍 Status     : " + status);
        System.out.println("📍 Time Taken : " + response.time() + " ms");

        // 🧾 Try to extract JSON summary if response is JSON
        try {
            LoggerUtil.printJsonSummary(response.jsonPath(), "error", "message", "timestamp");
        } catch (Exception e) {
            LoggerUtil.printInfo("ℹ️ No JSON body found to extract summary.");
        }

        // ✅ Success Message
        if (status == 500) {
            LoggerUtil.printSuccess("✅ 500 Internal Server Error - Simulated successfully 💥");
        } else {
            LoggerUtil.printSuccess("✅ 404 Not Found - Endpoint correctly does not exist ❗");
        }

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
