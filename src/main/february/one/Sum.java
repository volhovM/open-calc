package main.february.one;

/**
 * Created by volhovm on 2/14/14. Homework 1, ful
 */
public class Sum {
    public static void main(String[] args) throws Exception {
        System.out.println(solution1(args));
    }

    public static short solution1(String[] args) throws Exception {
        short sum = 0;
        for (String arg : args) {
            String[] a = arg.split("\\s+");
            for (String anA : a) {
                if (!anA.equals("")) {
                    if (Character.toLowerCase(anA.charAt(0)) == 'q' || (anA.length() > 1 && (Character.toLowerCase(
                        anA.charAt(1)) == 'q'))) {
                        boolean neg = false;
                        if (anA.charAt(0) == '-') {
                            anA = anA.substring(2);
                            neg = true;
                        } else {
                            anA = anA.substring(1);
                        }
                        if (anA.charAt(0) == '-') {
                            throw new Exception("Numbers like q-33421 are not allowed");
                        }
                        sum += neg ? (short) Integer.parseInt("-" + anA,
                                                              4) : (short) Integer.parseInt(anA, 4);

                    } else {
                        sum += (short) Integer.parseInt(anA);
                    }
                }
            }
        }
        return sum;
    }

    //    public static short decodeQ(String qNum) {
    //        short result = 0;
    //        boolean isNeg;
    //        if (qNum.charAt(0) == '-'){
    //            isNeg = true;
    //            qNum = qNum.substring(1);
    //        }
    //        qNum = qNum.substring(1);
    //        for (int i = 0; i < qNum.length(); i++) {
    //            result +=
    //        }
    //    }


    ////Doesn't work
    //    public static int solution2(String[] args) {
    //        int sum = 0;
    //        for (String arg : args) {
    //            for (int i = 0; i < arg.length(); i++) {
    //                if (!Character.isDigit(arg.charAt(i)) && arg.charAt(i) != '-') { //isWhiteSpace(...) maybe?
    //                    sum += Integer.parseInt(arg.substring(0, i));
    //                    if (i != arg.length() - 1) {
    //                        arg = arg.substring(i + 1);
    //                        i = 0;
    //                    }
    //                }
    //                if (i == arg.length() - 1) sum += Integer.parseInt(arg);
    //            }
    //        }
    //        return sum;
    //    }
}
