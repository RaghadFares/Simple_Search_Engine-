import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class SimpleExcelReader {

    // Method to read the dataset.csv and return document data
    public static String[][] readDataset(String filePath) {
        String[][] dataset = new String[100][2]; // Assume max 100 documents, change as needed
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2); // Split by the first comma
                if (parts.length >= 2) {
                    dataset[index][0] = parts[0].trim(); // Document ID
                    dataset[index][1] = parts[1].trim(); // Document content
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return dataset;
    }

    // Method to read the stop words file and return them as a string array
    public static String[] readStopWords(String filePath) {
        String[] stopWords = new String[100]; // Assume max 100 stop words, change as needed
        int index = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords[index] = line.trim().toLowerCase(); // Store stop words in lowercase
                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }

        return stopWords;
    }
}
