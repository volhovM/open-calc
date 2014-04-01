package main.march.one.deque;

/**
 * Created by volhovm on 3/11/14.
 */
public class ArrayDequeSingleton {
    static private int n = 0;
    static private Object[] mainArray = new Object[10];
    static private int head = 1;
    static private int tail = 0;

    static public void addLast(Object o) {
        if (n + 1 > mainArray.length) {
            expandArray();
        }
        if (head == 0) {
            head = mainArray.length - 1;
        } else {
            head--;
        }
        mainArray[head] = o;
        n++;
    }

    static public void addFirst(Object o) {
        if (n + 1 > mainArray.length) {
            expandArray();
        }
        tail = (tail + 1) % (mainArray.length);
        mainArray[tail] = o;
        n++;
    }

    static private void expandArray() {
        Object[] tempArray = new Object[n * 3];
        if (head == 0 && tail == n) {
            System.arraycopy(mainArray, 0, tempArray, 0, n);
        } else {
            System.arraycopy(mainArray, head, tempArray, 0, mainArray.length - head);
            System.arraycopy(mainArray, 0, tempArray, mainArray.length - head, tail + 1);
        }
        head = 0;
        tail = n - 1;
        mainArray = tempArray;
    }

    static public Object removeLast() {
        assert n > 0;
        Object ret = mainArray[head];
        head = (head + 1) % mainArray.length;
        n--;
        return ret;
    }

    static public Object removeFirst() {
        assert n > 0;
        Object ret = mainArray[tail];
        if (tail == 0) {
            tail = mainArray.length - 1; //TODO +-1
        } else {
            tail--;
        }
        n--;
        return ret;
    }

    static public Object peekLast() {
        assert n > 0;
        return mainArray[head];
    }

    static public Object peekFirst() {
        assert n > 0;
        return mainArray[tail];
    }

    static public boolean isEmpty() {
        return n == 0;
    }

    static public int size() {
        return n;
    }
}
