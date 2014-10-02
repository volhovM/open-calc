package study.march.java8;

/**
 * Created by volhovm on 3/19/14.
 */
public class test {
    public static void main(String[] args) {
        //        LinkedList<Triple> list = new LinkedList<>();
        //        for (int i = 1000; i > 0; i--) {
        //            list.add(new Triple(String.valueOf(i), i * 2, i * 3));
        //        }
        //        //        Collections.sort(list, (a, b) -> a.getA().compareTo(b.getA()));
        //        list.stream().sorted((a,
        //                              b) -> a.getA().compareTo(b.getA())).filter(a -> a.getA()
        // .startsWith(
        //            "12")).forEach((t) -> System.out.println(t.getA()));
        try {
            int a = 4 / 0;
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ignored) {
        } finally {
            System.out.println("test");
        }
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