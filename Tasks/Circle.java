public class Circle {
    private String color;
    public Circle(){
        color = " ";
    }
    public void setColor(String color){
        this.color = color;
    }
    public String getColor(){
        return this.color;
    }
    public static void swapColor(Circle aBall, Circle anotherBall){
        System.out.println("swap: " + aBall.getColor() + "    " + anotherBall.getColor());
        System.out.println("swapping colors");
        String temp = aBall.getColor();
        aBall.setColor(anotherBall.getColor());
        anotherBall.setColor(temp);
        System.out.println("swap: " + aBall.getColor() + "    " + anotherBall.getColor());
    }
    public static void main(String[] args){
        Circle b1 = new Circle();
        Circle b2 = new Circle();
        b2.setColor("blue");
        System.out.println();
        System.out.println("main: " + b1.getColor() + "    " + b2.getColor());
        swapColor(b1, b2);
        System.out.println("main: " + b1.getColor() + "    " + b2.getColor());
        System.out.println();
    }
}