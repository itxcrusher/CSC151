package student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Converts an infix expression to a postfix expression using a stack-based algorithm.
 *
 * @author Ali Hamza
 * @version 10/27/2024
 *
 * Invariants:
 * 1. The `myReader` field reads input expressions from a file.
 * 2. The stack is used to manage operators and parentheses during conversion.
 */
public class ConverterOperand {

    private Scanner myReader;

    /**
     * Non-default constructor; Gradescope needs this to run tests
     *
     * @param infile path to the input file
     */
    public ConverterOperand(String infile) {
        try {
            myReader = new Scanner(new File(infile));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    /**
     * Reads and converts all infix expressions from the input file to postfix.
     */
    public void convert() {
        while (myReader.hasNext()) {
            String nextExpression = myReader.next();
            String postfix = convertExpression(nextExpression);
            System.out.println(nextExpression + " --> " + postfix);
        }
    }

    /**
     * Converts a single infix expression to postfix.
     *
     * @param expression the infix expression to convert
     * @return the postfix expression
     */
    public String convertExpression(String expression) {
        Stack<Token> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();

        int leftParenCount = 0;
        int rightParenCount = 0;

        for (char c : expression.toCharArray()) {
            if (c == '(') {
                leftParenCount++;
            } else if (c == ')') {
                rightParenCount++;
                if (rightParenCount > leftParenCount) {
                    throw new IllegalArgumentException("Unmatched parentheses");
                }
            }

            Token token = createToken(c);
            if (token != null) {
                postfix.append(token.handle(stack));
            }
        }

        if (leftParenCount != rightParenCount) {
            throw new IllegalArgumentException("Unmatched parentheses");
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop().toString());
        }

        return postfix.toString();
    }

    /**
     * Creates the appropriate Token object for the given character.
     *
     * @param c the character to be converted into a Token
     * @return a Token object (either an operand, operator, or parenthesis)
     */
    private Token createToken(char c) {
        switch (c) {
            case '+':
                return new Plus();
            case '-':
                return new Minus();
            case '*':
                return new Multiply();
            case '/':
                return new Divide();
            case '^':
                return new Exponent();
            case '(':
                return new LeftParen();
            case ')':
                return new RightParen();
            case ';':
                return new Semicolon();
            default:
                return new Operand(c);
        }
    }
}
