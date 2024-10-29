package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LinkedList class to ensure correct behavior.
 */
public class LinkedListTest {

    private static final String ELEMENT_A = "A";
    private static final String ELEMENT_B = "B";
    private static final String ELEMENT_C = "C";

    @Test
    public void testAddAtHead() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        assertEquals("{>A}", list.toString());
    }

    @Test
    public void testAddAtTail() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.addAtTail(ELEMENT_B);
        assertEquals("{A, >B}", list.toString());
    }

    @Test
    public void testAddAt() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.addAt(ELEMENT_B, 1);
        assertEquals("{A, >B}", list.toString());

        list.addAt(ELEMENT_C, 1);
        assertEquals("{A, >C, B}", list.toString());
    }

    @Test
    public void testGet() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        assertEquals(ELEMENT_A, list.get(0));
    }

    @Test
    public void testSet() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.set(ELEMENT_B, 0);
        assertEquals("{>B}", list.toString());
    }

    @Test
    public void testRemoveFromHead() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.addAtTail(ELEMENT_B);
        list.remove(0);
        assertEquals("{>B}", list.toString());
    }

    @Test
    public void testRemoveFromTail() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.addAtTail(ELEMENT_B);
        list.remove(1);
        assertEquals("{>A}", list.toString());
    }

    @Test
    public void testRemoveMiddleElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.addAtTail(ELEMENT_B);
        list.addAtTail(ELEMENT_C);
        list.remove(1);
        assertEquals("{A, >C}", list.toString());
    }

    @Test
    public void testRemoveSingleElement() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        list.remove(0);
        assertEquals("{}", list.toString());
    }

    @Test
    public void testAddAtOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAt(ELEMENT_A, 1));
    }

    @Test
    public void testGetOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void testSetOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(ELEMENT_A, 0));
    }

    @Test
    public void testRemoveOutOfBounds() {
        LinkedList<String> list = new LinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    @Test
    public void testSizeMethod() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtHead(ELEMENT_A);
        assertEquals(1, list.size());
    }

    @Test
    public void testToStringMethod() {
        LinkedList<String> list = new LinkedList<>();
        assertEquals("{}", list.toString());
    }

    @Test
    public void testAddAtTailWithCurrentUpdate() {
        LinkedList<String> list = new LinkedList<>();
        list.addAtTail(ELEMENT_A);
        list.addAtTail(ELEMENT_B);
        assertEquals("{A, >B}", list.toString());
    }
}
