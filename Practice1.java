import java.util.Stack;

class Node {
    int val;
    Node prev;
    Node next;
    Node child;
}

public class Practice1 {
    public static Node flatten(Node l1) {
        if (l1 == null) return null;

        Node current = l1;
        Node tail = l1;
        Stack<Node> stack = new Stack<>();

        while (current != null) {
            if (current.child != null) {
                if (current.next != null) {
                    stack.push(current.next);
                    current.next.prev = null;
                }

                current.next = current.child;
                current.child.prev = current;
                current.child = null;
            }
            tail = current;
            current = current.next;
        }

        while (!stack.isEmpty()) {
            current = stack.pop();
            tail.next = current;
            current.prev = tail;
            while (current != null) {
                tail = current;
                current = current.next;
            }
        }

        return l1;
    }
}
