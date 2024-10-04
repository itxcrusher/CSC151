package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SequenceTest {
    private Sequence sequence;

    @BeforeEach
    public void setUp() {
        sequence = new Sequence();
    }

    // add before tests
    @Test
    public void testAddBeforeNormal() {
        sequence.addAfter("B");
        sequence.addBefore("A");
        assertEquals("{>A, B} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAddBeforeWhenEmpty() {
        sequence.addBefore("A");
        assertEquals("{>A} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAddBeforeCapacityExpansion() {
        Sequence smallSequence = new Sequence(2);
        smallSequence.addAfter("A");
        smallSequence.addAfter("B");
        smallSequence.addBefore("C");
        assertEquals("{A, >C, B} (capacity = 5)", smallSequence.toString());
    }

    @Test
    public void testAddBeforeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> sequence.addBefore(null));
    }

    // add after tests
    @Test
    public void testAddAfterNormal() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        assertEquals("{A, >B} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAddAfterWhenEmpty() {
        sequence.addAfter("A");
        assertEquals("{>A} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAddAfterCapacityExpansion() {
        Sequence smallSequence = new Sequence(2);
        smallSequence.addAfter("A");
        smallSequence.addAfter("B");
        smallSequence.addAfter("C");
        assertEquals("{A, B, >C} (capacity = 5)", smallSequence.toString());
    }

    @Test
    public void testAddAfterWithNull() {
        assertThrows(IllegalArgumentException.class, () -> sequence.addAfter(null));
    }

    // remove current tests
    @Test
    public void testRemoveCurrentNormal() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.removeCurrent();
        assertEquals("{>A} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testRemoveCurrentWhenEmpty() {
        sequence.removeCurrent(); // Should not throw any exception
        assertEquals("{} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testRemoveCurrentAtEnd() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.removeCurrent();
        assertEquals("{>A} (capacity = 10)", sequence.toString());
    }

    // ensure capacity tests
    @Test
    public void testEnsureCapacityIncrease() {
        sequence.ensureCapacity(20);
        assertEquals(20, sequence.getCapacity());
    }

    @Test
    public void testEnsureCapacityNoChange() {
        sequence.ensureCapacity(5);
        assertEquals(10, sequence.getCapacity());
    }

    // advance tests
    @Test
    public void testAdvanceNormal() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.start();
        sequence.advance();
        assertEquals("{A, >B} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAdvanceWhenNoCurrent() {
        sequence.advance();
        assertEquals("{} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAdvancePastEnd() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.start();
        sequence.advance();
        sequence.advance();
        assertNull(sequence.getCurrent());
    }

    // size tests
    @Test
    public void testInitialSize() {
        assertEquals(0, sequence.size());
    }

    @Test
    public void testSizeAfterInsertions() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        assertEquals(2, sequence.size());
    }

    // start tests
    @Test
    public void testStartNormal() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.start();
        assertEquals("{>A, B} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testStartWhenEmpty() {
        sequence.start();
        assertNull(sequence.getCurrent());
    }

    // trim to size tests
    @Test
    public void testTrimToSize() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.trimToSize();
        assertEquals(2, sequence.size());
        assertEquals(2, sequence.getCapacity());
    }

    @Test
    public void testTrimToSizeNoChange() {
        Sequence smallSequence = new Sequence(2);
        smallSequence.addAfter("A");
        smallSequence.addAfter("B");
        smallSequence.trimToSize();
        assertEquals(2, smallSequence.getCapacity());
    }

    // equals tests
    @Test
    public void testEqualsTrue() {
        Sequence other = new Sequence();
        sequence.addAfter("A");
        other.addAfter("A");
        assertTrue(sequence.equals(other));
    }

    @Test
    public void testEqualsDifferentSize() {
        Sequence other = new Sequence();
        sequence.addAfter("A");
        assertFalse(sequence.equals(other));
    }

    @Test
    public void testEqualsDifferentCurrent() {
        Sequence other = new Sequence();
        sequence.addAfter("A");
        other.addAfter("A");
        other.advance();
        assertFalse(sequence.equals(other));
    }

    // clear tests
    @Test
    public void testClear() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.clear();
        assertEquals(0, sequence.size());
        assertNull(sequence.getCurrent());
        assertEquals("{} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAdvanceWithOneElement() {
        sequence.addAfter("A");
        sequence.advance();
        assertNull(sequence.getCurrent());
        assertEquals("{A} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testAddAllWithEmptySequence() {
        Sequence emptySequence = new Sequence();
        sequence.addAfter("A");
        sequence.addAll(emptySequence);
        assertEquals("{>A} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testCloneWithNonEmptySequence() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        
        Sequence cloned = sequence.clone();
        cloned.addAfter("C");
        
        assertEquals("{A, >B} (capacity = 10)", sequence.toString());
        assertEquals("{A, B, >C} (capacity = 10)", cloned.toString());
    }

    @Test
    public void testRemoveCurrentWithMultipleElements() {
        sequence.addAfter("A");
        sequence.addAfter("B");
        sequence.addAfter("C");
        
        sequence.removeCurrent();
        assertEquals("{A, >B} (capacity = 10)", sequence.toString());
    }

    @Test
    public void testEnsureCapacityWithLowerValue() {
        sequence.ensureCapacity(5);
        assertEquals(10, sequence.getCapacity());
    }

    @Test
    public void testEqualsWithDifferentElements() {
        Sequence another = new Sequence();
        sequence.addAfter("A");
        another.addAfter("B");
        
        assertFalse(sequence.equals(another));
    }
}
