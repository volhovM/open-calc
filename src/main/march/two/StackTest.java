package main.march.two;

/**
 * Created by volhovm on 3/13/14.
 */
public class StackTest {
    public static void main(String[] args) {
        test(new LinkedStack());
        test(new ArrayStack());
    }

    private static void test(Stack stack) {
        fill(stack, 10000);
        dump(stack);
    }

    private static void dump(Stack stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            System.out.println(stack.pop());
        }
    }

    private static void fill(Stack stack, int to) {
        for (int i = 0; i < to; i++) {
            stack.push(i);
        }
    }
}
