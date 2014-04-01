package main.march.one.deque;

/**
 * Created by volhovm on 3/10/14.
 */
public class EasyDequeTest {
    public static void main(String[] args) {
        ArrayDeque deque = new ArrayDeque();
        //        for (int i = 1; i <= 40; i++) {
        //            deque.addFirst(i);
        //            deque.addLast(-i);
        //        }
        //        while (!deque.isEmpty()){
        //            System.out.println(deque.peekFirst());
        //            System.out.println(deque.removeFirst());
        //            System.out.println(deque.peekLast());
        //            System.out.println(deque.removeLast());
        //        }

        for (int i = 0; i < 100; i++) {
            deque.addFirst(i);
        }
        while (!deque.isEmpty()) {
            System.out.println(deque.peekLast() + " " + deque.removeLast());
        }
    }
}
