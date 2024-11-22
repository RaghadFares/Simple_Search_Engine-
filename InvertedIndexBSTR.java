public class InvertedIndexBSTR {//this class is only for the Rank class
    BSTR<Word> words;

    public InvertedIndexBSTR(){
        words = new BSTR<Word>();
    }

    public void AddFromInvertedL(InvertedIndex inverted){
        if(inverted.words.empty())
            return;

        inverted.words.findFirst();
        while(!inverted.words.last()){
            words.insert(inverted.words.retrieve().wordText, inverted.words.retrieve());
            inverted.words.findNext();
        }
        words.insert(inverted.words.retrieve().wordText, inverted.words.retrieve());
    }

    public void add(String text, int ID){
        if(!findWord(text)){
            Word w= new Word(text);
            w.documentIds.insert(ID);
            words.insert(text,w);
        }else{
            Word exist = words.retrieve();
            exist.addDocumentId(ID);
        }
    }

    public boolean findWord(String word){
        return words.findKey(word);
    }

    public void PrintInvertedIndex(){
        if(words==null){
            System.out.println("null inverted index");
            return;
        }
        else if(words.empty()){
            System.out.println("empty inverted index");
            return;
        }
    }

    class BSTRNode<T>{
        String key;
        T data;
        BSTRNode<T> left, right;

        public BSTRNode(String key, T data ){
            this.key = key;
            this.data = data;
            left = right = null;
        }

    }

    class BSTR<T>{
        private BSTRNode<T> root, current;

        public BSTR(){
            current = root = null;
        }

        public boolean empty(){
            return root == null;
        }

        public boolean full(){
            return false;
        }

        public T retrieve(){
            return current.data;
        }

        public boolean findKey(String key){
            BSTRNode<T> p = root;
            while (p != null){
                current = p;
                if(key.compareToIgnoreCase(p.key) == 0){
                    return true;
                }
                else if (key.compareToIgnoreCase(p.key) <0){
                    p=p.left;
                }else{
                    p=p.right;
                }
            }
            return false;
        }

        public boolean insert(String key, T val){
            if(root == null){
                current = root = new BSTRNode<T>(key, val);
                return true;
            }

            BSTRNode<T> p = current;
            if(findKey(key)){
                current = p;
                return false;
            }

            BSTRNode<T> tmp = new BSTRNode<T>(key, val);
            if (key.compareToIgnoreCase(current.key) <0){
                current.left = tmp;
            }else{
                current.right = tmp;
            }
            current=tmp;
            return true;
        }

    }
}
