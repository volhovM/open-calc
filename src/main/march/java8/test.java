package main.march.java8;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by volhovm on 3/19/14.
 */
public class test {
    public static void main(String[] args) {
        LinkedList<Triple> list = new LinkedList<>();
        for (int i = 1000; i > 0; i--) {
            list.add(new Triple(String.valueOf(i), i * 2, i * 3));
        }
        //        Collections.sort(list, (a, b) -> a.getA().compareTo(b.getA()));
        list.stream().sorted((a,
                              b) -> a.getA().compareTo(b.getA())).filter(a -> a.getA().startsWith(
            "12")).forEach((t) -> System.out.println(t.getA()));
    }

    static class Triple {
        private String a;
        private double b, c;

        Triple(String a, double b, double c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String getA() {
            return a;
        }
    }
}