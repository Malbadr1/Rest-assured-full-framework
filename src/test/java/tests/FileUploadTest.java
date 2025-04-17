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
 * It uploads a file to the /api/upload endpoint and expects a 200 OK response.
 */
@Epic("API Testing")
@Feature("File Upload")
@Story("Upload file using POST /api/upload")
@Owner("Mohanad Albadri")
@Severity(SeverityLevel.CRITICAL)
@DisplayName("📤 File Upload - Should return 200 OK")
public class FileUploadTest extends TestBase {

    @Test
    @Description("Ensure the file upload API works and returns status 200 with proper handling")
    void testFileUpload() {
        String filePath = "src/test/resources/files/sample.txt";

        // 📌 Section
        LoggerUtil.printSection("FILE UPLOAD", "Uploading file from: " + filePath);

        // 📤 Request
        LoggerUtil.printRequest("POST", "/api/upload");
        System.out.println("📎 File: " + filePath);

        // 🔄 Perform Upload
        Response response = UserService.uploadFile(filePath);


        // 📥 Response
        LoggerUtil.printResponse(response.statusCode(), response.asPrettyString());

        // ✅ Assertion
        assertEquals(200, response.statusCode(), "❌ Expected 200 OK");

        // 📌 Summary
        System.out.println("\n📌 SUMMARY");
        System.out.println("────────────");
        System.out.println("📍 File       : " + filePath);
        System.out.println("📍 Status     : " + response.statusCode());
        System.out.println("📍 Upload Time: " + response.time() + " ms");

        // ✅ Done
        LoggerUtil.printSuccess("✅ File uploaded successfully 📤");

        // ⏱️ Measure and print response time
        long time = response.time();
        LoggerUtil.printResponseTime(time);
    }
}
