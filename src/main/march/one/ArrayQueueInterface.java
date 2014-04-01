package main.march.one;

/**
 * Created by volhovm on 3/7/14.
 */
public interface ArrayQueueInterface {
    //    State: N, a_1...a_n
    //    Methods:

    //    pre: -
    void push(Object o);
    //    post: size = size' + 1 & a_size = o & a_i = (a_i)' in i = 1..size'

    //    pre: size > 0
    Object pop();
    //    post: return = a_1 & size = size' - 1 & a_i = (a_(i+1))' in 1..size'

    //    pre: size > 0
    Object peek();
    //    post: return = a_1 & size = size'

    //    pre: -
    boolean isEmpty();
    //    post: return = (size == 0)

    //    pre: -
    int size();
    //    post: return = N
}
