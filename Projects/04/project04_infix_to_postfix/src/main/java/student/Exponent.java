package student;

/**
 * Represents the Exponent operator (^) in the infix to postfix conversion.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. Precedence of the exponent operator is the highest (3).
 */
public class Exponent implements Token {

    /**
     * Returns the string representation of the Exponent token.
     *
     * @return the "^" symbol.
     */
    @Override
    public String toString() {
        return "^";
    }

    /**
     * Processes the Exponent token by handling the precedence and pushing it onto the stack.
     *
     * @param s the stack of tokens being used for the conversion process.
     * @return the part of the postfix expression that should be appended.
     */
    @Override
    public String handle(Stack<Token> s) {
        StringBuilder result = new StringBuilder();

        s.push(this);

        return result.toString();
    }
}
