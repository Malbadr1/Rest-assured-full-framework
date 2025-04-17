package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ❌ This test verifies the API behavior when trying to fetch a non-existing user.
 * It sends a GET request for a user ID that does not exist and expects a 404 response.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Fetch non-existing user")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("❌ Get Invalid User - Should return 404 Not Found")
public class FailedUserTest extends TestBase {

    @Test
    @Description("Ensure the API returns 404 when requesting a user that does not exist (e.g., ID = 99999)")
    void shouldReturnNotFound_whenUserDoesNotExist() {

        System.out.print("🔢 Enter invalid user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        int invalidUserId  = scanner.nextInt();



        // 📌 Section
        LoggerUtil.printSection("NOT FOUND", "Testing non-existing user (ID = " + invalidUserId + ")");

        // 📤 Request info
        LoggerUtil.printRequest("GET", "/api/users/" + invalidUserId);

        // 🔄 Perform GET request
        Response response = UserService.getInvalidUser(invalidUserId);


        // 📥 Print response
        LoggerUtil.printResponse(response.statusCode(), response.asString());

        // ✅ Assertion
        assertEquals(404, response.statusCode(), "❌ Expected 404 Not Found");

        // 📌 Summary
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 User ID     : " + invalidUserId);
        System.out.println("📍 Status Code : 404 - Not Found");

        // ✅ Success
        LoggerUtil.printSuccess("✅ 404 Not Found - User does not exist as expected ❗");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
