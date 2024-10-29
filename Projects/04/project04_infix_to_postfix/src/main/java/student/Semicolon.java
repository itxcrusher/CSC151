package student;

/**
 * Represents the semicolon (;) in the infix to postfix conversion,
 * which indicates the end of an expression.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. The semicolon should cause all remaining operators on the stack to be popped.
 */
public class Semicolon implements Token {

    /**
     * Returns the string representation of the Semicolon token.
     *
     * @return the ";" symbol.
     */
    @Override
    public String toString() {
        return ";";
    }

    /**
     * Processes the Semicolon token by popping all remaining operators from the stack.
     *
     * @param s the stack of tokens being used for the conversion process.
     * @return the part of the postfix expression that should be appended
     * (all remaining operators)
     */
    @Override
    public String handle(Stack<Token> s) {
        StringBuilder result = new StringBuilder();

        while (!s.isEmpty()) {
            result.append(s.pop());
        }

        return result.toString();
    }
}
