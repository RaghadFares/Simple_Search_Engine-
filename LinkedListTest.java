public class LinkedListTest {
    public static void main(String[] args) {
        // Create a LinkedList of integers
        LinkedList<Integer> list = new LinkedList<>();

        // Test 1: Check if the list is initially empty
        System.out.println("Is the list empty? " + list.empty()); // Expected: true
// ---
        // Test 2: Insert elements into the list
        list.insert(10);
        list.insert(20);
        list.insert(30);
        System.out.print("List after inserting 10, 20, 30: ");
        list.display(); // Expected: 10 20 30

        // Test 3: Retrieve the first element
        list.findFirst();
        System.out.println("First element: " + list.retrieve()); // Expected: 10

        // Test 4: Update the first element
        list.update(15);
        System.out.print("List after updating first element to 15: ");
        list.display(); // Expected: 15 20 30

        // Test 5: Move to the next element and retrieve it
        list.findNext();
        System.out.println("Second element: " + list.retrieve()); // Expected: 20

        // Test 6: Remove the current element (20)
        list.remove();
        System.out.print("List after removing the second element: ");
        list.display(); // Expected: 15 30

        // Test 7: Insert a new element after the current position
        list.insert(25);
        System.out.print("List after inserting 25: ");
        list.display(); // Expected: 15 30 25

        // Test 8: Check if the current element is the last element
        list.findFirst(); // Start from the beginning
        System.out.println("Is the current element the last? " + list.last()); // Expected: false

// Move to the last element and check again
        while (!list.last()) {
            list.findNext();
        }
        System.out.println("Is the current element the last? " + list.last()); // Expected: true

        // Test 9: Remove the last element (25)
        list.remove();
        System.out.print("List after removing the last element: ");
        list.display(); // Expected: 15 30

        // Test 10: Attempt to remove an element when the list is empty
        LinkedList<Integer> emptyList = new LinkedList<>();
        emptyList.remove();
        System.out.println("List after removing from an empty list: ");
        emptyList.display(); // Expected: (no output)

        // Test 11: Insert into an empty list and display
        emptyList.insert(50);
        System.out.print("List after inserting 50 into an empty list: ");
        emptyList.display(); // Expected: 50
    }
}

