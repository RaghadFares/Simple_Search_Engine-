import java.io.FileReader;
import java.io.IOException;

class SimpleExcelReader {

    // Method to read the dataset.csv (using only basic arrays, no collections)
    public static Document[] readDataset(String filePath) {
        // Read the file content manually using basic file reading
        String content = readFile(filePath);
        String[] lines = splitContentByNewlines(content);
        Document[] documents = new Document[lines.length];

        // Read each line, split by commas, and create Document objects
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = splitLineByComma(line); // Split by the first comma
            int docId = Integer.parseInt(parts[0].trim()); // Extract document ID
            String contentText = parts[1].trim(); // Extract content

            // Create a Document object
            documents[i] = new Document(docId, contentText);
        }

        return documents;
    }

    // Method to read the stop.txt file (using only basic arrays, no collections)
    public static String[] readStopWords(String filePath) {
        // Read the file content manually
        String content = readFile(filePath);
        return splitContentByNewlines(content);
    }

    // Helper method to simulate reading a file (only split by newlines, no collections)
    private static String readFile(String filePath) {
        // Simple simulation of file content reading (no actual IO in this case)
        if (filePath.equals("dataset.csv")) {
            return "1, Hello world, this is a test document\n"
                    + "2, This document contains stop words and punctuation!\n"
                    + "3, Another example without stop words.";
        } else if (filePath.equals("stop.txt")) {
            return "is\na\nthe\nand\nto\nin";
        }
        return ""; // Return empty string if no matching file
    }

    // Helper method to split content by newlines (mimicking splitting lines from a file)
    private static String[] splitContentByNewlines(String content) {
        return content.split("\n");
    }

    // Helper method to split a line by comma
    private static String[] splitLineByComma(String line) {
        return line.split(",", 2); // Split only by the first comma
    }
}
