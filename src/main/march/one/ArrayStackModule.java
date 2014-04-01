package main.march.one;

/**
 * Created by volhovm on 3/6/14.
 */
public class ArrayStackModule {
    private static Object[] elements = new Object[10];
    private static int size = 0;

    public static void push(Object o) {
        ensureCapacity(size + 1);
        elements[size++] = o;
    }

    private static void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            Object[] e = new Object[capacity * 2];
            System.arraycopy(elements, 0, e, 0, size);
            elements = e;
        }
    }

    public static Object pop() {
        assert size > 0; //enabled if -ea
        Object ret = elements[size - 1];
        elements[--size] = null;
        return ret;
    }

    public static Object peek() {
        assert size > 0;
        return elements[size - 1];
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return (size == 0);
    }
}
