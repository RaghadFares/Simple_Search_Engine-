public class Document {
    int id; // Document ID
    LinkedList<String> words; // LinkedList to store words in the document

    // Constructor to initialize the document with an ID and content
    public Document(int id, String content) {
        this.id = id;
        this.words = new LinkedList<String>(); // Initialize an empty LinkedList for words
        processDocumentContent(content); // Process and populate the LinkedList with words
    }

    // Method to process the document content
    private void processDocumentContent(String content) {
        // Convert content to lowercase
        content = content.toLowerCase();

        // Remove punctuation and non-alphanumeric characters
        content = removePunctuation(content);

        // Split the content into words based on whitespace
        String[] wordArray = content.split("\\s+");

        // Add words to the LinkedList using insert() method
        for (String word : wordArray) {
            words.insert(word);  // Use insert() to add word to the linked list
        }
    }

    // Method to remove punctuation and non-alphanumeric characters
    private String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", ""); // Remove punctuation and special characters
    }

    // Method to process and remove stop words from the document
    public void removeStopWords(String[] stopWords) {
        LinkedList<String> filteredWords = new LinkedList<String>();

        // Iterate through the words using findFirst() and findNext()
        words.findFirst();  // Start from the first word
        while (words.retrieve() != null) {  // While there is a current word
            String word = words.retrieve();
            boolean isStopWord = false;

            // Check if the word is a stop word
            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            // If it's not a stop word, add it to the filtered list
            if (!isStopWord) {
                filteredWords.insert(word);
            }

            words.findNext();  // Move to the next word
        }

        // Update the LinkedList with the filtered words (without stop words)
        words = filteredWords;
    }

    // Method to display the processed document content
    public void displayDocument() {
        System.out.println("Document ID: " + id);
        System.out.print("Processed Content: ");
        words.findFirst();  // Start from the first word
        while (words.retrieve() != null) {  // While there is a current word
            System.out.print(words.retrieve() + " ");  // Print the current word
            words.findNext();  // Move to the next word
        }
        System.out.println("\n-----------------------------");
    }
}
