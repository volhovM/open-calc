package main.march.one;

/**
 * Created by volhovm on 3/10/14.
 */
public class Sum {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue();
        for (String arg : args) {
            String[] a = arg.split("\\s+");
            for (String current : a) {
                if (!current.equals("")) {
                    queue.push(current);
                }
            }
        }
        long sum = 0;
        while (!queue.isEmpty()) {
            sum += Long.parseLong((String) queue.pop());
        }
        System.out.println(String.valueOf(sum));
    }
}
