public class CompareToExample {
    public static void main(String[] args) {
        // Defining several integers for comparison
        Integer num1 = 50;
        Integer num2 = 100;
        Integer num3 = 50;
        Integer num4 = 200;
        Integer num5 = 75;
        Integer num6 = 30;

        // Comparing num1 and num2
        System.out.println("Comparing num1 (" + num1 + ") and num2 (" + num2 + "):");
        compare(num1, num2);

        // Comparing num1 and num3 (same value)
        System.out.println("\nComparing num1 (" + num1 + ") and num3 (" + num3 + "):");
        compare(num1, num3);

        // Comparing num2 and num4 (num2 is smaller)
        System.out.println("\nComparing num2 (" + num2 + ") and num4 (" + num4 + "):");
        compare(num2, num4);

        // Comparing num5 and num6 (num5 is larger)
        System.out.println("\nComparing num5 (" + num5 + ") and num6 (" + num6 + "):");
        compare(num5, num6);

        // Comparing num3 and num6 (num3 is larger)
        System.out.println("\nComparing num3 (" + num3 + ") and num6 (" + num6 + "):");
        compare(num3, num6);

        // Comparing num4 and num1 (num4 is greater)
        System.out.println("\nComparing num4 (" + num4 + ") and num1 (" + num1 + "):");
        compare(num4, num1);
    }

    // Method to compare two numbers and print results
    public static void compare(Integer num1, Integer num2) {
        if (num1.compareTo(num2) < 0) {
            System.out.println(num1 + " is less than " + num2);
        } else if (num1.compareTo(num2) > 0) {
            System.out.println(num1 + " is greater than " + num2);
        } else {
            System.out.println(num1 + " is equal to " + num2);
        }
    }
}
