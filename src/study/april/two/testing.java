package study.april.two;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author volhovm
 *         Created on 10.04.14
 */


public class testing {
    public static void main(String[] args) {

        //copy(new ArrayList<String>(), new ArrayList<Integer>());
        swapImpl(new ArrayList<Integer>(), 2, 3);
    }

    static public <T> void dump(T[] a, Collection<T> c) {
        c.add(a[0]);
    }

    static public <T> void copy(ArrayList<T> a, ArrayList<T> b) {
        b.add(a.get(0));
    }

    private static <T> void swapImpl(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
