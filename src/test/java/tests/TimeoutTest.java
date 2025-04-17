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
 * ⏳ Timeout Simulation Test
 * ✅ This test simulates a timeout scenario to test how the API handles delayed responses.
 */
@Epic("API Testing")
@Feature("Performance & Resilience")
@Story("Simulate timeout during API call")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("⏳ Simulate Timeout - Should handle slow responses")
public class TimeoutTest extends TestBase {

    @Test
    @Description("Ensure the API properly handles slow response scenarios (e.g., delay > client timeout threshold)")
    void testTimeoutSimulation() {

        int timeoutMs = 1000;

        // 🧪 Section Title
        LoggerUtil.printSection("TIMEOUT SIMULATION", "Simulating a delayed response with timeout = " + timeoutMs + "ms");

        // 📤 Request
        LoggerUtil.printRequest("GET", "/api/slow");

        // 🕒 Execute slow request
        long start = System.currentTimeMillis();
        Response response = UserService.simulateTimeout(timeoutMs);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;



        // 📥 Response details
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assertion
        int status = response.statusCode();
        assertTrue(status == 200 || status == 500 || status == 504,
                "❌ Expected status 200, 500, or 504 but got: " + status);

        // 🎯 Success message
        LoggerUtil.printSuccess("✅ Timeout test executed and response was handled in " + elapsedTime + "ms");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
