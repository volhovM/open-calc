package study.march.two;

/**
 * Created by volhovm on 3/13/14.
 */
public abstract class AbstactStack implements Stack {
    protected int size;


    public Object pop() {
        assert size > 0; //enabled if -ea
        Object ret = popImpl();
        size--;
        return ret;
    }

    protected abstract Object popImpl();

    public Object peek() {
        Object element = pop();
        push(element);
        return element;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
