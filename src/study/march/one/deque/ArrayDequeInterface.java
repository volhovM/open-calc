package study.march.one.deque;

/**
 * Created by volhovm on 3/10/14.
 */
public interface ArrayDequeInterface {
    //    State: N, a_1...a_n
    //    Inv: N >= 0
    //    Methods:

    //    pre: -
    void addFirst(Object o);
    //    post: size = size' + 1 & a_size = o & a_i = (a_(i-1))' in i = 1..size' && a_1 = o

    //    pre: -
    void addLast(Object o);
    //    post: size = size' + 1 & a_size = o & a_i = (a_i)' in i = 1..size' && a_n = o

    //    pre: size > 0
    Object removeFirst();
    //    post: return = a_1 & size = size' - 1 & a_i = (a_(i+1))' in 1..size'

    //    pre: size > 0
    Object removeLast();
    //    post: return = a_n & size = size' - 1 & a_i = (a_i)' in 1..size

    //    pre: size > 0
    Object peekFirst();
    //    post: return = a_1 & size = size'

    //    pre: size > 0
    Object peekLast();
    //    post: return = a_n & size = size'

    //    pre: -
    boolean isEmpty();
    //    post: return = (size == 0)

    //    pre: -
    int size();
    //    post: return = N
}
