public class BSTTest {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();

        // Insert nodes
        System.out.println("Inserting nodes into the tree...");
        tree.insert("apple", 1);
        tree.insert("banana", 2);
        tree.insert("cherry", 3);
        tree.insert("date", 4);
        tree.insert("elderberry", 5);

        // Insert a duplicate key
        boolean inserted = tree.insert("banana", 6);
        System.out.println("Inserted duplicate 'banana': " + inserted);

        // Find a key
        System.out.println("\nSearching for key 'cherry'...");
        boolean found = tree.findKey("cherry");
        if (found) {
            System.out.println("Key 'cherry' found, Data: " + tree.retrieve());
        } else {
            System.out.println("Key 'cherry' not found.");
        }

        // Perform traversals
        System.out.println("\nIn-order Traversal:");
        tree.inOrder();

        System.out.println("\nPre-order Traversal:");
        tree.preOrder();

        System.out.println("\nPost-order Traversal:");
        tree.postOrder();

        // Retrieve root data
        System.out.println("\nRoot data: " + tree.getRoot().getData());
    }
}
