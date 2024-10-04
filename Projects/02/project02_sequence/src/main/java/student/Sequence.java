package student;
/**
 * @author Ali Hamza
 * @version 09/28/2024
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus.
 */
public class Sequence {
    /**
     * Invariant:
     * 1. `size` represents the number of elements in the sequence.
     * 2. `capacity` is the length of the internal array `data`.
     * 3. `current` is a valid index (0 <= current < size) or -1 if there is no current element.
     */
    private String[] data;
    private int current;
    private int size;
    private int capacity;
    private static final int INITIAL_CAPACITY = 10;
    private static final int ZERO_INDEX = 0;
    private static final int NO_ELEMENT = -1;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;

    /**
     * Creates a new sequence with initial capacity 10.
     */
    public Sequence() {
        this(INITIAL_CAPACITY);
    }
    

    /**
     * Creates a new sequence.
     * 
     * @param initialCapacity the initial capacity of the sequence.
     */
    public Sequence(int initialCapacity) {
        this.capacity = initialCapacity;
        this.data = new String[capacity];
        this.size = ZERO_INDEX;
        this.current = NO_ELEMENT;
    }

    /**
     * Adds and element before current element
     * 
     * @param value the value of element
     */
    public void addBefore(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (size == capacity) {
            ensureCapacity(SECOND_INDEX * capacity + FIRST_INDEX);
        }

        if (isCurrent()) {
            for (int i = size; i > current; i--) {
                data[i] = data[i - FIRST_INDEX];
            }
            data[current] = value;
        } else {
            for (int i = size; i > ZERO_INDEX; i--) {
                data[i] = data[i - FIRST_INDEX];
            }
            data[ZERO_INDEX] = value;
            current = ZERO_INDEX;
        }

        size++;
    }


    /**
     * Add a string to the sequence in the location after the current
     * element. If the sequence has no current element, the string is
     * added to the end of the sequence.
     *
     * The added element becomes the current element.
     *
     * If the sequences's capacity has been reached, the sequence will
     * expand to twice its current capacity plus 1.
     *
     * @param value the string to add.
     */
    public void addAfter(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }

        if (size == capacity) {
            ensureCapacity(SECOND_INDEX * capacity + FIRST_INDEX);
        }

        if (isCurrent()) {
            for (int i = size; i > current + FIRST_INDEX; i--) {
                data[i] = data[i - FIRST_INDEX];
            }
            data[current + FIRST_INDEX] = value;
            current++;
        } else {
            data[size] = value;
            current = size;
        }
        size++;
    }

    
    /**
     * @return true if and only if the sequence has a current element.
     */
    public boolean isCurrent() {
        return current >= ZERO_INDEX && current < size;
    }
    
    
    /**
     * @return the capacity of the sequence.
     */
    public int getCapacity() {
        return capacity;
    }

    
    /**
     * @return the element at the current location in the sequence, or
     * null if there is no current element.
     */
    public String getCurrent() {
        if (isCurrent()) {
            return data[current];
        } else {
            return null;
        }
    }
    
    
    /**
     * Increase the sequence's capacity to be at least minCapacity.
     * Do nothing if current capacity is already >= minCapacity.
     *
     * @param minCapacity the minimum capacity that the sequence
     * should now have.
     */
    public void ensureCapacity(int minCapacity) {
        if (capacity < minCapacity) {
            String[] newData = new String[minCapacity];
            copyArray(data, newData, size, ZERO_INDEX);
            data = newData;
            capacity = minCapacity;
        }
    }

    /**
     * Helper method to copy elements from one array to another.
     *
     * @param source the array to copy elements from.
     * @param destination the array to copy elements to.
     * @param size the number of elements to copy.
     * @param destPos destination position to paste
     */
    private void copyArray(String[] source, String[] destination, int size, int destPos) {
        for (int i = ZERO_INDEX; i < size; i++) {
            destination[destPos + i] = source[i];
        }
    }


    /**
     * Place the contents of another sequence at the end of this sequence.
     *
     * If adding all elements of the other sequence would exceed the
     * capacity of this sequence, the capacity is changed to make room for
     * all of the elements to be added.
     * 
     * Postcondition: NO SIDE EFFECTS!  the other sequence should be left
     * unchanged.  The current element of both sequences should remain
     * where they are. (When this method ends, the current element
     * should refer to the same element that it did at the time this method
     * started.)
     *
     * @param another the sequence whose contents should be added.
     */
    public void addAll(Sequence another) {
        if (another == null || another.size == ZERO_INDEX) {
            return;
        }
        ensureCapacity(size + another.size);
        copyArray(another.data, data, another.size, size);
        size += another.size;
    }

    
    /**
     * Move forward in the sequence so that the current element is now
     * the next element in the sequence.
     *
     * If the current element was already the end of the sequence,
     * then advancing causes there to be no current element.
     *
     * If there is no current element to begin with, do nothing.
     */
    public void advance() {
        if (isCurrent()) {
            if (current == size - FIRST_INDEX) {
                current = NO_ELEMENT;
            } else {
                current++;
            }
        }
    }

    
    /**
     * Make a copy of this sequence.  Subsequence changes to the copy
     * do not affect the current sequence, and vice versa.
     * 
     * Postcondition: NO SIDE EFFECTS!  This sequence's current
     * element should remain unchanged.  The clone's current
     * element will correspond to the same place as in the original.
     *
     * @return the copy of this sequence.
     */
    @Override
    public Sequence clone() {
        Sequence copy = new Sequence(capacity);

        for (int i = ZERO_INDEX; i < size; i++) {
            copy.data[i] = this.data[i];
        }

        copy.size = this.size;
        copy.current = this.current;
        return copy;
    }
   
    
    /**
     * Remove the current element from this sequence.  The following
     * element, if there was one, becomes the current element.  If
     * there was no following element (current was at the end of the
     * sequence), the sequence now has no current element.
     *
     * If there is no current element, does nothing.
     */
    public void removeCurrent() {
        if (isCurrent()) {
            for (int i = current; i < size - FIRST_INDEX; i++) {
                data[i] = data[i + FIRST_INDEX];
            }
            data[size - FIRST_INDEX] = null;
            size--;
    
            if (size == ZERO_INDEX) {
                current = NO_ELEMENT;
            } else if (current >= size) {
                current = size - FIRST_INDEX;
            }
        }
    }    


    /**
     * @return the number of elements stored in the sequence.
     */
    public int size() {
        return size;
    }

    
    /**
     * Set the current element to the start of the sequence.  If the
     * sequence is empty, the sequence has no current element.
     */
    public void start() {
        if (size > ZERO_INDEX) {
            current = ZERO_INDEX;
        } else {
            current = NO_ELEMENT;
        }
    }

    
    /**
     * Reduce the current capacity to its actual size, so that it has
     * capacity to store only the elements currently stored.
     */
    public void trimToSize() {
        if (capacity > size) {
            String[] newData = new String[size];
            copyArray(data, newData, size, ZERO_INDEX);
            data = newData;
            capacity = size;
        }
    }
    
    
    /**
     * Produce a string representation of this sequence.  The current
     * location is indicated by a >.  For example, a sequence with "A"
     * followed by "B", where "B" is the current element, and the
     * capacity is 5, would print as:
     * 
     *    {A, >B} (capacity = 5)
     * 
     * The string you create should be formatted like the above example,
     * with a comma following each element, no comma following the
     * last element, and all on a single line.  An empty sequence
     * should give back "{}" followed by its capacity.
     * 
     * @return a string representation of this sequence.
     */
    @Override
    public String toString() {
        String result = "{";

        for (int i = ZERO_INDEX; i < size; i++) {
            if (i == current) {
                result += ">" + data[i];
            } else {
                result += data[i];
            }

            if (i < size - FIRST_INDEX) {
                result += ", ";
            }
        }

        result += "} (capacity = " + capacity + ")";
        return result;
    }


    /**
     * Check whether another sequence is equal to this one.  To be
     * considered equal, the other sequence must have the same size
     * as this sequence, have the same elements, in the same
     * order, and with the same element marked
     * current.  The capacity can differ.
     * 
     * Postcondition: NO SIDE EFFECTS!  this sequence and the
     * other sequence should remain unchanged, including the
     * current element.
     * 
     * @param other the other Sequence with which to compare
     * @return true iff the other sequence is equal to this one.
     */
    public boolean equals(Sequence other) {
        if (other == null || this.size != other.size || this.current != other.current) {
            return false;
        }
        for (int i = ZERO_INDEX; i < size; i++) {
            if (!this.data[i].equals(other.data[i])) {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * 
     * @return true if Sequence empty, else false
     */
    public boolean isEmpty() {
        return size == ZERO_INDEX;
    }
    
    
    /**
     *  Empty the sequence.  There should be no current element.
     */
    public void clear() {
        for (int i = ZERO_INDEX; i < size; i++) {
            data[i] = null;
        }
        size = ZERO_INDEX;
        current = NO_ELEMENT;
    }

}
