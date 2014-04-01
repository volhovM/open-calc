package main.march.one.deque;

/**
 * Created by volhovm on 3/11/14.
 */
public class ArrayDequeADT {
    private int N = 0;
    private Object[] mainArray = new Object[10];
    private int head = 1;
    private int tail = 0;

    static public void addLast(ArrayDequeADT arrayDequeADT, Object o) {
        if (arrayDequeADT.N + 1 > arrayDequeADT.mainArray.length) {
            expandArray(arrayDequeADT);
        }
        if (arrayDequeADT.head == 0) {
            arrayDequeADT.head = arrayDequeADT.mainArray.length - 1;
        } else {
            arrayDequeADT.head--;
        }
        arrayDequeADT.mainArray[arrayDequeADT.head] = o;
        arrayDequeADT.N++;
    }

    static public void addFirst(ArrayDequeADT arrayDequeADT, Object o) {
        if (arrayDequeADT.N + 1 > arrayDequeADT.mainArray.length) {
            expandArray(arrayDequeADT);
        }
        arrayDequeADT.tail = (arrayDequeADT.tail + 1) % (arrayDequeADT.mainArray.length);
        arrayDequeADT.mainArray[arrayDequeADT.tail] = o;
        arrayDequeADT.N++;
    }

    static private void expandArray(ArrayDequeADT arrayDequeADT) {
        Object[] tempArray = new Object[arrayDequeADT.N * 3];
        if (arrayDequeADT.head == 0 && arrayDequeADT.tail == arrayDequeADT.N) {
            System.arraycopy(arrayDequeADT.mainArray, 0, tempArray, 0, arrayDequeADT.N);
        } else {
            System.arraycopy(arrayDequeADT.mainArray,
                             arrayDequeADT.head,
                             tempArray,
                             0,
                             arrayDequeADT.mainArray.length - arrayDequeADT.head);
            System.arraycopy(arrayDequeADT.mainArray,
                             0,
                             tempArray,
                             arrayDequeADT.mainArray.length - arrayDequeADT.head,
                             arrayDequeADT.tail + 1);
        }
        arrayDequeADT.head = 0;
        arrayDequeADT.tail = arrayDequeADT.N - 1;
        arrayDequeADT.mainArray = tempArray;
    }

    static public Object removeLast(ArrayDequeADT arrayDequeADT) {
        assert arrayDequeADT.N > 0;
        Object ret = arrayDequeADT.mainArray[arrayDequeADT.head];
        arrayDequeADT.head = (arrayDequeADT.head + 1) % arrayDequeADT.mainArray.length;
        arrayDequeADT.N--;
        return ret;
    }

    static public Object removeFirst(ArrayDequeADT arrayDequeADT) {
        assert arrayDequeADT.N > 0;
        Object ret = arrayDequeADT.mainArray[arrayDequeADT.tail];
        if (arrayDequeADT.tail == 0) {
            arrayDequeADT.tail = arrayDequeADT.mainArray.length - 1; //TODO +-1
        } else {
            arrayDequeADT.tail--;
        }
        arrayDequeADT.N--;
        return ret;
    }

    static public Object peekLast(ArrayDequeADT arrayDequeADT) {
        assert arrayDequeADT.N > 0;
        return arrayDequeADT.mainArray[arrayDequeADT.head];
    }

    static public Object peekFirst(ArrayDequeADT arrayDequeADT) {
        assert arrayDequeADT.N > 0;
        return arrayDequeADT.mainArray[arrayDequeADT.tail];
    }

    static public boolean isEmpty(ArrayDequeADT arrayDequeADT) {
        return arrayDequeADT.N == 0;
    }

    static public int size(ArrayDequeADT arrayDequeADT) {
        return arrayDequeADT.N;
    }
}
