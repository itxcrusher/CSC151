package student;

/**
 * Represents the left parenthesis "(" in the infix to postfix conversion.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. Left parentheses do not directly participate in the precedence comparison.
 */
public class LeftParen implements Token {

    /**
     * Returns the string representation of the LeftParen token.
     *
     * @return the "(" symbol.
     */
    @Override
    public String toString() {
        return "(";
    }

    /**
     * Processes the LeftParen token by pushing it onto the stack.
     *
     * @param s the stack of tokens being used for the conversion process.
     * @return an empty string since no token is appended to the
     * postfix string when encountering a left parenthesis.
     */
    @Override
    public String handle(Stack<Token> s) {
        s.push(this);
        return "";
    }
}
