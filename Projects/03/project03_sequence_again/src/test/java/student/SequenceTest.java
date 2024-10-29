package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Sequence class to ensure correct functionality.
 */
public class SequenceTest {
    private Sequence sequence;
    private static final String ELEMENT_A = "A";
    private static final String ELEMENT_B = "B";
    private static final String ELEMENT_C = "C";

    @BeforeEach
    public void setUp() {
        sequence = new Sequence();
    }

    /** Tests for adding elements before the current element. */
    @Test
    public void testAddBefore_AtHead() {
        sequence.addAfter(ELEMENT_B);
        sequence.addBefore(ELEMENT_A);
        assertEquals("{>A, B}", sequence.toString());
    }

    @Test
    public void testAddBefore_AtMiddle() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_C);
        sequence.start();
        sequence.advance();
        sequence.addBefore(ELEMENT_B);
        assertEquals("{A, >B, C}", sequence.toString());
    }

    @Test
    public void testAddBefore_EmptySequence() {
        sequence.addBefore(ELEMENT_A);
        assertEquals("{>A}", sequence.toString());
    }

    /** Tests for adding elements after the current element. */
    @Test
    public void testAddAfter_AtHead() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        assertEquals("{A, >B}", sequence.toString());
    }

    @Test
    public void testAddAfter_AtMiddle() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_C);
        sequence.start();
        sequence.addAfter(ELEMENT_B);
        assertEquals("{A, >B, C}", sequence.toString());
    }

    @Test
    public void testAddAfter_EmptySequence() {
        sequence.addAfter(ELEMENT_A);
        assertEquals("{>A}", sequence.toString());
    }

    /** Tests for removing the current element. */
    @Test
    public void testRemoveCurrent_AtHead() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.start();
        sequence.removeCurrent();
        assertEquals("{>B}", sequence.toString());
    }

    @Test
    public void testRemoveCurrent_AtMiddle() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.addAfter(ELEMENT_C);
        sequence.start();
        sequence.advance();
        sequence.removeCurrent();
        assertEquals("{A, >C}", sequence.toString());
    }

    @Test
    public void testRemoveCurrent_WhenCurrentIsNull() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.start();
        sequence.advance();
        sequence.advance();
        sequence.removeCurrent();
        assertEquals("{A, B}", sequence.toString());
    }

    @Test
    public void testRemoveCurrent_EmptySequence() {
        sequence.removeCurrent();
        assertEquals("{}", sequence.toString());
    }

    /** Tests for advancing the current element. */
    @Test
    public void testAdvance_FromHeadToTail() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.start();
        sequence.advance();
        assertEquals("{A, >B}", sequence.toString());
    }

    @Test
    public void testAdvance_AtTail() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.start();
        sequence.advance();
        sequence.advance();
        assertNull(sequence.getCurrent());
    }

    @Test
    public void testAdvance_NoCurrentElement() {
        sequence.advance();
        assertEquals("{}", sequence.toString());
    }

    /** Tests for clearing the sequence. */
    @Test
    public void testClear() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.clear();
        assertEquals(0, sequence.size());
        assertEquals("{}", sequence.toString());
    }

    /** Tests for cloning the sequence. */
    @Test
    public void testClone_IdenticalCopy() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        Sequence cloned = sequence.clone();
        assertEquals(sequence.toString(), cloned.toString());
    }

    @Test
    public void testClone_IndependentCopy() {
        sequence.addAfter(ELEMENT_A);
        Sequence cloned = sequence.clone();
        cloned.addAfter(ELEMENT_B);
        assertNotEquals(sequence.toString(), cloned.toString());
    }

    /** Tests for the equals method. */
    @Test
    public void testEquals_SameContent() {
        Sequence other = new Sequence();
        sequence.addAfter(ELEMENT_A);
        other.addAfter(ELEMENT_A);
        assertTrue(sequence.equals(other));
    }

    @Test
    public void testEquals_DifferentContent() {
        Sequence other = new Sequence();
        sequence.addAfter(ELEMENT_A);
        other.addAfter(ELEMENT_B);
        assertFalse(sequence.equals(other));
    }

    @Test
    public void testEquals_DifferentCurrentPosition() {
        Sequence other = new Sequence();
        sequence.addAfter(ELEMENT_A);
        other.addAfter(ELEMENT_A);
        other.advance();
        assertFalse(sequence.equals(other));
    }

    /** Tests for adding all elements from another sequence. */
    @Test
    public void testAddAll_NonEmptySequence() {
        Sequence sequence1 = new Sequence();
        sequence1.addAfter(ELEMENT_A);

        Sequence sequence2 = new Sequence();
        sequence2.addAfter(ELEMENT_B);

        sequence1.addAll(sequence2);
        assertEquals("{A, >B}", sequence1.toString());
    }

    @Test
    public void testAddAll_EmptySequence() {
        Sequence other = new Sequence();
        sequence.addAfter(ELEMENT_A);
        sequence.addAll(other);
        assertEquals("{>A}", sequence.toString());
    }

    /** Tests for the start method. */
    @Test
    public void testStart() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        sequence.start();
        assertEquals("{>A, B}", sequence.toString());
    }

    @Test
    public void testStart_EmptySequence() {
        sequence.start();
        assertNull(sequence.getCurrent());
    }

    /** Tests for the size method. */
    @Test
    public void testSize_AfterInsertions() {
        sequence.addAfter(ELEMENT_A);
        sequence.addAfter(ELEMENT_B);
        assertEquals(2, sequence.size());
    }

    @Test
    public void testSize_EmptySequence() {
        assertEquals(0, sequence.size());
    }
}
