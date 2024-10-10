public class Array {
    public static void main(String[] args) {
        // Declare and initialize an array of integers
        int[] numbers = {1, 2, 3, 4, 5};

        // Print all elements of the array
        System.out.print("\nArray elements --> ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        // Access an element of the array
        int thirdElement = numbers[2];
        System.out.println("The third element is: " + thirdElement);

        // Modify an element of the array
        numbers[4] = 10;
        System.out.print("Modified array elements --> ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
