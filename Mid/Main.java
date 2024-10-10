public class Main {

    // Program structure
    public static void main(String[] args){
        System.out.println("Hello World!");
    }

    // Constructor
    public Main(){}

    // Control Structures
    public boolean isAdult(int age){
        if (age >= 18) {
            System.out.println("Adult");
            return true;
        } else {
            System.out.println("Minor");
            return false;
        }
    }

    // Exception Handling
    public void handleException(){
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero.");
        }
    }

    // Testing
    public void testisAdult(){
        boolean isAdult = isAdult(18);
        if (isAdult){
            System.out.println("Test Pass");
        }else{
            System.out.println("Test Fail");
        }
    }

    // Interface
    public interface Animal { void makeSound(); }

    public class Dog implements Animal{
        public void makeSound(){
            System.out.println("Bark");
        }
    }

    // Generic Class
    public class Vehicle<T>{
        private T sound;
        public void makeSound(){
            System.out.println(sound);
        }
    }
}
