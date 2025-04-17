package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 🔐 This test verifies Bearer Token authentication scenarios:
 * 1. No token (expect 401)
 * 2. Invalid token (expect 401 or 403)
 * 3. Valid token (expect 200 or 404 if user doesn't exist)
 */
@Epic("API Testing")
@Feature("Authentication")
@Story("Bearer Token Access")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.BLOCKER)
@DisplayName("🔐 Bearer Token Authentication Test")

public class AuthTokenTest extends TestBase {

    private static int userId;

    @BeforeAll
    public static void readUserIdFromConsole() {
        System.out.print("🔢 Enter user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        userId = scanner.nextInt();
    }


    @Test
    @Description("1️⃣ Valid token - Expect 200 OK or 404 if user doesn't exist")
    void testValidToken() {
        String token = "your_valid_token";

        LoggerUtil.printSection("AUTH TOKEN", "✅ Valid Token - Testing GET with Bearer Token");
        LoggerUtil.printRequest("GET", "/api/users/" + userId);
        System.out.println("🚡️ Sending GET with valid Bearer token");

        Response response = UserService.getUserWithBearerToken(token, userId);
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        if (response.statusCode() == 200) {
            LoggerUtil.printSuccess("✅ Access granted – Token is valid 🎯");
            LoggerUtil.printJsonSummary(response.jsonPath(), "id", "name", "email", "username", "createdAt");
        } else if (response.statusCode() == 404) {
            LoggerUtil.printError("⚠️ Valid token, but user ID not found (404)");
        } else {
            LoggerUtil.printError("❌ Unexpected response: " + response.statusCode());
        }
        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);

    }

    @Test
    @Description("2️⃣ Missing token - Expect 401 Unauthorized")
    void testMissingToken() {
        LoggerUtil.printSection("AUTH TOKEN", "🚫 Missing Token - Testing unauthorized access");
        LoggerUtil.printRequest("GET", "/api/users/" + userId);
        System.out.println("🚡️ Sending GET without Bearer token");

        Response response = UserService.getUserWithoutToken(userId);
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        if (response.statusCode() == 401) {
            LoggerUtil.printError("❌ 401 Unauthorized – Token is required");
        } else {
            LoggerUtil.printError("⚠️ Expected 401, but got " + response.statusCode());
        }
        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }

    @Test
    @Description("3️⃣ Invalid token - Expect 401 or 403")
    void testInvalidToken() {
        String token = "invalid_token";

        LoggerUtil.printSection("AUTH TOKEN", "❌ Invalid Token - Testing access with wrong token");
        LoggerUtil.printRequest("GET", "/api/users/" + userId);
        System.out.println("🚡️ Sending GET with invalid Bearer token");

        Response response = UserService.getUserWithBearerToken(token, userId);
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        if (response.statusCode() == 401 || response.statusCode() == 403) {
            LoggerUtil.printError("❌ Access denied – Invalid token (code " + response.statusCode() + ")");
        } else {
            LoggerUtil.printError(" ❌ Invalid Token - Testing access with wrong token\n"+"⚠️ Expected 401/403, but got " + response.statusCode());
        }
        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }

}
