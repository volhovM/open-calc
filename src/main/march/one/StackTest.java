package main.march.one;

/**
 * Created by volhovm on 3/6/14.
 */
public class StackTest {
    public static void main(String[] args) {
        ArrayStackObject stack1 = new ArrayStackObject();
        for (int i = 0; i < 500; i++) {
            ArrayStackModule.push(i);
        }
        for (int i = 0; !ArrayStackModule.isEmpty(); i++) {
            System.out.println(ArrayStackModule.pop());
        }
    }
}
