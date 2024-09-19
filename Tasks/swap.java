public class swap {
    private static void swap(int x, int y){
        System.out.println("swap: " + x + "    " + y);
        System.out.println("swapping values");
        int temp = x;
        x = y;
        y = temp;
        System.out.println("swap: " + x + "    " + y);
    }
    public static void main(String[] args) {
        int n1 = 50;
        int n2 = 10;

        System.out.println();
        System.out.println("main: " + n1 + "    " + n2);
        swap(n1, n2);
        System.out.println("main: " + n1 + "    " + n2);
        System.out.println();
    }
}