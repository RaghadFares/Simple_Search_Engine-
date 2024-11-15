import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleExcelReader {

    public static void load(String fileName) {
        BufferedReader reader = null;

        try {
            // Open the file for reading
            reader = new BufferedReader(new FileReader(fileName));
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    System.out.println("Empty line found, skipping...");
                    continue;
                }

                // Split the line by commas
                String[] values = line.split(",");

                // Print values with tab as a delimiter
                for (String value : values) {
                    System.out.print(value.trim() + "\t");
                }
                System.out.println(); // Move to the next line after each row
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } finally {
            // Close the reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        // Provide the path to your CSV file
        String filePath = "dataset.csv"; // File name is "dataset.csv"
        load(filePath); // Call the load method with the file path
    }
}
