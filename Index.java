public class Index {
    LinkedList<Document> allDocuments; // LinkedList to store all documents

    // Constructor to initialize the Index
    public Index() {
        this.allDocuments = new LinkedList<>(); // Initialize the LinkedList of documents
    }

    // Method to add a document to the index
    public void addDocument(Document document) {
        allDocuments.insert(document); // Add the document to the LinkedList
    }

    // Method to display all documents in the index
    public void displayAllDocuments() {
        System.out.println("Index contains the following documents:");
        System.out.println("---------------------------------------");

        // Traverse the LinkedList of documents
        allDocuments.findFirst();
        while (allDocuments.retrieve() != null) {
            Document document = allDocuments.retrieve(); // Retrieve the current document
            document.displayDocument(); // Use the Document class's displayDocument method
            allDocuments.findNext();
        }
    }
}

