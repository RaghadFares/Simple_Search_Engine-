import java.util.LinkedList;
// document class
class Document {
    int id; // ID of the document
    LinkedList<String> words; // List of words in the document

    // Constructor to initialize the document with an ID and a LinkedList of words
    Document(int id, LinkedList<String> words) {
        this.id = id;
        this.words = words; // Assign the provided LinkedList to the words field
    }
}
