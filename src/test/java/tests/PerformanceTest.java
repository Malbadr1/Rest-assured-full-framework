package tests;

import base.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ⏱️ Performance Test
 * Verifies that the API responds within the expected time limit (e.g., < 3000ms).
 */
@Epic("API Testing")
@Feature("Performance")
@Story("Response Time Validation")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("⏱️ API should respond within 3000ms")
public class PerformanceTest extends TestBase {

    @Test
    @Description("Check if the GET request to /api/users/2 responds within 3 seconds.")
    void shouldRespondWithinTimeLimit() {

        LoggerUtil.printSection("PERFORMANCE", "Measuring response time for user request");
        LoggerUtil.printRequest("GET", "/api/users/2");

        long responseTime = UserService.getResponseTime(2);

        LoggerUtil.printInfo("⏱️ Total Response Time: " + responseTime + "ms");

        assertTrue(responseTime < 3000, "❌ API is too slow! Took: " + responseTime + "ms");

        LoggerUtil.printSuccess("✅ Response time is acceptable: " + responseTime + "ms ⚡");
    }
}
