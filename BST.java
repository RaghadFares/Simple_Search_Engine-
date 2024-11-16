public class BST<T> {
    private BSTNode<T> root, current;

    /** Creates a new instance of BST */
    public BST() {
        root = current = null;
    }

    /** Check if the tree is empty */
    public boolean empty() {
        return root == null;
    }

    /** Retrieve the data of the current node */
    public T retrieve() {
        if (current != null) {
            return current.data;
        }
        return null;  // Return null if current is not set
    }

    /** Search for a key in the BST (case-insensitive) */
    public boolean findKey(String k) {
        BSTNode<T> p = root;
        current = null;  // Reset current to null before starting the search

        if (empty()) return false;  // If the tree is empty, return false

        while (p != null) {
            int comparison = k.compareToIgnoreCase(p.key);
            if (comparison == 0) {
                current = p;  // Set the current node to the found node
                return true;   // Key found
            } else if (comparison < 0) {
                p = p.left;   // Move to the left subtree if key is smaller
            } else {
                p = p.right;  // Move to the right subtree if key is larger
            }
        }
        return false;  // Key not found
    }

    /** Insert a new key into the BST (case-insensitive) */
    public boolean insert(String k, T val) {
        // Check if the key already exists
        if (findKey(k)) {
            return false; // Key already exists in the tree
        }

        // Create a new node
        BSTNode<T> p = new BSTNode<T>(k, val);

        // If the tree is empty, the new node becomes the root
        if (empty()) {
            root = current = p;
            return true;
        } else {
            // Otherwise, find the correct position to insert the node, starting from the root
            BSTNode<T> parent = null;
            BSTNode<T> currentNode = root;
            while (currentNode != null) {
                parent = currentNode;
                int comparison = k.compareToIgnoreCase(currentNode.key);
                if (comparison < 0) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }

            // Insert the new node as a left or right child of the parent
            if (k.compareToIgnoreCase(parent.key) < 0) {
                parent.left = p;
            } else {
                parent.right = p;
            }
            current = p;  // Move to the newly inserted node
            return true;
        }
    }

    /** Accessor method to get the root */
    public BSTNode<T> getRoot() {
        return root;
    }

    /** In-order Traversal */
    public void inOrder() {
        if (root == null) {
            System.out.println("Empty tree");
        } else {
            inOrder(root);
        }
    }

    private void inOrder(BSTNode<T> p) {
        if (p != null) {
            inOrder(p.left);  // Visit left child
            System.out.println("Key: " + p.key + " Data: " + p.data);  // Visit node itself
            inOrder(p.right);  // Visit right child
        }
    }

    /** Pre-order Traversal */
    public void preOrder() {
        if (root == null) {
            System.out.println("Empty tree");
        } else {
            preOrder(root);
        }
    }

    private void preOrder(BSTNode<T> p) {
        if (p != null) {
            System.out.println("Key: " + p.key + " Data: " + p.data);  // Visit node itself
            preOrder(p.left);  // Visit left child
            preOrder(p.right); // Visit right child
        }
    }

    /** Post-order Traversal */
    public void postOrder() {
        if (root == null) {
            System.out.println("Empty tree");
        } else {
            postOrder(root);
        }
    }

    private void postOrder(BSTNode<T> p) {
        if (p != null) {
            postOrder(p.left);  // Visit left child
            postOrder(p.right); // Visit right child
            System.out.println("Key: " + p.key + " Data: " + p.data);  // Visit node itself
        }
    }

    // Nested BSTNode class for storing data and tree structure
    public static class BSTNode<T> {
        private String key;
        private T data;
        private BSTNode<T> left, right;

        /** Creates a new instance of BSTNode */
        public BSTNode(String k, T val) {
            this.key = k;
            this.data = val;
            this.left = this.right = null;
        }

        // Getter and Setter methods for accessing and modifying fields
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

        public void setKey(String key) {
            this.key = key;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setLeft(BSTNode<T> left) {
            this.left = left;
        }

        public void setRight(BSTNode<T> right) {
            this.right = right;
        }
    }
}
