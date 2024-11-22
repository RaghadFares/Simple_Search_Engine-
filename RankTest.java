public class RankTest {
    // Attributes for Index and InvertedIndex
    Index index;
    InvertedIndex invertedIndex;

    // Dataset and stop words loaded using SimpleExcelReader
    String[][] dataset;
    String[] stopWords;

    // Constructor to initialize and load data
    public RankTest() {
        index = new Index();
        invertedIndex = new InvertedIndex();

        // Load dataset and stop words using SimpleExcelReader with default paths
        dataset = SimpleExcelReader.readDataset();
        stopWords = SimpleExcelReader.readStopWords();

        Load(); // Populate the index and inverted index
    }

    public void Load() {
        // Populate both the index and inverted index
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

    public static void main(String[] args) {
        RankTest Test = new RankTest();

        // Create an instance of InvertedIndexBSTR and populate it
        InvertedIndexBSTR inverted = new InvertedIndexBSTR();
        inverted.AddFromInvertedL(Test.invertedIndex); // Fill the BST inverted index

        System.out.println("Ranking Market"); // First test
        Rank R1 = new Rank(inverted, Test.index, "market"); // Create Rank object and send the query term
        R1.AddSortedList(); // Add it to the full sorted IDs list
        R1.PrinRankDocList(); // Display all the document IDs and their ranks

        System.out.println("Ranking Sports"); // Second test
        Rank R2 = new Rank(inverted, Test.index, "sports");
        R2.AddSortedList();
        R2.PrinRankDocList();
    }
}
