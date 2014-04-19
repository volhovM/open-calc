package study.march.three;

/**
 * Created by volhovm on 3/20/14.
 */
public class A {
    public static void main(String[] args) {
        byte a = 0;
        short b = 1;
        int c = 0b0001010101;
        long d = 30_000_000_000_000L;
        long f = 0xA125_DEAD;
        Byte aa = a;
        Short bb = b;
        Integer cc = c;
        Long dd = d;
        Long x1 = 12332536234634752L;
        Long x2 = x1 - 1; //Long x2 = Long.valueOf(x1.longValue() - 1)
        Long x3 = x2 + 1;
        System.err.println(x1 == x3); //false
        System.err.println(x1.equals(x3)); //true

        double xx = 010.53253; //10.532
        double xy = 010; //8
        System.err.println(-Double.MAX_VALUE / 2 * 2);
        System.err.println(-Double.MAX_VALUE * 2 / 2);

        Character ch = '2';

        Boolean bo = Boolean.TRUE;

        int test = 5;
        test += 12512412635L;
        //test = test + 1231253623L; не скомпилится

        Integer zzz = 0;
        System.out.println(zzz instanceof Number);

        fori:
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 0x3623ABAB; j++) {
                break fori;
            }
        }

        if (false) { //Единственная конструкция, не подверженная компиляторной проверке на
        // недостижимый код
            System.out.println();
        }
    }
}
