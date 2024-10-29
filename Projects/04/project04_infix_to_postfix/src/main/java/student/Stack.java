package student;

import java.util.EmptyStackException;

/**
 * A generic stack implementation using a linked list as the underlying data structure.
 *
 * Invariants:
 * 1. The stack stores elements in a LIFO (Last-In, First-Out) order.
 * 2. The stack uses a LinkedList for internal storage,
 * but ListNode elements are never exposed directly.
 * 3. The stack allows pushing and popping elements,
 * and peeking at the top element without removing it.
 *
 * @param <T> the type of elements stored in the stack.
 */
public class Stack<T> {

    private final LinkedList<T> list;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        this.list = new LinkedList<>();
    }

    /**
     * Returns whether the stack is empty or not.
     *
     * @return {@code true} if the stack contains no elements, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return list.size() == 0;
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param toPush the element to push onto the stack.
     */
    public void push(T toPush) {
        list.addAtHead(toPush);
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the top element of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(0);
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(0);
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements currently in the stack.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a string representation of the stack.
     * The string format shows elements from the top to bottom of the stack.
     *
     * @return a string representation of the stack.
     */
    @Override
    public String toString() {
        return list.toString();
    }
}
