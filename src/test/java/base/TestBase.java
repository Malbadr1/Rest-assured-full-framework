package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigReader;

/**
 * Global setup class for all test cases.
 * It initializes the base URI and enables Allure reporting.
 */
public class TestBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.get("base.url");
        RestAssured.filters(new AllureRestAssured());

        System.out.println("\n===============================");
        System.out.println("🔧 Test Environment Initialized");
        System.out.println("🌐 Base URI: " + RestAssured.baseURI);
        System.out.println("===============================\n");
    }
}
