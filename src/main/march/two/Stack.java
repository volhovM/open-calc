package main.march.two;

/**
 * Created by volhovm on 3/13/14.
 */
public interface Stack {
    public void push(Object e);

    public Object peek();

    public Object pop();

    public int size();

    public boolean isEmpty();
}
