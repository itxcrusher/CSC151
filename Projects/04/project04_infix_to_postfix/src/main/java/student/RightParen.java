package student;

/**
 * Represents the right parenthesis ")" in the infix to postfix conversion.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. Right parentheses cause all operators on the stack up
 * to the corresponding left parenthesis to be popped.
 */
public class RightParen implements Token {

    /**
     * Returns the string representation of the RightParen token.
     *
     * @return the ")" symbol.
     */
    @Override
    public String toString() {
        return ")";
    }

    /**
     * Processes the RightParen token by popping operators
     * off the stack until a left parenthesis is encountered.
     *
     * @param s the stack of tokens being used for the conversion process.
     * @return the part of the postfix expression that should be appended
     * (operators up to the left parenthesis).
     */
    @Override
    public String handle(Stack<Token> s) {
        StringBuilder result = new StringBuilder();

        while (!s.isEmpty() && !(s.peek() instanceof LeftParen)) {
            result.append(s.pop());
        }

        if (!s.isEmpty()) {
            s.pop();
        }

        return result.toString();
    }
}
