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
 * ⏳ This test simulates a timeout scenario to test response delay handling.
 */
@Epic("API Testing")
@Feature("Performance & Resilience")
@Story("Simulate timeout during API call")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("⏳ Simulate Timeout - Should handle slow responses")
public class TimeoutTest extends TestBase {

    @Test
    @Description("Ensure the API properly handles slow response scenarios")
    void testTimeoutSimulation() {
        Response response = UserService.simulateTimeout(1000);

        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());
        int status = response.statusCode();
        assertTrue(status == 200 || status == 504 || status == 500,
                "❌ Expected status 200, 500, or 504 but got: " + status);
        LoggerUtil.printSuccess("✅ Timeout test executed");
    }
}
