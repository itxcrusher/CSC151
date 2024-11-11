package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the IndexEntry class to ensure correct behavior.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 */
public class IndexEntryTest {

    @Test
    public void testConstructor() {
        IndexEntry entry = new IndexEntry("Example");
        assertEquals("Example", entry.getWord());
        assertNotNull(entry.getPagelist());
        assertEquals("{}", entry.getPagelist().toString());
    }

    @Test
    public void testConstructorWithNullWord() {
        assertThrows(IllegalArgumentException.class, () -> new IndexEntry(null));
    }

    @Test
    public void testAddPage() {
        IndexEntry entry = new IndexEntry("Test");
        assertTrue(entry.addPage(1));
        assertTrue(entry.addPage(2));
        assertFalse(entry.addPage(1)); // Duplicate
        assertTrue(entry.addPage(3));
        assertTrue(entry.addPage(4));
        assertFalse(entry.addPage(5)); // Pagelist is full
        assertEquals("{1, 2, 3, 4}", entry.getPagelist().toString());
    }

    @Test
    public void testHasPage() {
        IndexEntry entry = new IndexEntry("Test");
        entry.addPage(1);
        entry.addPage(2);
        assertTrue(entry.hasPage(1));
        assertFalse(entry.hasPage(3));
    }

    @Test
    public void testIsFull() {
        IndexEntry entry = new IndexEntry("Test");
        assertFalse(entry.isFull());
        entry.addPage(1);
        entry.addPage(2);
        entry.addPage(3);
        entry.addPage(4);
        assertTrue(entry.isFull());
    }

    @Test
    public void testCompareTo() {
        IndexEntry entryA = new IndexEntry("Apple");
        IndexEntry entryB = new IndexEntry("Banana");
        IndexEntry entryC = new IndexEntry("apple");

        assertTrue(entryA.compareTo(entryB) < 0);  // "Apple" comes before "Banana"
        assertTrue(entryB.compareTo(entryA) > 0);  // "Banana" comes after "Apple"
        assertTrue(entryA.compareTo(entryC) < 0);  // "Apple" (ASCII) comes before "apple"
        assertEquals(0, entryA.compareTo(new IndexEntry("Apple"))); // Equal words
    }

    @Test
    public void testCompareToWithNull() {
        IndexEntry entry = new IndexEntry("Apple");
        assertThrows(NullPointerException.class, () -> entry.compareTo(null));
    }

    @Test
    public void testEquals() {
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Apple");
        IndexEntry entry3 = new IndexEntry("Banana");

        assertEquals(entry1, entry2);
        assertNotEquals(entry1, entry3);
        assertNotEquals(entry1, null);
        assertNotEquals(entry1, "Apple");
    }

    @Test
    public void testHashCode() {
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Apple");
        assertEquals(entry1.hashCode(), entry2.hashCode());
    }

    @Test
    public void testToString() {
        IndexEntry entry = new IndexEntry("Test");
        entry.addPage(1);
        entry.addPage(2);
        assertEquals("Test {1, 2}", entry.toString());
    }

    @Test
    public void testEqualsWithDifferentObjectType() {
        IndexEntry entry = new IndexEntry("Apple");
        String notAnIndexEntry = "Apple";
        assertNotEquals(entry, notAnIndexEntry);
    }

    @Test
    public void testHashCodeConsistency() {
        IndexEntry entry = new IndexEntry("Apple");
        int initialHashCode = entry.hashCode();
        for (int i = 0; i < 10; i++) {
            assertEquals(initialHashCode, entry.hashCode());
        }
    }

    @Test
    public void testEqualsSameObject() {
        IndexEntry entry = new IndexEntry("Apple");
        assertTrue(entry.equals(entry));
    }

    @Test
    public void testEqualsEqualWords() {
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Apple");
        assertTrue(entry1.equals(entry2));
    }

    @Test
    public void testEqualsDifferentWords() {
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Banana");
        assertFalse(entry1.equals(entry2));
    }

    @Test
    public void testEqualsWithNull() {
        IndexEntry entry = new IndexEntry("Apple");
        assertFalse(entry.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        IndexEntry entry = new IndexEntry("Apple");
        String notAnIndexEntry = "Apple";
        assertFalse(entry.equals(notAnIndexEntry));
    }

    @Test
    public void testCompareToNull() {
        IndexEntry entry = new IndexEntry("Apple");
        assertThrows(NullPointerException.class, () -> entry.compareTo(null));
    }
}
