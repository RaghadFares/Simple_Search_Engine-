import java.util.Scanner;

public class MainMenuTest {
    static Index index;
    InvertedIndex invertedIndex;
    InvertedIndexBST invertedIndexBST;
    int tokens;
    static int DocNum = 0;
    // Dataset and stop words loaded using SimpleExcelReader
    String[][] dataset;
    String[] stopWords;

    public MainMenuTest() {
        index = new Index();
        invertedIndex = new InvertedIndex();
        invertedIndexBST = new InvertedIndexBST();
        tokens = 0;

        // Load dataset and stop words using SimpleExcelReader with default paths
        dataset = SimpleExcelReader.readDataset();
        stopWords = SimpleExcelReader.readStopWords();
    }

    public void Load() {
        // Populate the index and inverted index
        for (int i = 0; i < dataset.length; i++) {
            String docIdStr = dataset[i][0];
            String content = dataset[i][1];

            if (docIdStr != null && content != null) {
                int docId = Integer.parseInt(docIdStr.trim());
                Document document = new Document(docId, content);
                document.removeStopWords(stopWords); // Remove stop words
                index.addDocument(document);
                DocNum++;
                // Add each word in the document to the inverted index
                document.words.findFirst();
                while (document.words.retrieve() != null) {
                    String wordText = document.words.retrieve();
                    invertedIndex.add(wordText, docId);
                    invertedIndexBST.add(wordText, docId);
                    document.words.findNext();
                }
            }
        }
    }

    public void PrinDocWithID(LinkedList<Integer> IDs) { // Helper method to display documents
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

    public static void Menu() {
        System.out.println("1 - Retrieve a term using");
        System.out.println("   1. index with lists");
        System.out.println("   2. inverted index with lists");
        System.out.println("   3. inverted index with BST");
        System.out.println("2 - Boolean Retrieval");
        System.out.println("3 - Ranked Retrieval");
        System.out.println("4 - Indexed Documents");
        System.out.println("5 - Indexed Tokens");
        System.out.println("6 - Exit");
        System.out.println("----------------------------------------------------");
    }

    public static void ChoiceCases() { // Main menu handling
        MainMenuTest Test = new MainMenuTest();
        Test.Load();
        Scanner s = new Scanner(System.in);
        int choice = 0;
        do {
            Menu();
            choice = s.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the word to retrieve:");
                    String word = s.next();
                    System.out.println("Choose how to retrieve it:");
                    int SecChoice = 0;
                    do {
                        System.out.println("1 - Index with lists");
                        System.out.println("2 - Inverted index with lists");
                        System.out.println("3 - Inverted index with BST");
                        System.out.println("4 - Exit first option");
                        SecChoice = s.nextInt();
                        switch (SecChoice) {
                            case 1:
                                LinkedList<Integer> output = index.DocWithWord(word);
                                System.out.print("Word " + word);
                                System.out.print(" --> ");
                                output.display();
                                System.out.println();
                                break;

                            case 2:
                                boolean found = Test.invertedIndex.search_word_in(word);
                                if (found)
                                    Test.invertedIndex.words.retrieve().display();
                                else
                                    System.out.println("Word does not exist");
                                break;

                            case 3:
                                boolean found2 = Test.invertedIndexBST.search_word_in(word);
                                if (found2)
                                    Test.invertedIndex.words.retrieve().display();
                                else
                                    System.out.println("Word does not exist");
                                break;

                            case 4:
                                System.out.println("Number of Documents: "+DocNum);
                                index.displayAllDocuments();
                                break;

                            default:
                                System.out.println("Choice should be one of the given numbers");
                                break;
                        }
                    } while (SecChoice != 4);
                    break;

                case 2:
                    s.nextLine(); // Consume newline
                    System.out.println("Enter query to retrieve:");
                    String Query = s.nextLine();
                    Query = Query.toLowerCase();
                    Query = Query.replaceAll(" and ", " AND ");
                    Query = Query.replaceAll(" or ", " OR ");
                    QueryProcessor Q = new QueryProcessor(Test.invertedIndex);
                    System.out.println("Result for \"" + Query + "\" ");
                    LinkedList<Integer> output2 = QueryProcessor.BQuery(Query);
                    Test.PrinDocWithID(output2);
                    break;

                case 3:
                    s.nextLine(); // Consume newline
                    System.out.println("Enter query to retrieve:");
                    String RQuery = s.nextLine();
                    RQuery = RQuery.toLowerCase();
                    InvertedIndexBSTR invertedBSTR = new InvertedIndexBSTR();
                    invertedBSTR.AddFromInvertedL(Test.invertedIndex);
                    Rank R1 = new Rank(invertedBSTR, index, RQuery);
                    R1.AddSortedList();
                    R1.PrinRankDocList();
                    break;

                case 4:
                    index.displayAllDocuments();
                    break;

                case 5:
                    System.out.println("Number of tokens: " + Document.count);
                    break;

                case 6:
                    System.out.println("Closing the menu");
                    break;

                default:
                    System.out.println("Choice should be one of the given numbers");
                    break;
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        ChoiceCases();
    }
}
