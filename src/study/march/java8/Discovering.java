package study.march.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by volhovm on 29.03.14.
 */
public class Discovering {
    public static void main(String[] args) {
        //        Thread s = new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                new Timer("myTimer", true).schedule(new TimerTask() {
        //                    @Override
        //                    public void run(){
        //                        System.out.println("Test!!");
        //                    }
        //                }, 600);
        //                try {
        //                    this.wait(1000);
        //                } catch (InterruptedException e) {
        //                    e.printStackTrace();
        //                }
        //            }
        //        });
        //        s.start();
        //        s.join();
        //        System.out.println("EOF");
        List<Person> z = new ArrayList<>();
        Random s = new Random();
        for (int i = 0; i < 1000; i++) {
            z.add(new Person(s.nextInt(100000), s.nextInt(50) + 20));
        }
        //        z.stream()
        //            .sorted((Integer a, Integer b) -> a.compareTo(b))
        //            .forEach(System.out::println);
        //        IntUnaryOperator oper = a -> a + 1;
        Map<Integer, List<Person>> map = z.stream()
            //            .map(String::valueOf)
            .collect(Collectors.groupingBy((Person a) -> a.getMoney() / 4));
    }

    private static class Person {
        int money;
        int age;

        private Person(int money, int age) {
            this.money = money;
            this.age = age;
        }

        public Integer getMoney() {
            return money;
        }
    }

    interface Foo {
        static <U> Function<U, U> identity() {
            return u -> u;
        }
    }
}

