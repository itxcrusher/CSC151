package student;

/**
 * A class that stores up to four unique page numbers for a word in the index.
 *
 * Invariants:
 * 1. The pages array always contains up to 4 unique page numbers.
 * 2. The count variable accurately reflects the number of pages stored (0 to 4).
 * 3. The pages are stored in the order they were added.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 */
public class Pagelist {
    /**
     * Maximum number of pages the pagelist can hold.
     */
    private static final int MAX_PAGES = 4;

    /**
     * Array to hold page numbers.
     */
    private int[] pages;

    /**
     * The number of page numbers currently stored in the pagelist.
     */
    private int count;

    /**
     * Constructs an empty Pagelist.
     */
    public Pagelist() {
        pages = new int[MAX_PAGES];
        count = 0;
    }

    /**
     * Adds a page number to the pagelist if it's not already present
     * and if there is space available.
     *
     * @param pageNumber the page number to add
     * @return true if the page number was added, false otherwise
     */
    public boolean addPage(int pageNumber) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Page number must be positive.");
        }
        if (contains(pageNumber)) {
            return false;
        }
        if (isFull()) {
            return false;
        }
        pages[count] = pageNumber;
        count++;
        return true;
    }

    /**
     * Checks if the pagelist contains a specific page number.
     *
     * @param pageNumber the page number to check for
     * @return true if the page number is in the pagelist, false otherwise
     */
    public boolean contains(int pageNumber) {
        for (int i = 0; i < count; i++) {
            if (pages[i] == pageNumber) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the pagelist is full (contains 4-page numbers).
     *
     * @return true if the pagelist is full, false otherwise
     */
    public boolean isFull() {
        return count >= MAX_PAGES;
    }

    /**
     * Returns a string representation of the pagelist in the format "{1, 2, 3}".
     *
     * @return the string representation of the pagelist
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < count; i++) {
            sb.append(pages[i]);
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
