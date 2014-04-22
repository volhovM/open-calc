package calc;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
//        assert args.length > 1;
//        StringBuilder s = new StringBuilder();
//        for (int i = 1; i < args.length; i++) {
//            s.append(args[i]);
//        }
        System.out.println(GenericParser.evaluate("-i", "x ^ y ^ y + 2 * x + 6 * 8 - 48 / y"));

        //        Expression3 expression =
        //            ExpressionParser.parse("2^(x^2 + 2 * x^2 + (2 + 3 + 4)* y ^3 + 10*y^(7 - 4)
        // )");
        //        expression = SimplifierBinary.simplify(expression);
        //        System.out.println(expression.toString());

        //        try {
        //            System.out.println(GenericParser.evaluate("100000*x*x*x*x*x*x/(x-1)"));
        //        } catch (Exception exc){
        //            System.err.println("Some unknown exception appeared:");
        //            exc.printStackTrace();
        //        }

    }
}
