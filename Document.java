class Document {
    int id; // ID of the document
    String content; // Content of the document as a single string

    // Constructor to initialize the document with an ID and content (text)
    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    }

    // Method to process and display the cleaned words in the document
    public void processWords(String[] stopWords) {
        // Remove punctuation and convert to lowercase
        String cleanedContent = content.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();

        // Split the content into words by whitespace
        String[] words = cleanedContent.split("\\s+");

        // StringBuilder to store the result for each document
        String result = "";

        // Traverse through each word in the words array
        for (String word : words) {
            // If the word is not empty and not a stop word, add it to the result
            if (!word.isEmpty() && !isStopWord(word, stopWords)) {
                result += word + " "; // Add cleaned word to result
            }
        }

        // Print the processed words for this document (in one line)
        System.out.println("Document ID: " + id + " | Processed Words: " + result.trim());
    }

    // Helper method to check if a word is a stop word
    private boolean isStopWord(String word, String[] stopWords) {
        for (String stopWord : stopWords) {
            if (word.equals(stopWord)) {
                return true;
            }
        }
        return false;
    }
}
