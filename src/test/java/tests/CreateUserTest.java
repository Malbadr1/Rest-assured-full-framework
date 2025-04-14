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
 * ✅ This test verifies that a user can be created successfully via POST request.
 * Expected: 201 Created status and correct response body fields.
 */
@Epic("API Testing")
@Feature("User Endpoint")
@Story("Create a new user via POST /api/users")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("✅ Create User - Should return 201 Created with correct data")
public class CreateUserTest extends TestBase {

    @Test
    @Description("Ensure the API successfully creates a user and returns 201 status code with expected name and job fields")
    void shouldCreateUserSuccessfully() {

        String name = "Ali";
        String job = "QA Tester";

        LoggerUtil.printSection("CREATE USER", "Testing creation of user: " + name + " (" + job + ")");
        LoggerUtil.printRequest("POST", "/api/users");

        Response response = UserService.createUser(name, job);

        LoggerUtil.printResponse(response.statusCode(), response.getBody().asPrettyString());

        assertEquals(201, response.statusCode(), "❌ Expected 201 Created");
        assertEquals(name, response.jsonPath().get("name"), "❌ Name mismatch");
        assertEquals(job, response.jsonPath().get("job"), "❌ Job mismatch");

        LoggerUtil.printSuccess("✅ 201 Created - User created successfully 🎉");
    }
}
