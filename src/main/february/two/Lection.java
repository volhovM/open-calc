package main.february.two;

/**
 * Created by volhovm on 2/27/14.
 */
public class Lection {
    // Pre: b >= 0
    // Post: result == a ^ b
    long power(long a, long b) {
        // b' >= 0
        long r = 1;
        // b' >= 0

        // inv: r * a ^ b == a' ^ b'
        while (b != 0) {
            // r * a ^ b == a' ^ b' && b > 0
            if (b % 2 == 1) {
                //r * a ^ b == a' ^ b' -- нечетное
                r *= a;
                b--;
                // r * a ^ b == a' ^ b' && b -- четное
            } else {
                // r * a ^ b == a' ^ b' && b -- четное
            }
            // r * a ^ b == a' ^ b' && b -- четное
            b /= 2;
            a *= a;
            // r * a ^ b == a' ^ b'
        }
        // r  == a' ^ b'
        return r;
        // result  == a' ^ b'
    }


    int a;

    int increment(int b) { // нечистая функция
        a += b;
        return a;
    }

    public static void main(String[] args) {
        Lection s = new Lection();
        System.out.println(s.power(2, 3));
    }
}
