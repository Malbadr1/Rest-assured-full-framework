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
 * 📤 This test verifies file upload functionality using multipart/form-data.
 */
@Epic("API Testing")
@Feature("File Upload")
@Story("Upload file using POST /api/upload")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("📤 File Upload - Should return 200 OK")
public class FileUploadTest extends TestBase {

    @Test
    @Description("Ensure the file upload API works and returns status 200")
    void testFileUpload() {
        String path = "src/test/resources/files/sample.txt";
        Response response = UserService.uploadFile(path);

        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");
        LoggerUtil.printSuccess("✅ File uploaded successfully");
    }
}
