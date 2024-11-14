
public class LinkedList<T> implements List<T> {
    private Node<T> head;    // Head of the list
    private Node<T> current; // Pointer to the current node

    // Constructor to initialize an empty list
    public LinkedList() {
        head = current = null;
    }

    // Checks if the list is empty
    public boolean empty() {
        return head == null;
    }

    // Checks if the current element is the last element in the list
    public boolean last() {
        return current != null && current.next == null;
    }

    // Always returns false as a linked list cannot be "full"
    public boolean full() {
        return false;
    }

    // Sets the current element to the first element in the list
    public void findFirst() {
        current = head;
    }

    // Moves the current pointer to the next element
    public void findNext() {
        if (current != null) {
            current = current.next;
        }
    }

    // Retrieves the data of the current node
    public T retrieve() {
        return (current != null) ? current.data : null;
    }

    // Updates the current node's data with the given value
    public void update(T val) {
        if (current != null) {
            current.data = val;
        }
    }

    // Inserts a new node after the current node
    public void insert(T val) {
        Node<T> tmp;
        if (empty()) {
            // If list is empty, the new node becomes both head and current
            current = head = new Node<>(val);
        } else {
            // Insert new node after current
            tmp = current.next;
            current.next = new Node<>(val);
            current = current.next;
            current.next = tmp;
        }
    }

    // Removes the current node from the list
    public void remove() {
        if (current == null) return;  // If current is null, do nothing

        // Case 1: Removing the head node
        if (current == head) {
            head = head.next;   // Move head to the next node
            current = head;     // Update current to head (or null if list is empty)
        }
        // Case 2: Removing a non-head node
        else {
            Node<T> tmp = head;
            // Traverse to find the node before the current node
            while (tmp != null && tmp.next != current) {
                tmp = tmp.next;
            }

            // If node before current exists, bypass current node
            if (tmp != null) {
                tmp.next = current.next;  // Skip the current node
                // If current was the last node, move current to the previous node
                current = current.next != null ? current.next : tmp;
            }
        }
    }
}