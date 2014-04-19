package study.march.one;

/**
 * Created by volhovm on 3/6/14.
 */

@SuppressWarnings("AccessStaticViaInstance")
public class ArrayQueueADT {
    private int N = 0;
    private Object[] mainArray = new Object[10];
    private int head = 0;
    private int tail = 0;

    public static void push(ArrayQueueADT queue, Object o) {
        if (queue.N + 1 > queue.mainArray.length) {
            expandArray(queue);
        }
        queue.mainArray[queue.tail] = o;
        queue.tail = (queue.tail + 1) % (queue.mainArray.length);
        queue.N++;
    }

    private static void expandArray(ArrayQueueADT queue) {
        Object[] tempArray = new Object[queue.N * 3];
        if (queue.head <= queue.tail) {
            System.arraycopy(queue.mainArray,
                             queue.tail,
                             tempArray,
                             0,
                             queue.mainArray.length - queue.tail);
            System.arraycopy(queue.mainArray,
                             0,
                             tempArray,
                             queue.mainArray.length - queue.tail - 1,
                             queue.head);
        } else {
            System.arraycopy(queue.mainArray, 0, tempArray, 0, queue.N);
        }
        queue.head = 0;
        queue.tail = queue.N;
        queue.mainArray = tempArray;
    }

    public static Object pop(ArrayQueueADT queue) {
        assert queue.N > 0;
        Object ret = queue.mainArray[queue.head];
        queue.head = (queue.head + 1) % queue.mainArray.length;
        queue.N--;
        return ret;
    }

    public static Object peek(ArrayQueueADT queue) {
        assert queue.N > 0;
        return queue.mainArray[queue.head];
    }

    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.N == 0;
    }

    public static int size(ArrayQueueADT queue) {
        return queue.N;
    }
}
