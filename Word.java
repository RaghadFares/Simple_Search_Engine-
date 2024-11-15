 public class Word {
    String wordText; // The word itself
    LinkedList<Integer> documentIds; // Custom LinkedList

    // Constructor to initialize the word and the document list
    public Word(String word) {
        this.wordText = word;
        this.documentIds = new LinkedList<>();
    }

    // Check if a document ID is present using manual traversal
    public boolean isDocumentIdPresent(int docId) {
        if (documentIds.empty()) { // Check if the list is empty
            return false;
        }

        documentIds.findFirst(); // Start at the first node
        while (!documentIds.last()) { // Traverse until the last node
            if (documentIds.retrieve().equals(docId)) { // Compare current node data
                return true;
            }
            documentIds.findNext(); // Move to the next node
        }

        // Check the last node
        return documentIds.retrieve().equals(docId);
    }

    // Get the word
    public String getWordText() {
        return wordText;
    }

    // Get the list of document IDs
    public LinkedList<Integer> getDocumentIds() {
        return documentIds;
    }
}
