package tests;

import base.TestBase;
import io.qameta.allure.*;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ✅ This test verifies that a user with ID = 2 can be fetched successfully
 * from the API, expecting a 200 OK response and a non-null email.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Get Existing User")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("✅ Get User - 200 OK when user exists")
public class GetUserTest extends TestBase {

    @Test
    @Description("Ensure the API returns 200 OK and valid user fields when fetching a user with ID = 2.")
    void shouldReturnUser_whenUserExists() {

        System.out.print("🔢 Enter user ID : ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();


        // 📌 Print section header
        LoggerUtil.printSection("GET USER", "Testing user retrieval with ID = " + userId);

        // 📤 Print request details
        LoggerUtil.printRequest("GET", "/api/users/" + userId);

        // 🔄 Send the request and get the response
        Response response = UserService.getUser(userId);



        // 📥 Print raw response
        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        // ✅ Assertions
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertNotNull(response.jsonPath().get("email"), "❌ Email should not be null");

        // 📌 Print a formatted summary of key fields
        JsonPath json = response.jsonPath();
        LoggerUtil.printJsonSummary(json, "id", "name", "username", "email", "role", "createdAt");

        // ✅ Print success message
        LoggerUtil.printSuccess("✅ 200 OK - User fetched successfully 🎉");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
