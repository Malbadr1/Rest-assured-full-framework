package tests;

import base.TestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import service.UserService;
import utils.LoggerUtil;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ✅ This test verifies that a user's job title can be partially updated using PATCH.
 * Expected: 200 OK and updated job field in the response.
 */
public class PatchUserTest extends TestBase {

    @Test
    void shouldPatchUserJobSuccessfully() {

        int userId = 2;
        String newJob = "Senior Automation Engineer";

        LoggerUtil.printSection("PATCH USER", "Testing partial update of user with ID: " + userId);
        LoggerUtil.printRequest("PATCH", "/api/users/" + userId);

        Response response = UserService.patchUser(userId, newJob);

        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        assertEquals(newJob, response.jsonPath().getString("job"), "❌ Job title not updated correctly");

        LoggerUtil.printSuccess("✅ 200 OK - User job updated successfully via PATCH 🛠️");
    }
}