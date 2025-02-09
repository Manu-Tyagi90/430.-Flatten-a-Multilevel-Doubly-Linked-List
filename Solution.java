import java.util.Stack;

class Node {
    int val;
    Node next;
    Node prev;
    Node child;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node next, Node prev, Node child) {
        this.val = val;
        this.next = next;
        this.prev = prev;
        this.child = child;
    }
}

public class Solution {
    public static Node flatten(Node l1) {
        if (l1 == null) {
            return l1;
        }

        Node cur = l1;
        Stack<Node> stack = new Stack<>();
        Node tail = null;

        while (cur != null) {
            if (cur.child != null) {
                if (cur.next != null) {
                    stack.push(cur.next);
                }
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }

            tail = cur;
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            cur = stack.pop();
            tail.next = cur;
            cur.prev = tail;

            while (cur != null) {
                tail = cur;
                cur = cur.next;
            }
        }

        return l1;
    }

    public static void main(String[] args) {
        /*
        1---2---3---4---5---6--NULL
                |
                7---8---9---10--NULL
                    |
                    11--12--NULL */
        
        Node l1 = new Node(1);  
        Node l2 = new Node(2);
        Node l3 = new Node(3);
        Node l4 = new Node(4);
        Node l5 = new Node(5);
        Node l6 = new Node(6);
        Node l7 = new Node(7);
        Node l8 = new Node(8);
        Node l9 = new Node(9);
        Node l10 = new Node(10);
        Node l11 = new Node(11);
        Node l12 = new Node(12);

        l1.next = l2;
        l2.prev = l1;
        l2.next = l3;
        l3.prev = l2;
        l3.next = l4;
        l4.prev = l3;
        l4.next = l5;
        l5.prev = l4;
        l5.next = l6;
        l6.prev = l5;

        l3.child = l7;
        l7.next = l8;
    
        l8.prev = l7;
        l8.next = l9;
        l9.prev = l8;
        l9.next = l10;
        l10.prev = l9;

        l8.child = l11;
        l11.next = l12;
        l12.prev = l11;

        Node res = flatten(l1);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
        


    }
}
// Time Complexity: O(n)
