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
 * ✅ This test verifies that a user's job title can be partially updated using PATCH.
 * Expected: 200 OK and updated job field in the response.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Partially update user job using PATCH")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.NORMAL)
@DisplayName("✅ PATCH User - Update job field")
public class PatchUserTest extends TestBase {

    @Test
    @Description("Ensure the API successfully updates only the job field using PATCH request and returns 200 status code.")
    void shouldPatchUserJobSuccessfully() {

        System.out.print("🔢 Enter user ID to fetch: ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();

        String newJob = "Senior Automation Engineer";

        // 📌 Section title
        LoggerUtil.printSection("PATCH USER", "Testing partial update of user with ID: " + userId);

        // 📤 Request
        LoggerUtil.printRequest("PATCH", "/api/users/" + userId);

        // 🔄 Send request
        Response response = UserService.patchUser(userId, newJob);


        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        // ✅ Assertions
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertEquals(newJob, response.jsonPath().getString("job"), "❌ Job title not updated correctly");

        // 📌 Summary
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 ID         : " + userId);
        System.out.println("📍 Updated Job: " + newJob);
        System.out.println("📍 Status     : " + response.statusCode());
        System.out.println("📍 Time Taken : " + response.time() + " ms");

        // ✅ Success
        LoggerUtil.printSuccess("✅ 200 OK - User job updated successfully via PATCH 🛠️");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
