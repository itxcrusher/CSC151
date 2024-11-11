package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Pagelist class to ensure correct behavior.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 */
public class PagelistTest {

    @Test
    public void testConstructor() {
        Pagelist pagelist = new Pagelist();
        assertNotNull(pagelist);
        assertEquals("{}", pagelist.toString());
    }

    @Test
    public void testAddPage() {
        Pagelist pagelist = new Pagelist();
        assertTrue(pagelist.addPage(1));
        assertEquals("{1}", pagelist.toString());
        assertTrue(pagelist.addPage(2));
        assertEquals("{1, 2}", pagelist.toString());
        assertFalse(pagelist.addPage(1)); // Duplicate
        assertEquals("{1, 2}", pagelist.toString());
        assertTrue(pagelist.addPage(3));
        assertTrue(pagelist.addPage(4));
        assertFalse(pagelist.addPage(5)); // Pagelist is full
        assertEquals("{1, 2, 3, 4}", pagelist.toString());
    }

    @Test
    public void testContains() {
        Pagelist pagelist = new Pagelist();
        pagelist.addPage(1);
        pagelist.addPage(2);
        assertTrue(pagelist.contains(1));
        assertTrue(pagelist.contains(2));
        assertFalse(pagelist.contains(3));
    }

    @Test
    public void testIsFull() {
        Pagelist pagelist = new Pagelist();
        assertFalse(pagelist.isFull());
        pagelist.addPage(1);
        pagelist.addPage(2);
        pagelist.addPage(3);
        assertFalse(pagelist.isFull());
        pagelist.addPage(4);
        assertTrue(pagelist.isFull());
    }

    @Test
    public void testToString() {
        Pagelist pagelist = new Pagelist();
        assertEquals("{}", pagelist.toString());
        pagelist.addPage(1);
        assertEquals("{1}", pagelist.toString());
        pagelist.addPage(2);
        assertEquals("{1, 2}", pagelist.toString());
        pagelist.addPage(3);
        pagelist.addPage(4);
        assertEquals("{1, 2, 3, 4}", pagelist.toString());
    }

    @Test
    public void testAddPageWhenFull() {
        Pagelist pagelist = new Pagelist();
        pagelist.addPage(1);
        pagelist.addPage(2);
        pagelist.addPage(3);
        pagelist.addPage(4);
        assertFalse(pagelist.addPage(5)); // Should not add as it is full
        assertTrue(pagelist.isFull());
        assertEquals("{1, 2, 3, 4}", pagelist.toString());
    }

    @Test
    public void testAddDuplicatePages() {
        Pagelist pagelist = new Pagelist();
        assertTrue(pagelist.addPage(1));
        assertFalse(pagelist.addPage(1)); // Duplicate
        assertEquals("{1}", pagelist.toString());
    }

    @Test
    public void testAddInvalidPageNumber() {
        Pagelist pagelist = new Pagelist();
        assertThrows(IllegalArgumentException.class, () -> pagelist.addPage(0));
        assertThrows(IllegalArgumentException.class, () -> pagelist.addPage(-1));
    }

    @Test
    public void testToStringEmptyPagelist() {
        Pagelist pagelist = new Pagelist();
        assertEquals("{}", pagelist.toString());
    }

    @Test
    public void testToStringFullPagelist() {
        Pagelist pagelist = new Pagelist();
        pagelist.addPage(1);
        pagelist.addPage(2);
        pagelist.addPage(3);
        pagelist.addPage(4);
        assertEquals("{1, 2, 3, 4}", pagelist.toString());
    }

    @Test
    public void testContainsEmptyPagelist() {
        Pagelist pagelist = new Pagelist();
        assertFalse(pagelist.contains(1));
    }
}
