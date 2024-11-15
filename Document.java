// the new Document class
class Document {
    int id; // ID of the document
    String content; // Content of the document (as a string)

    // Constructor to initialize the document with an ID and the content
    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    }

    // Method to process the document content by removing stop words and punctuation
    public void processDocument(String[] stopWords) {
        // Convert content to lowercase
        String processedContent = content.toLowerCase();

        // Remove punctuation
        processedContent = removePunctuation(processedContent);

        // Split the content into words using whitespace
        String[] words = processedContent.split("\\s+");

        // Iterate over the words and remove stop words
        String cleanedContent = "";
        for (int i = 0; i < words.length; i++) {
            boolean isStopWord = false;

            // Check if the word is a stop word
            for (int j = 0; j < stopWords.length; j++) {
                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            // If it's not a stop word, add it to the cleaned content
            if (!isStopWord) {
                cleanedContent += words[i] + " ";
            }
        }

        // Print the cleaned document content
        System.out.println("Document ID: " + id);
        System.out.println("Cleaned Content: " + cleanedContent.trim());
        System.out.println("-----------------------------");
    }

    // Helper method to remove punctuation from a string
    private String removePunctuation(String text) {
        return text.replaceAll("[^a-zA-Z0-9\\s]", ""); // Remove punctuation
    }
}
