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

                // Ensure the line has at least one comma (ID and content)
                if (!line.contains(",")) {
                    System.out.println("Malformed line (missing comma): " + line);
                    continue;
                }

                // Extract the ID and content
                String idPart = line.substring(0, line.indexOf(',')).trim();
                String contentPart = line.substring(line.indexOf(',') + 1).trim();

                try {
                    // Parse the ID to an integer
                    int id = Integer.parseInt(idPart);

                    // Print the ID and content
                    System.out.println("ID: " + id + " | Content: " + contentPart);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format: " + idPart + " in line: " + line);
                }
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
