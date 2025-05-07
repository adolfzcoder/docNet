package utils;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class EnvLoader {
    public static HashMap<String, String> loadEnv(String filePath) {
        HashMap<String, String> envVars = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().startsWith("#") && line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    envVars.put(parts[0].trim(), parts[1].trim());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return envVars;
    }
}