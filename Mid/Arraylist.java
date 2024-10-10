import java.util.ArrayList;

public class Arraylist {
    public static void main(String[] args) {
        // Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();

        // Add elements to the ArrayList
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Print all elements of the ArrayList
        System.out.print("\nArrayList elements --> ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();

        // Access an element of the ArrayList
        String secondFruit = fruits.get(1);
        System.out.println("The second fruit is: " + secondFruit);

        // Modify an element of the ArrayList
        fruits.set(2, "Grapes");
        System.out.print("Modified ArrayList elements --> ");
        for (String fruit : fruits) {
            System.out.print(fruit + " ");
        }
        System.out.println();
    }
}
