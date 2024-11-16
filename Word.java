public class Word {
    String wordText;              // The word itself
    LinkedList<Integer> documentIds; // LinkedList to store document IDs

    // Constructor to initialize the word and document IDs
    public Word(String word) {
        this.wordText = word;
        this.documentIds = new LinkedList<>(); // Initialize an empty LinkedList
    }

    // Method to add a document ID if it's not already in the list
    public void addDocumentId(int docId) {
        if (!isDocumentIdPresent(docId)) {
            documentIds.insert(docId); // Add document ID using your LinkedList's insert method
        }
    }

    // Method to check if a document ID is already in the list
    public boolean isDocumentIdPresent(int docId) {
        documentIds.findFirst();
        while (documentIds.retrieve() != null) {
            if (documentIds.retrieve() == docId) {
                return true; // Document ID found
            }
            documentIds.findNext();
        }
        return false; // Document ID not found
    }

    // Get the word
    public String getWordText() {
        return wordText;
    }

    // Print document IDs where the word appears
    public void printDocumentIds() {
        System.out.print("Word '" + wordText + "' appears in document IDs: ");
        documentIds.findFirst();
        while (documentIds.retrieve() != null) {
            System.out.print(documentIds.retrieve() + " ");
            documentIds.findNext();
        }
        System.out.println();
    }

    // Method to search for the word in a document (without using regex)
    public boolean isWordPresentInDocument(String content) {
        // Manually split the document into words and search for the word
        String[] words = content.split("\\s+"); // Split by spaces (basic word splitting)

        // Loop through each word in the document
        for (String word : words) {
            // Check if the current word matches exactly with wordText (case-insensitive)
            if (word.equalsIgnoreCase(wordText)) {
                return true; // Word found
            }
        }

        return false; // Word not found
    }
}
