import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleExcelReader {

    // Method to read the dataset.csv and return an array of documents (document ID and content)
    public static String[][] readDataset(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int lines = 0;

            // Count the number of valid lines, skipping empty or invalid lines
            boolean firstLine = true; // Flag to skip the header row
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !firstLine) {
                    lines++; // Increment line count for valid rows
                }
                firstLine = false;
            }

            // Initialize the dataset array
            String[][] dataset = new String[lines][2];
            scanner.close();

            // Read the dataset again, skipping the header row
            scanner = new Scanner(file);
            firstLine = true; // Reset the flag to skip header
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !firstLine) {
                    // Split line by the first comma and process it
                    String[] parts = line.split(",", 2);
                    if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                        try {
                            dataset[index][0] = parts[0].trim(); // Document ID
                            dataset[index][1] = parts[1].trim(); // Content
                            index++;
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid document ID format in line: " + line);
                        }
                    }
                }
                firstLine = false;
            }
            scanner.close();
            return dataset;
        } catch (FileNotFoundException e) {
            System.err.println("Error: Dataset file not found!");
            e.printStackTrace();
            return new String[0][0]; // Return an empty array on error
        }
    }

    // Method to read the stop.txt file and return stop words as an array
    public static String[] readStopWords(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int lines = 0;

            // Count the number of stop words
            while (scanner.hasNextLine()) {
                lines++;
                scanner.nextLine();
            }

            String[] stopWords = new String[lines];
            scanner.close();

            scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()) {
                stopWords[index] = scanner.nextLine().trim().toLowerCase();
                index++;
            }
            scanner.close();
            return stopWords;
        } catch (FileNotFoundException e) {
            System.err.println("Error: Stop words file not found!");
            e.printStackTrace();
            return new String[0]; // Return an empty array on error
        }
    }
}
