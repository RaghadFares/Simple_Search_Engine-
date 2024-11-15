public class Main {
    public static void main(String[] args) {
        // Example stop words
        String[] stopWords = {"is", "a", "the", "and", "to", "in"};

        // Create a document with content
        String content = "This is a test document, and it contains some words!";
        Document document = new Document(1, content);

        // Display the original document content
        document.displayDocument();

        // Remove stop words and display the updated content
        document.removeStopWords(stopWords);
        document.displayDocument();
    }
}
