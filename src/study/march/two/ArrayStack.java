package study.march.two;

/**
 * Created by volhovm on 3/6/14.
 */
public class ArrayStack extends AbstactStack implements Stack {
    private Object[] elements = new Object[10];

    public void push(Object o) {
        ensureCapacity(size + 1);
        elements[size++] = o;
    }

    private void ensureCapacity(int capacity) {
        if (elements.length < capacity) {
            Object[] e = new Object[capacity * 2];
            System.arraycopy(elements, 0, e, 0, size);
            elements = e;
        }
    }

    protected Object popImpl() {
        Object ret = elements[size - 1];
        elements[size - 1] = null;
        return ret;
    }
}
