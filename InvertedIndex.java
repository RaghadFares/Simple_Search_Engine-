public class InvertedIndex {
    LinkedList<Word> words; // Linked list to store words and their document IDs

    public InvertedIndex() {
        words = new LinkedList<>(); // Initialize the linked list
    }

    // Add a word and document ID to the inverted index
    public void add(String text, int docId) {
        if (search_word_in(text)) { // If the word exists
            Word existingWord = words.retrieve(); // Retrieve the current word
            existingWord.addDocumentId(docId);    // Add the document ID
        } else { // If the word does not exist
            Word newWord = new Word(text);
            newWord.addDocumentId(docId); // Add the document ID
            words.insert(newWord); // Insert the new word into the linked list
        }
    }

    // Search for a word in the inverted index
    public boolean search_word_in(String w) {
        words.findFirst(); // Start at the first node
        while (words.retrieve() != null) { // Iterate through the list
            if (words.retrieve().getWordText().equalsIgnoreCase(w)) {
                return true; // Found the word
            }
            words.findNext(); // Move to the next node
        }
        return false; // Word not found
    }

    // Retrieve the current word object
    public Word retrieve() {
        return words.retrieve(); // Retrieve the current node's data
    }

    // Display all words and their document IDs
    public void displayInvertedIndex() {
        words.findFirst(); // Start at the first node
        while (words.retrieve() != null) { // Iterate through the list
            Word currentWord = words.retrieve();
            System.out.print("Word: " + currentWord.getWordText() + " -> Document IDs: ");
            currentWord.printDocumentIds();
            words.findNext(); // Move to the next node
        }
    }
}
