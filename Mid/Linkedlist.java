class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Linkedlist {
    Node head;

    // Method to add a new node at the end
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    // Method to print the linked list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Linkedlist list = new Linkedlist();

        // Add elements to the linked list
        list.add(1);
        list.add(2);
        list.add(3);

        // Print all elements of the linked list
        System.out.print("\nLinked list elements --> ");
        list.printList();

        // Add another element
        list.add(4);
        System.out.print("\nUpdated linked list elements --> ");
        list.printList();
        System.out.println();
    }
}
