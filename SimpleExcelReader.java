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

    // Method to read the stop.txt file and return stop words as an array
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

    // Method to process documents and remove stop words manually (without collections)
    public static void processDocuments(String[][] dataset, String[] stopWords) {
        // Ensure dataset is not empty
        if (dataset.length == 0) {
            System.err.println("No valid documents found in dataset.");
            return;
        }

        // Process each document manually
        for (int i = 0; i < dataset.length; i++) {
            try {
                if (dataset[i][0] != null && !dataset[i][0].isEmpty() && dataset[i][1] != null && !dataset[i][1].isEmpty()) {
                    int docId = Integer.parseInt(dataset[i][0]); // Document ID
                    String content = dataset[i][1]; // Content of the document

                    // Create Document object (Assume Document class is defined elsewhere)
                    Document document = new Document(docId, content);

                    // Remove stop words
                    document.removeStopWords(stopWords);

                    // Display processed document
                    document.displayDocument();
                } else {
                    System.err.println("Skipping invalid document at index " + i);
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid document ID format in line: " + dataset[i][0]);
            }
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        String datasetFilePath = "dataset.csv"; // Path to your CSV file
        String stopWordsFilePath = "stop.txt";   // Path to your stop words file

        // Read the dataset and stop words
        String[][] dataset = readDataset(datasetFilePath);
        String[] stopWords = readStopWords(stopWordsFilePath);

        // Process the documents
        processDocuments(dataset, stopWords);
    }
}
