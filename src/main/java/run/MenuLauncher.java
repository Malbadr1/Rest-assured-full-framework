package run;

import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import utils.LoggerUtil;

import java.util.Scanner;

/**
 * 📋 Interactive CLI Menu for running REST Assured API tests.
 * Automatically returns to the menu after each test run.
 */
public class MenuLauncher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            printMenu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> runTest("tests.GetUserTest");
                case "2" -> runTest("tests.CreateUserTest");
                case "3" -> runTest("tests.UpdateUserTest");
                case "4" -> runTest("tests.DeleteUserTest");
                case "5" -> runTest("tests.BadRequestTest");
                case "6" -> runTest("tests.FailedUserTest");
                case "7" -> runTest("tests.InternalErrorTest");
                case "8" -> runTest("tests.PerformanceTest");
                case "9" -> runTest("tests.SchemaValidationTest");
                case "10" -> runTest("tests.PatchUserTest"); // ✅ PATCH user test
                case "0" -> System.out.println("👋 Exiting... Goodbye!");
                default -> System.out.println("❌ Invalid choice. Please select from 0–10.");
            }

        } while (!choice.equals("0"));
    }

    private static void printMenu() {

        System.out.println("\n──────────────────────────────");
        System.out.println("Check Result === 👆 === To Menu Again === 👇 ");
        System.out.println("\n──────────────────────────────");
        System.out.println("=== 🚀 REST Assured API Test Menu ===");
        System.out.println("1️⃣   Run GET user test");
        System.out.println("2️⃣   Run POST user test");
        System.out.println("3️⃣   Run PUT user test");
        System.out.println("4️⃣   Run DELETE user test");
        System.out.println("5️⃣   Run 400 Bad Request test");
        System.out.println("6️⃣   Run 404 Not Found test");
        System.out.println("7️⃣   Run 500 Internal Error test");
        System.out.println("8️⃣   Run Performance test");
        System.out.println("9️⃣   Run Schema Validation test");
        System.out.println("1️⃣0️⃣ Run PATCH user test");
        System.out.println("0️⃣  Exit");
        System.out.print("👉  Choose an option: ");
    }

    private static void runTest(String className) {
        LoggerUtil.printSection("▶️ Running test class", className);
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(className))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);
    }
}