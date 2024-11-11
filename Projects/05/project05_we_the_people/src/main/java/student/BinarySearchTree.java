package student;

/**
 * A generic binary search tree (BST) implementation that stores elements of type T.
 *
 * Invariants:
 * 1. For any node N, all elements in N's left subtree are less than N's key.
 * 2. For any node N, all elements in N's right subtree are greater than N's key.
 * 3. The tree does not contain duplicate elements.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 *
 * @param <T> the type of elements stored in the BST, which must implement Comparable<T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private BSTNode<T> root;

    /**
     * Default constructor that creates an empty BST.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a new value into this BST.
     *
     * @param newValue the value to insert
     */
    public void insert(T newValue) {
        if (newValue == null) {
            throw new IllegalArgumentException("Cannot insert null into the tree.");
        }
        root = insert(root, newValue);
    }

    /**
     * Recursive helper method to insert a value into the tree rooted at subroot.
     *
     * @param subroot the root of the subtree to insert into
     * @param value   the value to insert
     * @return the root of the subtree after insertion
     */
    private BSTNode<T> insert(BSTNode<T> subroot, T value) {
        if (subroot == null) {
            return new BSTNode<>(value);
        }
        int comparison = value.compareTo(subroot.key);
        if (comparison < 0) {
            subroot.llink = insert(subroot.llink, value);
        } else if (comparison > 0) {
            subroot.rlink = insert(subroot.rlink, value);
        }
        return subroot;
    }

    /**
     * Deletes a value from the BST. If the value is not present, the tree remains unchanged.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot delete null from the tree.");
        }
        root = delete(root, value);
    }

    /**
     * Recursive helper method to delete a value from the tree rooted at subroot.
     *
     * @param subroot the root of the subtree to delete from
     * @param value   the value to delete
     * @return the root of the subtree after deletion
     */
    private BSTNode<T> delete(BSTNode<T> subroot, T value) {
        if (subroot == null) {
            return null;
        }
        int comparison = value.compareTo(subroot.key);
        if (comparison < 0) {
            subroot.llink = delete(subroot.llink, value);
        } else if (comparison > 0) {
            subroot.rlink = delete(subroot.rlink, value);
        } else {
            if (subroot.isLeaf()) {
                return null;
            } else if (subroot.hasLeftChildOnly()) {
                return subroot.llink;
            } else if (subroot.hasRightChildOnly()) {
                return subroot.rlink;
            } else {
                BSTNode<T> largest = findLargest(subroot.llink);
                subroot.key = largest.key;
                subroot.llink = delete(subroot.llink, largest.key);
            }
        }
        return subroot;
    }

    /**
     * Finds the largest node in the subtree rooted at subroot.
     *
     * @param node the root of the subtree
     * @return the node with the largest value in the subtree
     */
    private BSTNode<T> findLargest(BSTNode<T> node) {
        BSTNode<T> current = node;
        while (current.rlink != null) {
            current = current.rlink;
        }
        return current;
    }

    /**
     * Searches for a target value in the BST.
     *
     * @param target the value to search for
     * @return true if the target is found, false otherwise
     */
    public boolean search(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Cannot search for null in the tree.");
        }
        return search(root, target);
    }

    /**
     * Recursive helper method to search for a target value in the subtree rooted at subroot.
     *
     * @param subroot the root of the subtree to search in
     * @param target  the value to search for
     * @return true if the target is found, false otherwise
     */
    private boolean search(BSTNode<T> subroot, T target) {
        if (subroot == null) {
            return false;
        }
        int comparison = target.compareTo(subroot.key);
        if (comparison == 0) {
            return true;
        } else if (comparison < 0) {
            return search(subroot.llink, target);
        } else {
            return search(subroot.rlink, target);
        }
    }

    /**
     * Finds and returns the object in the tree that equals the target.
     *
     * @param target the object to find
     * @return the object in the tree that equals the target, or null if not found
     */
    public T find(T target) {
        if (target == null) {
            throw new IllegalArgumentException("Cannot find null in the tree.");
        }
        return find(root, target);
    }

    private T find(BSTNode<T> node, T target) {
        if (node == null) {
            return null;
        }
        int comparison = target.compareTo(node.key);
        if (comparison == 0) {
            return node.key;
        } else if (comparison < 0) {
            return find(node.llink, target);
        } else {
            return find(node.rlink, target);
        }
    }

    /**
     * Returns a string representation of the BST in the format:
     * (left subtree) value (right subtree)
     *
     * @return the string representation of the BST
     */
    @Override
    public String toString() {
        return toString(root);
    }

    /**
     * Recursive helper method for toString().
     *
     * @param node the root of the subtree to convert to a string
     * @return the string representation of the subtree
     */
    private String toString(BSTNode<T> node) {
        String ret = "";
        if (node != null) {
            ret += "(";
            ret += toString(node.llink);
            ret += " " + node.key + " ";
            ret += toString(node.rlink);
            ret += ")";
        }
        return ret;
    }

    /**
     * Performs an in-order traversal of the BST
     * and returns a string with each element on a new line.
     *
     * @return the string representation of the BST in-order
     */
    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    /**
     * Recursive helper method for in-order traversal.
     *
     * @param node the current node
     * @param sb   the StringBuilder to accumulate the output
     */
    private void inOrderTraversal(BSTNode<T> node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.llink, sb);
            sb.append(node.key.toString()).append("\n");
            inOrderTraversal(node.rlink, sb);
        }
    }
}
