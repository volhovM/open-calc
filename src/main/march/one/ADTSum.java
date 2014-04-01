package main.march.one;

/**
 * Created by volhovm on 3/10/14.
 */
public class ADTSum {
    public static void main(String[] args) {
        ArrayQueueADT queue = new ArrayQueueADT();
        for (String arg : args) {
            String[] a = arg.split("\\s+");
            for (String current : a) {
                if (!current.equals("")) {
                    ArrayQueueADT.push(queue, current);
                }
            }
        }
        long sum = 0;
        while (!ArrayQueueADT.isEmpty(queue)) {
            sum += Long.parseLong((String) ArrayQueueADT.pop(queue));
        }
        System.out.println(String.valueOf(sum));
    }
}
