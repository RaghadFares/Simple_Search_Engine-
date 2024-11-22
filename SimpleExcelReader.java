import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SimpleExcelReader {
    // Default file paths
    private static final String DEFAULT_DATASET_FILE = "dataset.csv";
    private static final String DEFAULT_STOPWORDS_FILE = "stop.txt";

    // Method to read the dataset.csv
    public static String[][] readDataset() {
        return readDataset(DEFAULT_DATASET_FILE);
    }

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
                        dataset[index][0] = parts[0].trim(); // Document ID
                        dataset[index][1] = parts[1].trim(); // Content
                        index++;
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

    // Method to read the stop.txt
    public static String[] readStopWords() {
        return readStopWords(DEFAULT_STOPWORDS_FILE);
    }

    public static String[] readStopWords(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            int lines = 0;

            // Count the number of stop words, skipping empty lines
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    lines++;  // Increment only for valid stop words
                }
            }

            String[] stopWords = new String[lines];
            scanner.close();

            scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {  // Only process non-empty lines
                    stopWords[index] = line.toLowerCase(); // Store stop words in lowercase
                    index++;
                }
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
