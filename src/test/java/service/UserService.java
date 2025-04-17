package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.config.HttpClientConfig.httpClientConfig;

/**
 * ✅ This class provides reusable REST API methods for User operations.
 *
 * Handles the following:
 * - ✅ GET user by ID
 * - ✅ POST a new user with full fields
 * - ✅ PUT to update user
 * - ✅ PATCH to partially update user
 * - ✅ DELETE user
 * - ✅ Negative test scenarios (404 Not Found, 400 Bad Request, etc.)
 * - ✅ Simulated server error (500 Internal Server Error)
 * - ✅ Response time retrieval (for performance testing)
 * - ✅ Bearer Token Authentication
 * - ✅ Basic Authentication
 * - ✅ Timeout simulation
 * - ✅ JSON Schema validation
 * - ✅ Read JSON from external file
 * - ✅ File Upload (multipart)
 */
public class UserService {

    /**
     * ✅ GET a user by ID
     * @param id The user ID to retrieve
     * @return Response containing user data
     */
    public static Response getUser(int id) {
        System.out.println("\uD83D\uDCE4 Sending GET request to /api/users/" + id);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ✅ POST a new user with full details
     * Sends the user data to the /api/users endpoint and returns the response.
     *
     * @param name     Full name of the user
     * @param username Username
     * @param email    Email address
     * @param avatar   Avatar URL
     * @param status   User status (e.g., active)
     * @param role     User role (e.g., Admin, QA Tester)
     * @return Response object from the API
     */
    public static Response createUser(String name, String username, String email, String avatar, String status, String role) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("username", username);
        requestBody.put("email", email);
        requestBody.put("avatar", avatar);
        requestBody.put("status", status);
        requestBody.put("role", role);

        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .extract().response();
    }


    /**
     * ✅ PUT - Fully update an existing user by ID
     * @param id User ID to update
     * @param name New name
     * @param job New job title
     * @return Response object
     */
    public static Response updateUser(int id, String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);
        System.out.println("\u267B\uFE0F Sending PUT request to /api/users/" + id + " with body:\n" + body);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ✅ PATCH - Partially update user by ID (e.g., only the job)
     * @param id User ID
     * @param job New job value
     * @return Response
     */
    public static Response patchUser(int id, String job) {
        String body = String.format("{\"job\":\"%s\"}", job);
        System.out.println("\uD83D\uDD27 Sending PATCH request to /api/users/" + id + " with body:\n" + body);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ✅ DELETE a user by ID
     * @param id The user ID
     * @return Response with 204 status if deleted successfully
     */
    public static Response deleteUser(int id) {
        System.out.println("\uD83D\uDDD1️ Sending DELETE request to /api/users/" + id);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ❌ GET non-existent user to simulate 404 Not Found
     * @param id Invalid ID
     * @return 404 Response
     */
    public static Response getInvalidUser(int id) {
        System.out.println("❗ Sending GET request to /api/users/" + id + " (expecting 404)");
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ❌ Send malformed JSON to simulate 400 Bad Request
     * @return 400 Response
     */
    public static Response sendBadRequest() {
        System.out.println("❗ Sending invalid POST body to /api/users");
        return given()
                .contentType(ContentType.JSON)
                .body("{invalidJson}")
                .when()
                .post("/api/users")
                .then()
                .extract().response();
    }

    /**
     * ❌ Trigger server error by calling a broken endpoint (simulate 500)
     * @return 500 Response if backend handles it that way
     */
    public static Response triggerServerError() {
        System.out.println("🔥 Sending request to trigger 500 error (non-existent endpoint)");
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/trigger-500-error")
                .then()
                .extract().response();
    }

    /**
     * 🕒 Measure response time for a GET request to evaluate performance
     * @param id User ID
     * @return Response time in milliseconds
     */
    public static long getResponseTime(int id) {
        System.out.println("⏱️ Measuring response time for GET /api/users/" + id);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().time();
    }

    /**
     * 🔐 Use Bearer Token for authenticated GET request
     * @param token Bearer token
     * @param id User ID
     * @return Authenticated response
     */
    public static Response getUserWithBearerToken(String token, int id) {
        System.out.println("\uD83D\uDEA1️ Sending GET with Bearer token to /api/users/" + id);
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * 🔐 Use Basic Auth for authenticated GET request
     * @param username Username
     * @param password Password
     * @param id User ID
     * @return Authenticated response
     */
    public static Response getUserWithBasicAuth(String username, String password, int id) {
        System.out.println("\uD83D\uDD10 Sending GET with Basic Auth to /api/users/" + id);
        return given()
                .auth().preemptive().basic(username, password)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ⏳ Simulate delayed response for timeout testing
     * @param timeoutMs Threshold in ms
     * @return Response
     */
    public static Response simulateTimeout(int timeoutMs) {
        System.out.println("🐢 Simulating timeout with /api/slow endpoint");
        return given()
                .config(config().httpClient(httpClientConfig().setParam("http.socket.timeout", timeoutMs)))
                .contentType(ContentType.JSON)
                .when()
                .get("/api/slow")
                .then()
                .extract().response();
    }

    /**
     * 📄 Send POST using external JSON file as body
     * @param filePath File path to JSON
     * @return Response
     */
    public static Response postUserFromFile(String filePath) {
        System.out.println("\uD83D\uDCC2 Sending POST request with JSON from file: " + filePath);
        File file = new File(filePath);
        return given()
                .contentType(ContentType.JSON)
                .body(file)
                .when()
                .post("/api/users")
                .then()
                .extract().response();
    }

    /**
     * 📤 Upload file using multipart/form-data
     * @param filePath File path
     * @return Response
     */
    public static Response uploadFile(String filePath) {
        System.out.println("\uD83D\uDCE4 Uploading file: " + filePath);
        File file = new File(filePath);
        return given()
                .multiPart("file", file)
                .when()
                .post("/api/upload")
                .then()
                .extract().response();
    }

    /**
     * 🚫 Sends a GET request to fetch a user **without** providing a Bearer token.
     * This method is used to test unauthorized access or token absence handling.
     *
     * @param userId the ID of the user to retrieve
     * @return the API response without any Authorization header
     */
    public static Response getUserWithoutToken(int userId) {
        System.out.println("🚫 Sending request without Bearer token for user ID: " + userId);
        return given()
                .when()
                .get("/api/users/" + userId)
                .then()
                .extract().response();
    }

}
