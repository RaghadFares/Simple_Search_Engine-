import java.util.Scanner;

public class WordSearchTest {
    public static void main(String[] args) {
        // File paths for the dataset and stop words
        String datasetFilePath = "dataset.csv";  // Path to the dataset file
        String stopWordsFilePath = "stop.txt";   // Path to the stop words file

        // Read dataset and stop words
        String[][] dataset = SimpleExcelReader.readDataset(datasetFilePath);
        String[] stopWords = SimpleExcelReader.readStopWords(stopWordsFilePath);

        // Create an array of Document objects
        Document[] documents = new Document[dataset.length];

        // Populate the Document array
        for (int i = 0; i < dataset.length; i++) {
            String docIdStr = dataset[i][0];
            String content = dataset[i][1];

            if (docIdStr != null && content != null) {
                int docId = Integer.parseInt(docIdStr.trim());
                documents[i] = new Document(docId, content);
                documents[i].removeStopWords(stopWords); // Remove stop words
                documents[i].displayDocument();         // Display the processed document
            }
        }

        // Prompt the user for a word to search
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to search:");
        String searchWord = scanner.nextLine().trim();

        // Create a Word object for the word to search
        Word word = new Word(searchWord);

        // Check if the word is present in each document
        for (Document document : documents) {
            if (document != null) {
                // Convert the LinkedList of words back to a single string
                StringBuilder documentContent = new StringBuilder();
                document.words.findFirst();
                while (document.words.retrieve() != null) {
                    documentContent.append(document.words.retrieve()).append(" ");
                    document.words.findNext();
                }

                // Use the Word class to check for the word
                if (word.isWordPresentInDocument(documentContent.toString().trim())) {
                    word.addDocumentId(document.id); // Add document ID if the word is found
                }
            }
        }

        // Print the document IDs where the word appears
        word.printDocumentIds();
        scanner.close();
    }
}
