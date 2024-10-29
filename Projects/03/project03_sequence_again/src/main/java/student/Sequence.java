package student;

import java.util.Objects;

/**
 * @author Ali Hamza
 * @version 09/28/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */

/**
 * A sequence class that uses LinkedList for storage.
 *
 * Invariants:
 * 1. The `list` field stores all the elements in the sequence and is never null.
 * 2. The `currentIndex` points to the index of the current element:
 *    - `-1` if there is no current element.
 *    - A valid index (0 to list.size() - 1) when an element is selected.
 * 3. The sequence's size is always equal to the size of the underlying linked list.
 * 4. The sequence grows dynamically; `INITIAL_CAPACITY` is retained for compliance
 *    with earlier specifications, though capacity is not strictly managed.
 */
public class Sequence {

    private LinkedList<String> list;
    private int currentIndex;
    private static final int INITIAL_CAPACITY = 10;

    /**
     * Constructs an empty sequence with initial capacity.
     */
    public Sequence() {
        this.list = new LinkedList<>();
        this.currentIndex = -1;
    }

    /**
     * Clears the sequence, removing all elements.
     */
    public void clear() {
        list = new LinkedList<>();
        currentIndex = -1;
    }

    /**
     * Returns the size of the sequence.
     *
     * @return the number of elements in the sequence
     */
    public int size() {
        return list.size();
    }

    /**
     * Adds an element before the current element.
     *
     * @param value the value to add
     * @throws IllegalArgumentException if the value is null
     */
    public void addBefore(String value) {
        Objects.requireNonNull(value, "Value cannot be null");

        if (currentIndex == -1) {
            list.addAtHead(value);
            currentIndex = 0;
        } else {
            list.addAt(value, currentIndex);
        }
    }

    /**
     * Adds an element after the current element.
     *
     * @param value the value to add
     * @throws IllegalArgumentException if the value is null
     */
    public void addAfter(String value) {
        Objects.requireNonNull(value, "Value cannot be null");

        if (currentIndex == -1) {
            list.addAtHead(value);
            currentIndex = 0;
        } else {
            list.addAt(value, currentIndex + 1);
            currentIndex++;
        }
    }

    /**
     * Removes the current element from the sequence.
     * If the sequence becomes empty, the current index is reset to -1.
     */
    public void removeCurrent() {
        if (currentIndex == -1) return;

        list.remove(currentIndex);

        if (list.size() == 0) {
            currentIndex = -1;
        } else if (currentIndex >= list.size()) {
            currentIndex = 0;
        }
    }

    /**
     * Advances to the next element in the sequence.
     * If at the end, resets the current index to -1.
     */
    public void advance() {
        if (currentIndex < list.size() - 1) {
            currentIndex++;
        } else {
            currentIndex = -1;
        }
    }

    /**
     * Starts the sequence at the first element.
     * If the sequence is empty, sets the current index to -1.
     */
    public void start() {
        currentIndex = list.size() > 0 ? 0 : -1;
    }

    /**
     * Checks if there is a current element in the sequence.
     *
     * @return true if a current element exists, false otherwise
     */
    public boolean isCurrent() {
        return currentIndex != -1;
    }

    /**
     * Returns the value of the current element.
     *
     * @return the value of the current element, or null if none
     */
    public String getCurrent() {
        return isCurrent() ? list.get(currentIndex) : null;
    }

    /**
     * Adds all elements from another sequence to this sequence.
     * The current index is set to the last added element or remains unchanged if empty.
     *
     * @param other the other sequence to add
     */
    public void addAll(Sequence other) {
        Objects.requireNonNull(other, "Other sequence cannot be null");

        int originalSize = list.size();
        for (int i = 0; i < other.size(); i++) {
            list.addAt(other.list.get(i), originalSize + i);
        }

        if (currentIndex == -1 && list.size() > 0) {
            currentIndex = 0;
        } else {
            currentIndex = list.size() - 1;
        }
    }

    /**
     * Creates a deep copy of this sequence.
     *
     * @return a cloned copy of this sequence
     */
    @Override
    public Sequence clone() {
        Sequence cloned = new Sequence();
        for (int i = 0; i < list.size(); i++) {
            cloned.list.addAt(list.get(i), i);
        }
        cloned.currentIndex = this.currentIndex;
        return cloned;
    }

    /**
     * Checks if this sequence is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if both sequences are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sequence)) return false;
        Sequence other = (Sequence) obj;
        return Objects.equals(this.list.toString(), other.list.toString()) &&
                this.currentIndex == other.currentIndex;
    }

    /**
     * Returns a string representation of the sequence.
     * Marks the current element with a '>'.
     *
     * @return the string representation of the sequence
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < list.size(); i++) {
            if (i == currentIndex) sb.append(">");
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
