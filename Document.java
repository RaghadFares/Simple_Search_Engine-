public class Document {
    int id; // Document ID
    // updated version
    LinkedList<String> words;
    static int count = 0;// LinkedList to store words in the document
    // :
    // Constructor to initialize the document with an ID and content
    public Document(int id, String content) {
        this.id = id;
        this.words = new LinkedList<String>(); // Initialize an empty LinkedList for words
        processDocumentContent(content); // Process and populate the LinkedList with words
    }

    // Method to process the document content
    private void processDocumentContent(String content) {

        content = content.replaceAll("\'", " ");

        content = content.replaceAll("-", " ");
        // Convert content to lowercase
        content = content.toLowerCase();

        // Remove punctuation and non-alphanumeric characters
        content = removePunctuation(content);

        // Split the content into words based on whitespace
        String[] wordArray = content.split("\\s+");
        count+= wordArray.length;

        // Add words to the LinkedList
        for (String word : wordArray) {
            words.insert(word);

            // Use insert() to add word to the linked list
        }

    }


    // Method to remove punctuation and non-alphanumeric characters
    private String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", ""); // Remove punctuation and special characters
    }

    // Method to remove stop words from the document
    public void removeStopWords(String[] stopWords) {
        LinkedList<String> filteredWords = new LinkedList<String>();

        // Iterate through the words and remove stop words
        words.findFirst();
        while (words.retrieve() != null) {
            String word = words.retrieve();
            boolean isStopWord = false;

            // Check if the word is a stop word
            for (String stopWord : stopWords) {
                if (word.equals(stopWord)) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {
                filteredWords.insert(word); // Only add non-stop words
            }
            words.findNext();
        }

        // Update the LinkedList with the filtered words (without stop words)
    }

    // Method to display the processed document content
    public void displayDocument() {
        System.out.println("Document ID: " + id);
        System.out.print("Processed Content: ");
        words.display();  // Display the words in the linked list
        System.out.println("-----------------------------");
    }
}
