package student;

import java.util.NoSuchElementException;

/**
 * A generic singly linked list implementation.
 *
 * Invariants:
 * 1. The `head` field points to the first node in the list, or null if the list is empty.
 * 2. The `tail` field points to the last node in the list, or null if the list is empty.
 * 3. The `current` field points to the current node for iteration or operations, or null if no current node.
 * 4. The `size` field reflects the number of nodes in the list and is always non-negative.
 * 5. The `size` field is always consistent with the actual number of nodes in the list.
 *
 * @param <E> the type of elements stored in the linked list.
 */
public class LinkedList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;
    private ListNode<E> current;
    private int size;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }

    /**
     * Returns the size of the linked list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element at the head of the list.
     *
     * @param toAdd the element to add
     */
    public void addAtHead(E toAdd) {
        ListNode<E> newNode = new ListNode<>(toAdd, head);
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        current = head;
        size++;
    }

    /**
     * Adds an element at the tail of the list.
     *
     * @param toAdd the element to add
     */
    public void addAtTail(E toAdd) {
        ListNode<E> newNode = new ListNode<>(toAdd, null);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        current = tail;
        size++;
    }

    /**
     * Adds an element at a specific index.
     *
     * @param toAdd the element to add
     * @param index the index where the element is added
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void addAt(E toAdd, int index) {
        validateIndexForAdd(index);

        if (index == 0) {
            addAtHead(toAdd);
        } else if (index == size) {
            addAtTail(toAdd);
        } else {
            ListNode<E> previous = getNodeAt(index - 1);
            ListNode<E> newNode = new ListNode<>(toAdd, previous.getNext());
            previous.setNext(newNode);
            current = newNode;
            size++;
        }
    }

    /**
     * Retrieves the element at a given index.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        validateIndex(index);
        current = getNodeAt(index);
        return current.getData();
    }

    /**
     * Updates the element at a specific index.
     *
     * @param value the new value to set
     * @param index the index of the element to update
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void set(E value, int index) {
        validateIndex(index);
        ListNode<E> node = getNodeAt(index);
        node.setData(value);
        current = node;
    }

    /**
     * Removes the element at a given index.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        validateIndex(index);

        if (index == 0) {
            return removeHead();
        } else {
            ListNode<E> previous = getNodeAt(index - 1);
            ListNode<E> toRemove = previous.getNext();
            previous.setNext(toRemove.getNext());

            if (toRemove == tail) {
                tail = previous;
            }

            current = previous.getNext() != null ? previous.getNext() : previous;
            size--;
            return toRemove.getData();
        }
    }

    /**
     * Removes the head element.
     *
     * @return the removed element from the head
     */
    private E removeHead() {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }
        E data = head.getData();
        head = head.getNext();

        if (head == null) {
            tail = null;
        }
        current = head;
        size--;
        return data;
    }

    /**
     * Retrieves the node at a given index.
     *
     * @param index the index of the node
     * @return the node at the specified index
     */
    private ListNode<E> getNodeAt(int index) {
        ListNode<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    /**
     * Validates the index for retrieving elements.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Validates the index for adding elements.
     *
     * @param index the index to validate
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    /**
     * Returns a string representation of the linked list.
     *
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        ListNode<E> node = head;
        while (node != null) {
            if (node == current) {
                result.append(">");
            }
            result.append(node.getData());
            if (node.getNext() != null) {
                result.append(", ");
            }
            node = node.getNext();
        }
        result.append("}");
        return result.toString();
    }
}
