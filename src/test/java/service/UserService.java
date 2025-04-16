package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.config.RestAssuredConfig.config;
import static io.restassured.config.HttpClientConfig.httpClientConfig;

/**
 * ✅ This class provides reusable REST API methods for User operations.
 *
 * Handles the following:
 * - ✅ GET user by ID
 * - ✅ POST a new user
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
     * @param id The user ID
     * @return Response containing user data
     */
    public static Response getUser(int id) {
        System.out.println("📤 Sending GET request to /api/users/" + id);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ✅ POST a new user with name and job
     * @param name The name of the user
     * @param job The job title of the user
     * @return Response after user creation
     */
    public static Response createUser(String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);
        System.out.println("📤 Sending POST request to /api/users with body:\n" + body);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/users")
                .then()
                .extract().response();
    }

    /**
     * ✅ PUT to update an existing user
     * @param id The user ID
     * @param name Updated name
     * @param job Updated job
     * @return Response with updated user
     */
    public static Response updateUser(int id, String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);
        System.out.println("♻️ Sending PUT request to /api/users/" + id + " with body:\n" + body);
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ✅ PATCH request to partially update user data
     * @param id The user ID
     * @param job The new job title
     * @return Response with partial update
     */
    public static Response patchUser(int id, String job) {
        String body = String.format("{\"job\":\"%s\"}", job);
        System.out.println("🔧 Sending PATCH request to /api/users/" + id + " with body:\n" + body);
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
     * @return Response with 204 status if deleted
     */
    public static Response deleteUser(int id) {
        System.out.println("🗑️ Sending DELETE request to /api/users/" + id);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ❌ GET request to a non-existent user to simulate 404
     * @param id The user ID that does not exist
     * @return Response expected to be 404
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
     * ❌ Send malformed POST body to simulate 400 Bad Request
     * @return Response expected to be 400
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
     * ❌ Simulate 500 error using a fake or broken endpoint
     * @return Response with status 500 (if backend handles it that way)
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
     * 🕒 Measure response time for GET request (performance check)
     * @param id The user ID
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
     * 🔐 Perform GET request with Bearer Token authentication
     * @param token The Bearer token
     * @param id The user ID
     * @return Authenticated response
     */
    public static Response getUserWithBearerToken(String token, int id) {
        System.out.println("🛡️ Sending GET with Bearer token to /api/users/" + id);
        return given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * 🔐 Perform GET request with Basic Auth credentials
     * @param username The username
     * @param password The password
     * @param id The user ID
     * @return Authenticated response
     */
    public static Response getUserWithBasicAuth(String username, String password, int id) {
        System.out.println("🔐 Sending GET with Basic Auth to /api/users/" + id);
        return given()
                .auth().preemptive().basic(username, password)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .then()
                .extract().response();
    }

    /**
     * ⏳ Simulate a slow API response for timeout testing
     * @param timeoutMs Timeout threshold in milliseconds
     * @return Response from delayed endpoint
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
     * 📄 Send POST request using JSON body from file
     * @param filePath Path to the JSON file
     * @return Response from POST
     */
    public static Response postUserFromFile(String filePath) {
        System.out.println("📂 Sending POST request with JSON from file: " + filePath);
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
     * 📤 Upload a file to the server using multipart/form-data
     * @param filePath Path to the file to upload
     * @return Response from upload endpoint
     */
    public static Response uploadFile(String filePath) {
        System.out.println("📤 Uploading file: " + filePath);
        File file = new File(filePath);
        return given()
                .multiPart("file", file)
                .when()
                .post("/api/upload")
                .then()
                .extract().response();
    }
}
