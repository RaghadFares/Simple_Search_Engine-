class DocumentRank {//a class to make objects from, such that these objects contain an id and rank, to later than hold in a sorted list, based on their attributes, the id and rank
    int id;
    int rank;
    public DocumentRank(int id, int rank) {
        this.id = id;
        this.rank = rank;
    }
    public void display(){
        System.out.println( id +"             "+ rank); //display the final result for each id and rank in the list
    }
}

public class Rank{

    static String Query;
    static InvertedIndexBSTR inverted;
    static Index index;
    static LinkedList<Integer> AllDoc; //used to store all the IDS in the query
    static LinkedList<DocumentRank> AllRankDoc; //used to store the final result of the sorted documents

    public Rank(InvertedIndexBSTR inverted, Index index, String Query){
        this.inverted = inverted;
        this.index = index;
        this.Query = Query;
        AllDoc= new LinkedList<Integer>();
        AllRankDoc= new LinkedList<DocumentRank>();
    }

    public static void PrinRankDocList(){
        if(AllRankDoc.empty()){
            System.out.println("No docs");
            return;
        }
        System.out.println("Document ID   Rank");//display the header
        AllRankDoc.findFirst();
        while(!AllRankDoc.last()){
            AllRankDoc.retrieve().display();//display each ranked document that contains its id and rank, both sorted
            AllRankDoc.findNext();
        }
        AllRankDoc.retrieve().display();//the last one
    }

    public static Document DocWithID(int ID){
        return index.docWithID(ID);
    }//this method is used to return the whole document once given the id

    public static int WordOccurrenceInDoc(Document Doc, String Word){ //this method is used to count the frequency of the given word in the given document
        int Occ=0;
        LinkedList<String> Words = Doc.words;
        if(Words.empty()) return 0;
        Words.findFirst();
        while(!Words.last())
        {
            if(Words.retrieve().equalsIgnoreCase(Word))
                Occ++; //each time the word occurs in the given document the counter goes up by 1
            Words.findNext();
        }
        if(Words.retrieve().equalsIgnoreCase(Word)) //for the last one, since the condition up doesn't include it
            Occ++;
        return Occ;
    }

    public static int GetDocRank(Document Doc, String Query){ //given a whole query, this method goes around the whole query and counts each occurrence for the words in the whole query
        if(Query.length()==0) return 0;
        String Words[] = Query.split(" "); //split all the terms or words in the query
        int OccSum = 0;
        for(int i = 0; i < Words.length ; i++){
            OccSum+= WordOccurrenceInDoc(Doc, Words[i].trim().toLowerCase()); // the full rank of the query, if there are more than 1 words or term to calculate the occurrence in the query
        }
        return OccSum;
    }

    public static void RankQuery(String Query){//this method brings the linked list for each term, such that for a term the linked list will be retrieved, containing all the document ids that this word appears in
        LinkedList<Integer> A = new LinkedList<Integer>();
        if(Query.length()==0) return;
        String Words[] = Query.split("\\s+");//we can write " " instead, to split each word alone
        boolean flag = false;
        for(int i = 0; i < Words.length ; i++){
            flag = inverted.findWord(Words[i].trim().toLowerCase());
            if(flag) //if the term is found in the documents, then the variable A will hold its linked list of IDs
                A=inverted.words.retrieve().documentIds;
            AddFirstList(A);//then sends this linked list to the method AddFirstList, which adds the linked list of ids into another linked list which is the result linked list of all ids, such that it becomes sorted
        }
    }

    public static void AddFirstList(LinkedList<Integer> A){
        if(A.empty())
            return;
        A.findFirst();
        while (!A.empty()){
            boolean found = FoundR(AllDoc, A.retrieve());//used to make sure that the given id isn't repeated if it already exists
            if(!found){
                AddSortedIDList(A.retrieve());//then we call this method to also sort the document id
            }
            if(!A.last())
                A.findNext();
            else
                break;
        }
    }

    public static boolean FoundR(LinkedList<Integer> Result, Integer ID){
        if(Result.empty()) return false;
        Result.findFirst();
        while(!Result.last()) {
            if (Result.retrieve().equals(ID)) {
                return true;
            }
            Result.findNext();
        }
        if(Result.retrieve().equals(ID))
            return true;
        return false;
    }

    public static void AddSortedIDList(Integer ID){//this method is used to sort the ids, such that when we send an id number that is smaller than the one in the current position,it will be added immediately
        if(AllDoc.empty()){//if its empty insert immediately
            AllDoc.insert(ID);
            return;
        }
        AllDoc.findFirst();
        while (!AllDoc.last()){
            if(ID < AllDoc.retrieve()){//otherwise find a lower id to replace with and re-arrange
                Integer ID1 = AllDoc.retrieve();
                AllDoc.update(ID);
                AllDoc.insert(ID1);
                return;
            }
            else AllDoc.findNext();
        }
        if(ID < AllDoc.retrieve()){//to check the last one
            Integer ID1 = AllDoc.retrieve();
            AllDoc.update(ID);
            AllDoc.insert(ID1);
            return;
        }
        else AllDoc.insert(ID);
    }

    public static void AddSortedList(){
        RankQuery(Query); //first the linked list of all documents will be filled
        if(AllDoc.empty()){
            System.out.println("Empty");
            return;
        }
        AllDoc.findFirst();
        while (!AllDoc.last()){//we fill the list with all the document ids and ranks
            Document Doc = DocWithID(AllDoc.retrieve());
            int Rank = GetDocRank(Doc, Query);
            AddSortedList(new DocumentRank(AllDoc.retrieve(),Rank));//we call this method to sort the ranks, and fill it with a new document containing the id and its rank, to later display
            AllDoc.findNext();
        }
        Document Doc = DocWithID(AllDoc.retrieve());//for the last one
        int Rank = GetDocRank(Doc, Query);
        AddSortedList(new DocumentRank(AllDoc.retrieve(),Rank));
    }

    public static void AddSortedList(DocumentRank DR) {//same as the ranking method for the document ids, this one ranks based on the scores
        if(AllRankDoc.empty()){
            AllRankDoc.insert(DR);
            return;
        }
        AllRankDoc.findFirst();
        while (!AllRankDoc.last()){//find a rank that is smaller to replace the value with the given one
            if(DR.rank > AllRankDoc.retrieve().rank){
                DocumentRank DR1 = AllRankDoc.retrieve();
                AllRankDoc.update(DR);
                AllRankDoc.insert(DR1);
                return;
            }
            else AllRankDoc.findNext();
        }
        if(DR.rank > AllRankDoc.retrieve().rank){//to check for the last one
            DocumentRank DR1 = AllRankDoc.retrieve();
            AllRankDoc.update(DR);
            AllRankDoc.insert(DR1);
            return;
        }
        else AllRankDoc.insert(DR);
    }
}