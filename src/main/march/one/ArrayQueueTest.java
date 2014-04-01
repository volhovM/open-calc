package main.march.one;

/**
 * Created by volhovm on 3/6/14.
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        //--------------------------SINGLETON--------------------------

        for (int i = 0; i < 30; i++) {
            ArrayQueueSingleton.push(i);
        }
        while (!ArrayQueueSingleton.isEmpty()) {
            System.out.println(ArrayQueueSingleton.pop());
        }

        //-----------------------------ADT------------------------------

        ArrayQueueADT q1 = new ArrayQueueADT();
        ArrayQueueADT q2 = new ArrayQueueADT();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                ArrayQueueADT.push(q1, i);
            } else {
                ArrayQueueADT.push(q2, i);
            }
        }
        while (!ArrayQueueADT.isEmpty(q2)) {
            System.out.println(ArrayQueueADT.pop(q2));
        }

        //---------------------------OBJECT-----------------------------

        ArrayQueue oq1 = new ArrayQueue();
        ArrayQueue oq2 = new ArrayQueue();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                oq1.push(i);
            } else {
                oq2.push(i);
            }
        }
        while (!oq2.isEmpty()) {
            System.out.println(oq2.pop());
        }
    }
}
