package student;

/**
 * Represents the Plus operator (+) in the infix to postfix conversion.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. Precedence of the plus operator is 1 (lowest precedence, except parentheses).
 */
public class Plus implements Token {

    /**
     * Returns the string representation of the Plus token.
     *
     * @return the "+" symbol.
     */
    @Override
    public String toString() {
        return "+";
    }

    /**
     * Processes the Plus token by handling the precedence and pushing it onto the stack.
     *
     * @param s the stack of tokens being used for the conversion process.
     * @return the part of the postfix expression that should be appended.
     */
    @Override
    public String handle(Stack<Token> s) {
        StringBuilder result = new StringBuilder();

        while (!s.isEmpty() && !(s.peek() instanceof LeftParen)) {
            result.append(s.pop());
        }

        s.push(this);
        return result.toString();
    }
}
