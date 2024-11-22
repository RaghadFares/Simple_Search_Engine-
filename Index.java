public class Index {
    LinkedList<Document> allDocuments; // LinkedList to store all documents

// the updated version :)

    // Constructor to initialize the Index
    public Index() {
        this.allDocuments = new LinkedList<>(); // Initialize the LinkedList of documents
    }

    // Method to add a document to the index
    public void addDocument(Document document) {
        allDocuments.insert(document); // Add the document to the LinkedList
    }

    public Document docWithID(int ID){
        if(allDocuments.empty()){
            System.out.println("this document does not exist");
            return null;
        }
        allDocuments.findFirst();
        while (!allDocuments.last()){
            if(allDocuments.retrieve().id==ID)
                return allDocuments.retrieve();
            allDocuments.findNext();;
        }
        if(allDocuments.retrieve().id==ID)
            return allDocuments.retrieve();
        return null;
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

    public LinkedList<Integer> DocWithWord(String word){
        LinkedList<Integer> Ans = new LinkedList<>();
        if(allDocuments.empty()) return Ans;

        allDocuments.findFirst();
        while (!allDocuments.last()){
            if(allDocuments.retrieve().words.exist(word.toLowerCase().trim()));
            Ans.insert(allDocuments.retrieve().id);
            allDocuments.findNext();
        }
        if(allDocuments.retrieve().words.exist(word.toLowerCase().trim()));
        Ans.insert(allDocuments.retrieve().id);
        return Ans;
    }
}

