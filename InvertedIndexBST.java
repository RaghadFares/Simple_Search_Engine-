public class InvertedIndexBST {
    private BST<Word> words;

    public InvertedIndexBST() {
        words = new BST<>();
    }

    // Add a word and document ID to the inverted index
    public void add(String text, int docId) {
        if (search_word_in(text)) {
            Word existingWord = words.retrieve(text); // Retrieve word by its text key
            existingWord.addDocumentId(docId);
        } else {
            Word newWord = new Word(text);
            newWord.addDocumentId(docId);
            words.insert(newWord.getWordText(), newWord);
        }
    }

    // Search for a word in the inverted index using the BST
    public boolean search_word_in(String w) {
        return words.findKey(w);
    }

    // Retrieve the current word object by word text
    public Word retrieve(String text) {
        return words.retrieve(text);
    }

    // Display all words and their document IDs in an ordered array
    public void displayInvertedIndex() {
        if (words.empty()) {
            System.out.println("The inverted index is empty.");
            return;
        }

        String[] orderedWords = words.inOrderToArray();
        for (String wordText : orderedWords) {
            Word word = words.retrieve(wordText); // Retrieve the word by its text
            System.out.print("Word: " + word.getWordText() + " -> Document IDs: ");
            word.printDocumentIds();
        }
    }
}



class SimpleList<T> {
    private T[] elements;
    private int size;

    public SimpleList() {
        elements = (T[]) new Object[10]; // Initial capacity of 10
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    private void resize() {
        T[] newElements = (T[]) new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public T get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        }
        return null;
    }

    public int size() {
        return size;
    }
}

class BST<T> {
    private BSTNode<T> root;

    public BST() {
        root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean findKey(String key) {
        return findKey(root, key);
    }

    private boolean findKey(BSTNode<T> node, String key) {
        if (node == null) {
            return false;
        }
        int comparison = key.compareToIgnoreCase(((Word) node.getData()).getWordText());
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return findKey(node.getLeft(), key);
        } else {
            return findKey(node.getRight(), key);
        }
    }

    public void insert(String key, T data) {
        root = insert(root, key, data);
    }

    private BSTNode<T> insert(BSTNode<T> node, String key, T data) {
        if (node == null) {
            return new BSTNode<>(key, data);
        }
        int comparison = key.compareToIgnoreCase(((Word) node.getData()).getWordText());
        if (comparison < 0) {
            node.setLeft(insert(node.getLeft(), key, data));
        } else if (comparison > 0) {
            node.setRight(insert(node.getRight(), key, data));
        }
        return node;
    }

    public T retrieve(String key) {
        return retrieve(root, key);
    }

    private T retrieve(BSTNode<T> node, String key) {
        if (node == null) {
            return null;
        }
        int comparison = key.compareToIgnoreCase(((Word) node.getData()).getWordText());
        if (comparison == 0) {
            return node.getData();
        } else if (comparison < 0) {
            return retrieve(node.getLeft(), key);
        } else {
            return retrieve(node.getRight(), key);
        }
    }

    public String[] inOrderToArray() {
        SimpleList<String> orderedWords = new SimpleList<>();
        inOrderToArray(root, orderedWords);
        String[] result = new String[orderedWords.size()];
        for (int i = 0; i < orderedWords.size(); i++) {
            result[i] = orderedWords.get(i);
        }
        return result;
    }

    private void inOrderToArray(BSTNode<T> node, SimpleList<String> orderedWords) {
        if (node != null) {
            inOrderToArray(node.getLeft(), orderedWords);
            orderedWords.add(((Word) node.getData()).getWordText());
            inOrderToArray(node.getRight(), orderedWords);
        }
    }
}

class BSTNode<T> {
    private String key;
    private T data;
    private BSTNode<T> left, right;

    public BSTNode(String key, T data) {
        this.key = key;
        this.data = data;
        this.left = this.right = null;
    }

    public String getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}
