public class Word {
    String wordText;      // The word itself
    int[] documentIds;    // Array to store document IDs
    int size;             // Number of document IDs currently stored

    // Constructor to initialize the word and document IDs
    public Word(String word) {
        this.wordText = word;
        this.documentIds = new int[10]; // Initial size of 10, can be resized if necessary
        this.size = 0;
    }

    // Method to add a document ID if it's not already in the list
    public void addDocumentId(int docId) {
        if (!isDocumentIdPresent(docId)) {
            if (size == documentIds.length) {
                resize(); // Resize the array if full
            }
            documentIds[size++] = docId; // Add document ID to the list
        }
    }

    // Method to check if a document ID is already in the list
    public boolean isDocumentIdPresent(int docId) {
        for (int i = 0; i < size; i++) {
            if (documentIds[i] == docId) {
                return true; // Document ID found
            }
        }
        return false; // Document ID not found
    }

    // Resize the document IDs array
    private void resize() {
        int newSize = documentIds.length * 2; // Double the array size
        int[] newArray = new int[newSize];
        System.arraycopy(documentIds, 0, newArray, 0, documentIds.length);
        documentIds = newArray; // Assign the resized array
    }

    // Get the word
    public String getWordText() {
        return wordText;
    }

    // Print document IDs where the word appears
    public void printDocumentIds() {
        System.out.print("Word '" + wordText + "' appears in document IDs: ");
        for (int i = 0; i < size; i++) {
            System.out.print(documentIds[i] + " ");
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
            // Also ensure the word boundaries are considered (using space, punctuation, etc.)
            if (word.equalsIgnoreCase(wordText)) {
                return true; // Word found alone
            }
        }

        return false; // Word not found
    }
}
