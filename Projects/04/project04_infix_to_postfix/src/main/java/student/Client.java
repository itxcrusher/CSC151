package student;

/**
 * This is the entry point for the Infix to Postfix converter program.
 * It reads expressions from the input file, converts them using the Converter class,
 * and prints the results to the console.
 *
 * @author Ali Hamza
 * @version 10/26/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
public class Client
{
    public static void main(String[] args)
    {
        try {
            Converter converter = new Converter("proj4_input.txt");
            converter.convert();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Make sure the file path is correct and the file is accessible.");
        }
    }
}
