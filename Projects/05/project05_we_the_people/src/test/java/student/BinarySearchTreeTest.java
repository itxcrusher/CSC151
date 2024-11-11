package student;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the BinarySearchTree class to ensure correct behavior.
 *
 * @author Ali Hamza
 * @version 11/11/2024
 */
public class BinarySearchTreeTest {

    @Test
    public void testInsertAndSearchWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("Banana");
        IndexEntry entry2 = new IndexEntry("Apple");
        IndexEntry entry3 = new IndexEntry("Cherry");

        tree.insert(entry1);
        tree.insert(entry2);
        tree.insert(entry3);

        assertTrue(tree.search(entry1));
        assertTrue(tree.search(entry2));
        assertTrue(tree.search(entry3));
        assertFalse(tree.search(new IndexEntry("Date")));
    }

    @Test
    public void testInsertDuplicatesWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry = new IndexEntry("Apple");

        tree.insert(entry);
        tree.insert(entry); // Attempt to insert duplicate

        // There should be only one instance of "Apple"
        assertTrue(tree.search(entry));
    }

    @Test
    public void testDeleteLeafNodeWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Banana");
        IndexEntry entry3 = new IndexEntry("Cherry");

        tree.insert(entry2);
        tree.insert(entry1);
        tree.insert(entry3);

        tree.delete(entry3); // Deleting leaf node "Cherry"
        assertFalse(tree.search(entry3));
        assertTrue(tree.search(entry1));
        assertTrue(tree.search(entry2));
    }

    @Test
    public void testDeleteNodeWithLeftChildOnlyWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("D");
        IndexEntry entry2 = new IndexEntry("B");
        IndexEntry entry3 = new IndexEntry("A");

        tree.insert(entry1);
        tree.insert(entry2);
        tree.insert(entry3);

        tree.delete(entry2); // Deleting node "B" with left child only
        assertFalse(tree.search(entry2));
        assertTrue(tree.search(entry1));
        assertTrue(tree.search(entry3));
    }

    @Test
    public void testDeleteNodeWithRightChildOnlyWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("A");
        IndexEntry entry2 = new IndexEntry("B");
        IndexEntry entry3 = new IndexEntry("C");

        tree.insert(entry1);
        tree.insert(entry2);
        tree.insert(entry3);

        tree.delete(entry2); // Deleting node "B" with right child only
        assertFalse(tree.search(entry2));
        assertTrue(tree.search(entry1));
        assertTrue(tree.search(entry3));
    }

    @Test
    public void testDeleteNodeWithTwoChildrenWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("Mango");
        IndexEntry entry2 = new IndexEntry("Apple");
        IndexEntry entry3 = new IndexEntry("Banana");
        IndexEntry entry4 = new IndexEntry("Cherry");

        tree.insert(entry1);
        tree.insert(entry2);
        tree.insert(entry3);
        tree.insert(entry4);

        tree.delete(entry2); // Deleting node "Apple" with two children
        assertFalse(tree.search(entry2));
        assertTrue(tree.search(entry1));
        assertTrue(tree.search(entry3));
        assertTrue(tree.search(entry4));
    }

    @Test
    public void testInsertAndSearchWithString() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");

        assertTrue(tree.search("Apple"));
        assertTrue(tree.search("Banana"));
        assertTrue(tree.search("Cherry"));
        assertFalse(tree.search("Date"));
    }

    @Test
    public void testDeleteWithString() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");

        tree.delete("Banana");
        assertFalse(tree.search("Banana"));
        assertTrue(tree.search("Apple"));
        assertTrue(tree.search("Cherry"));
    }

    @Test
    public void testDeleteRootNode() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");

        tree.delete("Banana"); // Deleting root node
        assertFalse(tree.search("Banana"));
        assertTrue(tree.search("Apple"));
        assertTrue(tree.search("Cherry"));
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");

        String expected = "Apple\nBanana\nCherry\n";
        assertEquals(expected, tree.inOrderTraversal());
    }

    @Test
    public void testInOrderTraversalEmptyTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertEquals("", tree.inOrderTraversal());
    }

    @Test
    public void testDeleteNonExistingElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();

        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");

        tree.delete("Date"); // Deleting non-existing element
        // Tree should remain unchanged
        assertTrue(tree.search("Apple"));
        assertTrue(tree.search("Banana"));
        assertTrue(tree.search("Cherry"));
    }

    @Test
    public void testDeleteFromEmptyTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.delete("Apple"); // Deleting from empty tree
        assertFalse(tree.search("Apple"));
    }

    @Test
    public void testInsertNull() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.insert(null));
    }

    @Test
    public void testDeleteNull() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.delete(null));
    }

    @Test
    public void testSearchNull() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.search(null));
    }

    @Test
    public void testToStringMethod() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("Banana");
        tree.insert("Apple");
        tree.insert("Cherry");
        String expected = "(( Apple ) Banana ( Cherry ))";
        assertEquals(expected, tree.toString());
    }

    @Test
    public void testDeleteRootNodeWithTwoChildren() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("M");
        tree.insert("B");
        tree.insert("Q");
        tree.insert("A");
        tree.insert("C");
        tree.insert("O");
        tree.insert("Z");
        tree.delete("M");
        assertFalse(tree.search("M"));
        assertTrue(tree.search("B"));
        assertTrue(tree.search("Q"));
        assertTrue(tree.search("A"));
        assertTrue(tree.search("C"));
        assertTrue(tree.search("O"));
        assertTrue(tree.search("Z"));
    }

    @Test
    public void testDeleteNodeWithTwoChildrenDeepTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 35, 45, 65, 75};
        for (int value : values) {
            tree.insert(value);
        }
        tree.delete(30);
        assertFalse(tree.search(30));
        for (int value : values) {
            if (value != 30) {
                assertTrue(tree.search(value));
            }
        }
    }

    @Test
    public void testFindNull() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertThrows(IllegalArgumentException.class, () -> tree.find(null));
    }

    @Test
    public void testFindNonExistingElementReturnsNull() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("Apple");
        tree.insert("Banana");

        assertNull(tree.find("Cherry"));
    }

    @Test
    public void testToStringEmptyTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        assertEquals("", tree.toString());
    }

    @Test
    public void testToStringComplexTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("M");
        tree.insert("B");
        tree.insert("Q");
        tree.insert("A");
        tree.insert("C");

        String expected = "((( A ) B ( C )) M ( Q ))";
        assertEquals(expected, tree.toString());
    }

    @Test
    public void testFindExistingElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("Apple");
        tree.insert("Banana");
        tree.insert("Cherry");

        String result = tree.find("Banana");
        assertNotNull(result);
        assertEquals("Banana", result);
    }

    @Test
    public void testFindNonExistingElement() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("Apple");
        tree.insert("Banana");

        String result = tree.find("Date");
        assertNull(result);
    }

    @Test
    public void testFindInEmptyTree() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        String result = tree.find("Apple");
        assertNull(result);
    }

    @Test
    public void testFindWithIndexEntry() {
        BinarySearchTree<IndexEntry> tree = new BinarySearchTree<>();
        IndexEntry entry1 = new IndexEntry("Apple");
        IndexEntry entry2 = new IndexEntry("Banana");

        tree.insert(entry1);
        tree.insert(entry2);

        IndexEntry result = tree.find(new IndexEntry("Banana"));
        assertNotNull(result);
        assertEquals(entry2, result);
    }

    @Test
    public void testFindComparisonLessThanZero() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("M");
        tree.insert("G");
        tree.insert("T");

        String result = tree.find("B");
        assertNull(result);
    }

    @Test
    public void testHasRightChildOnlyTrue() {
        BSTNode<String> node = new BSTNode<>("Parent");
        node.rlink = new BSTNode<>("RightChild");

        assertTrue(node.hasRightChildOnly());
    }

    @Test
    public void testHasRightChildOnlyFalse() {
        BSTNode<String> node = new BSTNode<>("Parent");
        assertFalse(node.hasRightChildOnly());

        node.llink = new BSTNode<>("LeftChild");
        assertFalse(node.hasRightChildOnly());

        node.rlink = new BSTNode<>("RightChild");
        assertFalse(node.hasRightChildOnly());
    }

    @Test
    public void testBSTNodeToString() {
        BSTNode<String> node = new BSTNode<>("NodeValue");
        assertEquals("NodeValue", node.toString());
    }

    @Test
    public void testDeleteNodeWithRightChildOnly() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.insert("M");
        tree.insert("N");

        tree.delete("M");

        assertFalse(tree.search("M"));
        assertTrue(tree.search("N"));
    }
}
