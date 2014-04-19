package study.march.one.deque;

/**
 * Created by volhovm on 3/11/14.
 */
public class SumSquares {
    public static void main(String[] args) {
        ArrayDeque deque = new ArrayDeque();
        deque.addFirst(2D);
        deque.addFirst(3D);
        System.out.println(getSquares(deque));
    }

    public static Double getSquares(ArrayDeque deque) {
        double ret = 0;
        for (int i = 0; i < deque.size(); i++) {
            Double got = (Double) deque.removeFirst();
            ret += got * got;
            deque.addLast(got);
        }
        return ret;
    }
}
