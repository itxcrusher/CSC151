package student;

/**
 * Represents an operand in the infix expression, such as A, B, C, etc.
 *
 * @author Ali Hamza
 * @version 10/26/2024
 *
 * Invariants:
 * 1. The `operand` field stores a single character representing the operand.
 */
public class Operand implements Token {
    private final char operand;

    /**
     * Constructor to initialize the operand.
     * @param operand the character representing the operand.
     */
    public Operand(char operand) {
        this.operand = operand;
    }

    /**
     * Handles the operand by directly returning it.
     * Operands don't need to interact with the stack, so this just returns the operand itself.
     *
     * @param s the stack (not used for operands).
     * @return the string representation of the operand.
     */
    @Override
    public String handle(Stack<Token> s) {
        return Character.toString(operand);
    }

    /**
     * Returns the string representation of the operand.
     *
     * @return the operand as a string.
     */
    @Override
    public String toString() {
        return Character.toString(operand);
    }
}
