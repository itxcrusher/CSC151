package student;

/**
 * Represents a single node in a linked list.
 *
 * Invariants:
 * 1. The `data` field contains the element stored in this node. It can be null.
 * 2. The `next` field points to the next node in the list or is null if this is the last node.
 *
 * @param <E> the type of data stored in the node.
 */
public class ListNode<E> {
    private E data;
    private ListNode<E> next;

    /**
     * Constructor to initialize the node with data and a reference to the next node.
     *
     * @param data the data to store in this node.
     * @param next the reference to the next node.
     */
    public ListNode(E data, ListNode<E> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Overloaded constructor to initialize the node with only data.
     * The next reference is set to {@code null} by default.
     *
     * @param data the data to store in this node.
     */
    public ListNode(E data) {
        this(data, null);
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data in the node.
     */
    public E getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data the new data to store in this node.
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the reference to the next node in the list.
     *
     * @return the next node, or {@code null} if this is the last node.
     */
    public ListNode<E> getNext() {
        return next;
    }

    /**
     * Sets the reference to the next node in the list.
     *
     * @param next the next node to link to.
     */
    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    /**
     * Provides a string representation of the node's data for debugging purposes.
     *
     * @return a string representing the data stored in this node.
     */
    @Override
    public String toString() {
        return "ListNode{" + "data=" + data + "}";
    }
}
