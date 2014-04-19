package study.march.two;

/**
 * Created by volhovm on 3/13/14.
 */
public class LinkedStack extends AbstactStack implements Stack {
    private Node head;

    public void push(Object element) {
        head = new Node(element, head);
        size++;
    }

    protected Object popImpl() {
        Object res = head.value;
        head = head.next;
        return res;
    }

    private static class Node {
        private Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        private final Object value;
        private final Node next;
    }
}
