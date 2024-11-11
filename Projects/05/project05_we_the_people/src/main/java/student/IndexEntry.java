package student;

/**
 * Represents an entry in the index, containing a word and its associated pagelist.
 *
 * Invariants:
 * 1. The word is a non-null String.
 * 2. The pagelist is non-null and manages the pages where the word appears.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 */
public class IndexEntry implements Comparable<IndexEntry> {
    /**
     * The word being indexed.
     */
    private String word;

    /**
     * The pagelist associated with the word.
     */
    private Pagelist pagelist;

    /**
     * Constructs an IndexEntry with the specified word.
     *
     * @param word the word to index
     * @throws IllegalArgumentException if the word is null
     */
    public IndexEntry(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Word cannot be null.");
        }
        this.word = word;
        this.pagelist = new Pagelist();
    }

    /**
     * Adds a page number to the pagelist if possible.
     *
     * @param pageNumber the page number to add
     * @return true if the page number was added, false otherwise
     */
    public boolean addPage(int pageNumber) {
        return pagelist.addPage(pageNumber);
    }

    /**
     * Checks if the pagelist contains the specified page number.
     *
     * @param pageNumber the page number to check
     * @return true if the page number is in the pagelist, false otherwise
     */
    public boolean hasPage(int pageNumber) {
        return pagelist.contains(pageNumber);
    }

    /**
     * Checks if the pagelist is full.
     *
     * @return true if the pagelist is full, false otherwise
     */
    public boolean isFull() {
        return pagelist.isFull();
    }

    /**
     * Returns the word associated with this IndexEntry.
     *
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the pagelist associated with this IndexEntry.
     *
     * @return the pagelist
     */
    public Pagelist getPagelist() {
        return pagelist;
    }

    /**
     * Compares this IndexEntry with another based on the word, preserving case.
     * Ordering is based on ASCII values, so uppercase letters come before lowercase letters.
     *
     * @param other the other IndexEntry to compare with
     * @return a negative integer, zero, or a positive integer as this word is less than,
     * equal to, or greater than the specified word
     */
    @Override
    public int compareTo(IndexEntry other) {
        return this.word.compareTo(other.word);
    }

    /**
     * Checks if this IndexEntry is equal to another object.
     *
     * @param obj the reference object with which to compare
     * @return true if this object is equal to the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        IndexEntry other = (IndexEntry) obj;
        return word.equals(other.word);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        return word.hashCode();
    }

    /**
     * Returns a string representation of the IndexEntry in the format "word: {pageList}".
     *
     * @return the string representation of this IndexEntry
     */
    @Override
    public String toString() {
        return word + " " + pagelist.toString();
    }
}
