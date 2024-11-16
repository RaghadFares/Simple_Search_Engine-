import java.util.Scanner;

public class InvertedIndexBSTTest {
    public static void main(String[] args) {
        // File paths for the dataset and stop words
        String datasetFilePath = "dataset.csv";  // Path to the dataset file
        String stopWordsFilePath = "stop.txt";   // Path to the stop words file

        // Read dataset and stop words using SimpleExcelReader
        String[][] dataset = SimpleExcelReader.readDataset(datasetFilePath);
        String[] stopWords = SimpleExcelReader.readStopWords(stopWordsFilePath);

        // Create an InvertedIndexBST object
        InvertedIndexBST invertedIndex = new InvertedIndexBST();

        // Populate the InvertedIndexBST with documents
        for (int i = 0; i < dataset.length; i++) {
            String docIdStr = dataset[i][0];
            String content = dataset[i][1];

            if (docIdStr != null && content != null) {
                int docId = Integer.parseInt(docIdStr.trim());
                Document document = new Document(docId, content);
                document.removeStopWords(stopWords); // Remove stop words

                // Add each word in the document to the inverted index
                document.words.findFirst();
                while (document.words.retrieve() != null) {
                    String wordText = document.words.retrieve();
                    invertedIndex.add(wordText, docId); // Add word and document ID to the inverted index
                    document.words.findNext();
                }
            }
        }

        // Display the entire inverted index
        System.out.println("Displaying the entire inverted index:");
        invertedIndex.displayInvertedIndex();

        // Prompt the user for a word to search
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to search:");
        String searchWord = scanner.nextLine().trim();

        // Search for the word in the inverted index
        boolean found = invertedIndex.search_word_in(searchWord);

        if (found) {
            System.out.println("The word '" + searchWord + "' was found in the following documents:");
            Word foundWord = invertedIndex.retrieve(searchWord); // Retrieve the Word object for the search word
            foundWord.printDocumentIds(); // Print the document IDs where the word appears
        } else {
            System.out.println("The word '" + searchWord + "' was not found in any document.");
        }

        scanner.close();
    }
}
