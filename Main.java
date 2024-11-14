public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();

        // Test 1: Check if the list is empty
        System.out.println("Test 1: empty() - Expected: true, Actual: " + list.empty());

        // Test 2: Insert elements and retrieve first element
        list.insert(10);
        list.findFirst();
        System.out.println("Test 2: insert(10) and findFirst() - Expected: 10, Actual: " + list.retrieve());

        // Test 3: Insert more elements and retrieve current element
        list.insert(20);
        list.insert(30);
        System.out.println("Test 3: insert(20), insert(30) - Expected: 30, Actual: " + list.retrieve());

        // Test 4: Check if the list is empty after inserts
        System.out.println("Test 4: empty() - Expected: false, Actual: " + list.empty());

        // Test 5: Check if current element is the last element
        System.out.println("Test 5: last() - Expected: true, Actual: " + list.last());

        // Test 6: Move to first and next element
        list.findFirst();
        System.out.println("Test 6: findFirst() - Expected: 10, Actual: " + list.retrieve());
        list.findNext();
        System.out.println("Test 6: findNext() - Expected: 20, Actual: " + list.retrieve());

        // Test 7: Update current element (20) to 25
        list.update(25);
        System.out.println("Test 7: update(25) - Expected current: 25, Actual: " + list.retrieve());

        // Test 8: Insert a new element (15) after current element (25)
        list.insert(15);
        System.out.println("Test 8: insert(15) - Expected current: 15, Actual: " + list.retrieve());

        // Test 9: Remove the current element (15) and check the new current
        list.remove();
        System.out.println("Test 9: remove() - Expected current: 25, Actual: " + list.retrieve());

        // Additional test: Move to the next element and check if last
        list.findNext();
        System.out.println("Additional Test: last() - Expected: true, Actual: " + list.last());
    }
}
