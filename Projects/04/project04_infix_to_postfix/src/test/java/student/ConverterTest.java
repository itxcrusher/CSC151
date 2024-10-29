package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Converter class to ensure correct infix-to-postfix conversion.
 *
 * @author Ali Hamza
 * @version 10/26/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
public class ConverterTest {

    private Converter converter;

    @BeforeEach
    public void setUp() {
        converter = new Converter("proj4_input.txt");
    }

    @Test
    public void testSimpleAddition_shouldConvertToPostfix() {
        String input = "A+B;";
        String expected = "AB+";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to convert simple addition.");
    }

    @Test
    public void testMixedOperators_shouldConvertCorrectly() {
        String input = "A+B*C;";
        String expected = "ABC*+";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to convert mixed operators.");
    }

    @Test
    public void testParentheses_shouldHandleParenthesesCorrectly() {
        String input = "(A+B)*C;";
        String expected = "AB+C*";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to handle parentheses correctly.");
    }

    @Test
    public void testEmptyExpression_shouldReturnEmptyPostfix() {
        String input = ";";
        String expected = "";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to handle empty expression.");
    }

    @Test
    public void testComplexExpression_shouldConvertToPostfix() {
        String input = "(A+B)*(C-D)/E;";
        String expected = "AB+CD-*E/";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to convert complex expression.");
    }

    @Test
    public void testMultipleExpressions_shouldConvertBoth() {
        String input1 = "A+B;";
        String input2 = "(A+B)*C;";
        assertEquals("AB+", converter.convertExpression(input1),
                "Failed for first expression.");
        assertEquals("AB+C*", converter.convertExpression(input2),
                "Failed for second expression.");
    }

    @Test
    public void testSimpleSubtraction_shouldHandleSimpleSubtraction() {
        assertEquals("AB+C-", converter.convertExpression("A+B-C;"),
                "Failed for simple subtraction.");
    }

    @Test
    public void testUnmatchedParentheses_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertExpression("(A+B*(C-D;");
        }, "Unmatched parentheses should throw an error.");
    }

    @Test
    public void testNestedParentheses_shouldHandleCorrectly() {
        assertEquals("AB+CD-*E+FG+/", converter.convertExpression("((A+B)*(C-D)+E)/(F+G);"),
                "Failed to handle nested parentheses.");
    }

    // Operand-focused tests

    @Test
    public void testSingleOperand_shouldReturnSameOperand() {
        String input = "A;";
        String expected = "A";
        assertEquals(expected, converter.convertExpression(input),
                "Failed for single operand A.");
    }

    @Test
    public void testMultipleOperandsWithoutOperators_shouldReturnConcatenatedOperands() {
        String input = "ABC;";
        String expected = "ABC";
        assertEquals(expected, converter.convertExpression(input),
                "Failed for multiple operands without operators.");
    }

    @Test
    public void testOperandsWithOperators_shouldHandleCorrectly() {
        String input = "A+B*C-D;";
        String expected = "ABC*+D-";
        assertEquals(expected, converter.convertExpression(input),
                "Failed to handle operands with mixed operators.");
    }
}
