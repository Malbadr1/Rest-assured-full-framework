package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 📦 JsonUtil
 *
 * Utility class to read JSON files from the filesystem
 * and return their content as a String.
 */
public class JsonUtil {

    /**
     * 📄 Reads a JSON file and returns its content as a String.
     *
     * @param filePath path to the JSON file (relative to project root or absolute)
     * @return JSON content as plain String
     */
    public static String readJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("❌ Failed to read JSON file: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
}
