public class LinkedList {
    Node head; // head of the list

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // method to insert a new node at a specific position
    public void insertAtPos(int pos, int data) {
        Node newNode = new Node(data);
        if (pos < 1) {
            System.out.println("Invalid position");
            return;
        }
        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        int count = 1;
        while (current!= null && count < pos - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            System.out.println("Position out of range");
            return;
        }

        newNode.next = current.next;
        current.next = newNode;
    }

    // method to delete a node at a specific position
    public void deleteAtPosition(int pos) {
        if (head == null) {
            System.out.println("Linked list is empty");
            return;
        }
        if (pos < 1) {
            System.out.println("Invalid position");
            return;
        }
        if (pos == 1) {
            head = head.next;
            return;
        }

        Node current = head;
        int count = 1;
        while (current!= null && count < pos - 1) {
            current = current.next;
            count++;
        }

        if (current == null || current.next == null) {
            System.out.println("Position out of range");
            return;
        }

        current.next = current.next.next;
    }

    // method to delete a node after a specific node
    public void deleteAfterNode(Node node) {
        if (node == null) {
            System.out.println("Invalid node");
            return;
        }

        if (node.next == null) {
            System.out.println("No node to delete after");
            return;
        }

        node.next = node.next.next;
    }

    // method to search for a node with a specific data value
    public Node searchNode(int data) {
        Node current = head;
        int pos = 1;
        while (current!= null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
            pos++;
        }
        return null;
    }

    // method to display the elements of the list
    public void display() {
        Node current = head;
        while (current!= null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // example usage
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.head = new Node(10);
        list.head.next = new Node(20);
        list.head.next.next = new Node(30);
        list.head.next.next.next = new Node(40);
        list.head.next.next.next.next = new Node(50);

        list.display(); // output: 10 20 30 40 50

        list.insertAtPos(3, 100);
        list.display(); // output: 10 20 100 30 40 50

        list.deleteAtPosition(3);
        list.display(); // output: 10 20 30 40 50

        Node node = list.searchNode(30);
        if (node!= null) {
            list.deleteAfterNode(node);
            list.display(); // output: 10 20 30 50
        }
    }
}