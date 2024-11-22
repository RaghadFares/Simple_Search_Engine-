
public class QueryProcessor{

    static InvertedIndex invert;

    public QueryProcessor(InvertedIndex invert){
        this.invert = invert;
    }

    public static LinkedList<Integer> BQuery(String Query){//to evaluate the users input based on what their query contains
        if(!Query.contains("AND")&& !Query.contains("OR"))//if it doesn't contain AND nor OR, then we will return an empty linked list, as there is no query to process
            return AndQ(Query);
        if(Query.contains("AND")&& !Query.contains("OR"))//if it contains AND call method AndQ
            return AndQ(Query);
        else if(!Query.contains("AND")&& Query.contains("OR"))//if it contains OR call method OrQ
            return OrQ(Query);
        else
            return MixedQ(Query);//else return Mixed Query as it will contain both AND & OR
    }
    public static LinkedList<Integer>MixedQ(String Query) {
        LinkedList<Integer> FirstList = new LinkedList<Integer>();
        LinkedList<Integer> SecondList = new LinkedList<Integer>();
        if(Query.length()==0) return FirstList;

        String Words[] = Query.split("OR");

        FirstList = AndQ(Words[0]);//first term
        for(int i = 1 ; i < Words.length ; i++)
        {
            SecondList = AndQ(Words[i]);//intersect between the first 2 terms
            FirstList = OrQ(FirstList , SecondList); //then union between them
        }
        return FirstList;
    }


    public static LinkedList<Integer> AndQ(String Query){

        LinkedList<Integer> FirstIntersection = new LinkedList<Integer>(); //2 linked lists that will keep changing to find the final intersection
        LinkedList<Integer> SecondIntersection = new LinkedList<Integer>();

        String Words[] = Query.split("AND"); //to store all the words that we will find an intersection between

        if(Words.length==0) return FirstIntersection;

        boolean found = invert.search_word_in(Words[0].trim().toLowerCase());//first check if first word is in the documents or no
        if(found){
            FirstIntersection = invert.words.retrieve().documentIds;//if the word exists in the documents, then we will retrieve its linked list that contains all the document ids, that this word is in
        }
        for(int i = 1 ; i < Words.length ; i++)
        {
            found = invert.search_word_in(Words[i].trim().toLowerCase());
            if(found)
                SecondIntersection = invert.words.retrieve().documentIds;//same thing for all the other words that we want to find the intersection between, first we will check if they exist, then retrieve their id linked list

            FirstIntersection = AndQ(FirstIntersection, SecondIntersection);//call this method to bring the intersection between the first word ids linked list and the second word ids linked list
        }//then after that we will assign the first linked list that is called FirstIntersection, as the result of the intersection between the first word and the second, and repeat for the other words in the words array
//so that then we will find the intersection between the (intersection of the first word and second) and the third word, that is if more than 2 word are in the query
        return FirstIntersection;
    }

    public static LinkedList<Integer> AndQ(LinkedList<Integer> FirstIntersection, LinkedList<Integer> SecondIntersection){
        LinkedList<Integer> FullIntersection = new LinkedList<Integer>();
        if(FirstIntersection.empty() ||SecondIntersection.empty()) //if any of the linked lists empty then return the result
            return FullIntersection;

        FirstIntersection.findFirst();

        while(true){//to search for the intersection in the second linked list by going over each element, and seeing if it matches the current element that the first linked list is standing on
            boolean found = foundElement(FullIntersection, FirstIntersection.retrieve());//check if the id already exists in the FullIntersection
            if(!found){
                SecondIntersection.findFirst();
                while(true){
                    if(SecondIntersection.retrieve().equals(FirstIntersection.retrieve()))
                    {
                        FullIntersection.insert(FirstIntersection.retrieve());
                        break;
                    }
                    if(!SecondIntersection.last())
                        SecondIntersection.findNext();
                    else
                        break;
                }
            }
            if(!FirstIntersection.last())
                FirstIntersection.findNext();//after checking all the elements in the second linked list, we move to the next element in the first linked list, and repeat
            else
                break;
        }//end of while loop
        return FullIntersection;//the result of the intersection(but this will be done multiple times if it is more than 2 terms)
    }

    public static boolean foundElement(LinkedList<Integer> FullIntersection, Integer docID){
        if(FullIntersection.empty()) return false;//this method will return true if the result already has this id otherwise it will return false

        FullIntersection.findFirst();

        while(!FullIntersection.last()){
            if(FullIntersection.retrieve().equals(docID))
                return true;

            FullIntersection.findNext();
        }
        if(FullIntersection.retrieve().equals(docID))//for the last one
            return true;

        return false;
    }

    public static LinkedList<Integer> OrQ(String Query){

        LinkedList<Integer> FirstUnion = new LinkedList<Integer>();//2 linked lists that will keep changing to find the final union
        LinkedList<Integer> SecondUnion = new LinkedList<Integer>();

        String Words[] = Query.split("OR");//to store all the words that we will find a union between

        if(Words.length==0) return FirstUnion;

        boolean found = invert.search_word_in(Words[0].trim().toLowerCase());
        if(found){
            FirstUnion = invert.words.retrieve().documentIds;
        }
        for(int i = 1 ; i < Words.length ; i++)
        {
            found = invert.search_word_in(Words[i].trim().toLowerCase());
            if(found)
                SecondUnion = invert.words.retrieve().documentIds;

            FirstUnion = OrQ(FirstUnion, SecondUnion);
        }

        return FirstUnion;
    }

    public static LinkedList<Integer> OrQ(LinkedList<Integer> FirstIntersection, LinkedList<Integer> SecondIntersection){
        LinkedList<Integer> FullUnion = new LinkedList<Integer>();
        if(FirstIntersection.empty() && SecondIntersection.empty()) //if both the linked lists  are empty, then return the result
            return FullUnion;

        FirstIntersection.findFirst();

        while(!FirstIntersection.empty()){//fill the result linked list with all the document ids of the first word as long as it already doesn't exist in the result(no repetition)
            boolean found = foundElement(FullUnion, FirstIntersection.retrieve());
            if(!found)
                FullUnion.insert(FirstIntersection.retrieve());
            if(!FirstIntersection.last())
                FirstIntersection.findNext();
            else
                break;
        }//end while

        while(!SecondIntersection.empty()){//fill the result linked list with all the document ids of the second word as long as it already doesn't exist in the result(no repetition)
            boolean found = foundElement(FullUnion, SecondIntersection.retrieve());
            if(!found)
                FullUnion.insert(SecondIntersection.retrieve());
            if(!SecondIntersection.last())
                SecondIntersection.findNext();
            else
                break;
        }//end while
        return FullUnion;
    }


    public static LinkedList<Integer> NotQ(String Query) {
        LinkedList<Integer> IncludedDocs = new LinkedList<Integer>();
        LinkedList<Integer> ExcludedDocs = new LinkedList<Integer>();

        String[] Words = Query.split("NOT");

        if (Words.length == 0) return IncludedDocs;

        boolean found = invert.search_word_in(Words[0].trim().toLowerCase());
        if (found) {
            IncludedDocs = invert.words.retrieve().documentIds;
        }

        // Assuming the second word is after "NOT"
        if (Words.length > 1) {
            found = invert.search_word_in(Words[1].trim().toLowerCase());
            if (found) {
                ExcludedDocs = invert.words.retrieve().documentIds;
            }
        }

        // Return the documents in IncludedDocs that are not in ExcludedDocs
        return Subtract(IncludedDocs, ExcludedDocs);
    }

    public static LinkedList<Integer> Subtract(LinkedList<Integer> IncludedDocs, LinkedList<Integer> ExcludedDocs) {
        LinkedList<Integer> ResultDocs = new LinkedList<Integer>();

        if (IncludedDocs.empty()) return ResultDocs;

        IncludedDocs.findFirst();
        while (true) {
            Integer currentDoc = IncludedDocs.retrieve();
            if (!foundElementForNot(ExcludedDocs, currentDoc)) {
                ResultDocs.insert(currentDoc);
            }
            if (!IncludedDocs.last()) {
                IncludedDocs.findNext();
            } else {
                break;
            }
        }
        return ResultDocs;
    }

    public static boolean foundElementForNot(LinkedList<Integer> list, Integer docID) {
        if (list.empty()) return false;

        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().equals(docID)) return true;
            list.findNext();
        }
        return list.retrieve().equals(docID);
    }
}
