public class QueryProcessorTest {
    Index index;
    InvertedIndex invertedIndex;

    // Read dataset and stop words
    String[][] dataset;
    String[] stopWords;

    public QueryProcessorTest() {
        index = new Index();
        invertedIndex = new InvertedIndex();

        // Use SimpleExcelReader to load dataset and stop words
        dataset = SimpleExcelReader.readDataset();
        stopWords = SimpleExcelReader.readStopWords();
    }

    public void Load() {
        for (int i = 0; i < dataset.length; i++) {
            String docIdStr = dataset[i][0];
            String content = dataset[i][1];

            if (docIdStr != null && content != null) {
                int docId = Integer.parseInt(docIdStr.trim());
                Document document = new Document(docId, content);
                document.removeStopWords(stopWords); // Remove stop words

                // Add each word in the document to the inverted index
                document.words.findFirst();
                while (document.words.retrieve() != null) {
                    String wordText = document.words.retrieve();
                    invertedIndex.add(wordText, docId);
                    index.addDocument(document); // Add word and document ID to the inverted index
                    document.words.findNext();
                }
            }
        }
    }

    public void PrinDocWithID(LinkedList<Integer> IDs) {
        if (IDs.empty()) {
            System.out.println("There is no document");
            return;
        }

        IDs.findFirst();
        while (!IDs.last()) {
            Document d = index.docWithID(IDs.retrieve());
            if (d != null)
                System.out.println("Document " + d.id);
            IDs.findNext();
        }
        Document d = index.docWithID(IDs.retrieve());
        if (d != null)
            System.out.println("Document " + d.id);
        System.out.println("");
    }

    public static void main(String[] args) {
        QueryProcessorTest test = new QueryProcessorTest();
        test.Load();

        QueryProcessor q = new QueryProcessor(test.invertedIndex);

        // Tests
        LinkedList<Integer> ANDExample = QueryProcessor.BQuery("referees AND accuracy");
        test.PrinDocWithID(ANDExample); // Using the method we made, we can print the IDs only

        LinkedList<Integer> ORExample = QueryProcessor.BQuery("Market OR accuracy");
        test.PrinDocWithID(ORExample);

        LinkedList<Integer> NOTExample = QueryProcessor.NotQ("Market NOT accuracy");
        test.PrinDocWithID(NOTExample);

        LinkedList<Integer> MIXExample = QueryProcessor.MixedQ("market OR hopeful AND make");
        test.PrinDocWithID(MIXExample);
    }
}
