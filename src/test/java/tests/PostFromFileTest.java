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
 * 📂 This test sends a POST request using JSON data from an external file.
 */
@Epic("API Testing")
@Feature("Data-driven Testing")
@Story("Create user using JSON file input")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("📂 POST From File - Should return 201 Created")
public class PostFromFileTest extends TestBase {

    @Test
    @Description("Ensure the API successfully creates a user using JSON file input")
    void testPostUserFromFile() {
        String path = "src/test/resources/data/newUser.json";
        Response response = UserService.postUserFromFile(path);

        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());
        assertEquals(201, response.statusCode(), "❌ Expected 201 Created");
        LoggerUtil.printSuccess("✅ User created successfully from file");
    }
}
