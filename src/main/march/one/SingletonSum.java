package main.march.one;

/**
 * Created by volhovm on 3/10/14.
 */
public class SingletonSum {
    public static void main(String[] args) {
        for (String arg : args) {
            String[] a = arg.split("\\s+");
            for (String current : a) {
                if (!current.equals("")) {
                    ArrayQueueSingleton.push(current);
                }
            }
        }
        long sum = 0;
        while (!ArrayQueueSingleton.isEmpty()) {
            sum += Long.parseLong((String) ArrayQueueSingleton.pop());
        }
        System.out.println(String.valueOf(sum));
    }
}
