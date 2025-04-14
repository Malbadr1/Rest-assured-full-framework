package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * ✅ This class provides reusable REST API methods for User operations.
 *
 * Handles the following:
 * - ✅ GET user by ID
 * - ✅ POST a new user
 * - ✅ PUT to update user
 * - ✅ DELETE user
 * - ✅ Negative test scenarios (404 Not Found, 400 Bad Request, etc.)
 * - ✅ Simulated server error (500 Internal Server Error)
 * - ✅ Response time retrieval (for performance testing)
 */
public class UserService {

    /**
     * ✅ GET a user by ID (expecting 200 OK)
     */
    public static Response getUser(int id) {
        System.out.println("📤 Sending GET request to /api/users/" + id);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id);

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ✅ POST a new user (expecting 201 Created)
     */
    public static Response createUser(String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);
        System.out.println("📤 Sending POST request to /api/users with body:\n" + body);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/api/users");

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ✅ PUT to update a user (expecting 200 OK)
     */
    public static Response updateUser(int id, String name, String job) {
        String body = String.format("{\"name\":\"%s\", \"job\":\"%s\"}", name, job);
        System.out.println("♻️ Sending PUT request to /api/users/" + id + " with body:\n" + body);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("/api/users/" + id);

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ✅ DELETE a user (expecting 204 No Content)
     */
    public static Response deleteUser(int id) {
        System.out.println("🗑️ Sending DELETE request to /api/users/" + id);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/users/" + id);

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ❌ GET a user that does not exist (expecting 404 Not Found)
     */
    public static Response getInvalidUser(int id) {
        System.out.println("❗ Sending GET request to /api/users/" + id + " (expecting 404)");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id);

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ❌ Send a malformed POST body (simulating 400 Bad Request)
     */
    public static Response sendBadRequest() {
        System.out.println("❗ Sending invalid POST body to /api/users");

        Response response = given()
                .contentType(ContentType.JSON)
                .body("{invalidJson}") // invalid body to trigger 400 if backend enforces validation
                .when()
                .post("/api/users");

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * ❌ Simulate 500 Internal Server Error (using a fake endpoint)
     */
    public static Response triggerServerError() {
        System.out.println("🔥 Sending request to trigger 500 error (non-existent endpoint)");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/trigger-500-error");

        System.out.println("📥 Status Code: " + response.statusCode());
        return response;
    }

    /**
     * 🕒 Get the response time for fetching a user (used in performance testing)
     *
     * @param id The user ID
     * @return Time in milliseconds
     */
    public static long getResponseTime(int id) {
        System.out.println("⏱️ Measuring response time for GET /api/users/" + id);

        long time = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/users/" + id)
                .time(); // milliseconds

        System.out.println("⏱️ Response Time: " + time + " ms");
        return time;
    }
}
