public class Node<T> {
    public T data;
    public Node<T> next;

    // Default constructor
    public Node() {
        data = null;
        next = null;
    }

    // Constructor with a value
    public Node(T val) {
        data = val;
        next = null;
    }

    // Setters/Getters (if needed)
    public void setData(T val) {
        this.data = val;
    }

    public T getData() {
        return data;
    }

    public void setNext(Node<T> nextNode) {
        this.next = nextNode;
    }

    public Node<T> getNext() {
        return next;
    }
}

