package utils;


import io.restassured.path.json.JsonPath;

import java.util.Map;
/**
 * 💡 LoggerUtil is a utility class that provides consistent, emoji-enhanced,
 * and human-readable logging for REST API test execution.
 *
 * It is mainly used for:
 * - Logging request and response data
 * - Highlighting success and error outcomes
 * - Structuring output into readable sections
 * - Measuring performance
 */

public class LoggerUtil {

    private static final String DIVIDER = "────────────────────────────────────────";
    private static final String SECTION_LINE = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    /**
     * 📌 Print a well-formatted test section header.
     */
    public static void printSection(String title, String message) {
        System.out.println("\n" + SECTION_LINE);
        System.out.println("📌 SECTION: " + title.toUpperCase());
        System.out.println("🔸 " + message);
        System.out.println(SECTION_LINE + "\n");
    }

    /**
     * ℹ️ General information message.
     */
    public static void printInfo(String message) {
        System.out.println("ℹ️ INFO: " + message);
    }

    /**
     * ✅ Success output.
     */
    public static void printSuccess(String message) {
        System.out.println("\n✅ SUCCESS: " + message + "\n");
    }

    /**
     * ❌ Error output.
     */
    public static void printError(String message) {
        System.out.println("\n❌ ERROR: " + message + "\n");
    }

    /**
     * 📤 Request information.
     */
    public static void printRequest(String method, String endpoint) {
        System.out.println("📤 REQUEST →");
        System.out.println("🔹 Method  : " + method.toUpperCase());
        System.out.println("🔹 Endpoint: " + endpoint);
    }

    /**
     * 📦 Print request body.
     */
    public static void printRequestBody(Map<String, Object> body) {
        System.out.println("🔸 Payload :");
        System.out.println("{" );
        body.forEach((key, value) ->
                System.out.println("  \"" + key + "\": \"" + value + "\","));
        System.out.println("}\n");
    }
    // ✅ Logs the request body for user creation
    public static void printRequestBody(String name, String username, String email, String avatar, String status, String role) {
        System.out.println("\n📦 Request Body:");
        System.out.println("{");
        System.out.println("  \"name\"     : \"" + name + "\",");
        System.out.println("  \"username\" : \"" + username + "\",");
        System.out.println("  \"email\"    : \"" + email + "\",");
        System.out.println("  \"avatar\"   : \"" + avatar + "\",");
        System.out.println("  \"status\"   : \"" + status + "\",");
        System.out.println("  \"role\"     : \"" + role + "\"");
        System.out.println("}");
    }

    /**
     * 📥 Response status and body.
     */
    public static void printResponse(int code, String body) {
        System.out.println("\n📥 RESPONSE ←");
        System.out.println("🔸 Status Code : " + code);
        System.out.println("🔸 Body:\n" + body + "\n");
    }

    /**
     * ⏱️ Response time.
     */
    public static void printResponseTime(long milliseconds) {
        System.out.println("⏱️ RESPONSE TIME: " + milliseconds + " ms");
    }

    /**
     * ─ Divider.
     */
    public static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * 🧾 Key-value summary.
     */
    public static void printKeyValue(String key, String value) {
        System.out.printf("📌 %-15s: %s%n", key, value);
    }

    /**
     * 📊 Highlight specific fields in the JSON response.
     */
    public static void printJsonSummary(JsonPath jsonPath, String... fields) {
        System.out.println("📌 SUMMARY\n────────────");
        for (String field : fields) {
            String value = jsonPath.getString(field);
            if (value != null) {
                printKeyValue(capitalize(field), value);
            }
        }
        System.out.println();
    }

    private static String capitalize(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

}

