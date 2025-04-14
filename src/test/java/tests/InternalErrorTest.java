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

        LoggerUtil.printSection("INTERNAL ERROR", "Simulating a 500 Internal Server Error");
        LoggerUtil.printRequest("GET", "/api/invalid-endpoint");

        Response response = UserService.triggerServerError();

        LoggerUtil.printResponse(response.statusCode(), response.asString());

        assertEquals(500, response.statusCode(), "❌ Expected 500 Internal Server Error");

        LoggerUtil.printSuccess("✅ 500 Internal Server Error - Simulated successfully 💥");
    }
}
