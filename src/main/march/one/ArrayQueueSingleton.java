package main.march.one;

/**
 * Created by volhovm on 3/6/14.
 */

public class ArrayQueueSingleton {
    private static int N = 0;
    private static Object[] mainArray = new Object[10];
    private static int head = 0;
    private static int tail = 0;

    public static void push(Object o) {
        if (N + 1 > mainArray.length) {
            expandArray();
        }
        mainArray[tail] = o;
        tail = (tail + 1) % (mainArray.length);
        N++;
    }

    private static void expandArray() {
        Object[] tempArray = new Object[N * 3];
        if (head <= tail) {
            System.arraycopy(mainArray, tail, tempArray, 0, mainArray.length - tail);
            System.arraycopy(mainArray, 0, tempArray, mainArray.length - tail - 1, head);
        } else {
            System.arraycopy(mainArray, 0, tempArray, 0, N);
        }
        head = 0;
        tail = N;
        mainArray = tempArray;
    }

    public static Object pop() {
        assert N > 0;
        Object ret = mainArray[head];
        head = (head + 1) % mainArray.length;
        N--;
        return ret;
    }

    public static Object peek() {
        assert N > 0;
        return mainArray[head];
    }

    public static boolean isEmpty() {
        return N == 0;
    }

    public static int size() {
        return N;
    }
}
