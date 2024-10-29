package student;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import java.util.EmptyStackException;

/**
 * Unit tests for the Stack class to ensure correct behavior.
 *
 * @author Ali Hamza
 * @version 10/26/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
@Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
public class StackTest {

    private Stack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack<>();
    }

    @AfterEach
    public void tearDown() {
        stack = null;
    }

    @Test
    public void testConstructor_shouldCreateEmptyStack() {
        assertEquals("{>}", stack.toString(),
                "An empty stack. (> indicates the top of the stack)");
    }

    @Test
    public void testIsEmpty_shouldReturnTrueForEmptyStack() {
        assertTrue(stack.isEmpty(), "Stack should be empty initially.");
    }

    @Test
    public void testPush_shouldAddElementToEmptyStack() {
        stack.push("A");
        assertEquals("{>A}", stack.toString().replaceAll("[ ]+", ""),
                "Pushing A onto an empty stack.");
        assertFalse(stack.isEmpty(), "Stack should not be empty after push.");
    }

    @Test
    public void testPush_shouldAddMultipleElements() {
        stack.push("A");
        stack.push("B");
        assertEquals("{>B,A}", stack.toString().replaceAll("[ ]+", ""),
                "Pushing A then B onto an empty stack.");
    }

    @Test
    public void testPop_shouldRemoveAndReturnTopElement() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C", stack.pop(), "Popping the top element (C) from the stack.");
        assertEquals("{>B,A}", stack.toString().replaceAll("[ ]+", ""),
                "Stack after popping C.");
    }

    @Test
    public void testPeek_shouldReturnTopElementWithoutRemoving() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C", stack.peek(), "Peeking the top element (C) from the stack.");
        assertEquals("{>C,B,A}", stack.toString().replaceAll("[ ]+", ""),
                "Stack should remain unchanged after peek.");
    }

    @Test
    public void testSize_shouldReturnCorrectStackSize() {
        stack.push("A");
        assertEquals(1, stack.size(), "Stack size should be 1 after pushing one element.");
        stack.push("B");
        assertEquals(2, stack.size(), "Stack size should be 2 after pushing two elements.");
        stack.pop();
        assertEquals(1, stack.size(), "Stack size should be 1 after popping one element.");
    }

    @Test
    public void testPop_shouldThrowExceptionWhenEmpty() {
        assertThrows(EmptyStackException.class, () -> stack.pop(),
                "Popping from an empty stack should throw EmptyStackException.");
    }

    @Test
    public void testPeek_shouldThrowExceptionWhenEmpty() {
        assertThrows(EmptyStackException.class, () -> stack.peek(),
                "Peeking from an empty stack should throw EmptyStackException.");
    }

    @Test
    public void testClear_shouldEmptyTheStack() {
        stack.push("A");
        stack.push("B");
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements.");
    }

    @Test
    public void testLargeStackOperations() {
        for (int i = 0; i < 1000; i++) {
            stack.push("Element " + i);
        }
        assertEquals(1000, stack.size(), "Stack should contain 1000 elements.");

        for (int i = 999; i >= 0; i--) {
            assertEquals("Element " + i, stack.pop());
        }
        assertTrue(stack.isEmpty(), "Stack should be empty after popping all elements.");
    }
}
