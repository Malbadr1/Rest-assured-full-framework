package utils;

/**
 * 💡 Utility class for printing formatted logs with emoji support.
 * Use this to print clean test output with consistent formatting.
 */
public class LoggerUtil {

    public static void printSection(String title, String message) {
        String line = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";
        System.out.println("\n" + line);
        System.out.println("📌 " + title.toUpperCase());
        System.out.println("🔸 " + message);
        System.out.println(line + "\n");
    }

    public static void printInfo(String message) {
        System.out.println("ℹ️ " + message);
    }

    public static void printSuccess(String message) {
        System.out.println("\n✅ SUCCESS: " + message + "\n");
    }

    public static void printError(String message) {
        System.out.println("\n❌ ERROR: " + message + "\n");
    }

    public static void printRequest(String method, String endpoint) {
        System.out.printf("📤 Request → Method: %-6s | Endpoint: %s%n", method.toUpperCase(), endpoint);
    }

    public static void printResponse(int code, String body) {
        System.out.println("📥 Response Code: " + code);
        System.out.println("📦 Response Body: " + body + "\n");
    }

    public static void printResponseTime(long milliseconds) {
        System.out.println("⏱️ Response Time: " + milliseconds + "ms");
    }

    public static void printDivider() {
        System.out.println("──────────────────────────────\n");
    }
}
