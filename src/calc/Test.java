package calc;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
        //        Expression3 expression =
        //            ExpressionParser.parse("2^(x^2 + 2 * x^2 + (2 + 3 + 4)* y ^3 + 10*y^(7 - 4)
        // )");
        //        expression = Simplifier.simplify(expression);
        //        System.out.println(expression.toString());

        //        try {
        //            System.out.println(ElevenTest.evaluate("100000*x*x*x*x*x*x/(x-1)"));
        //        } catch (Exception exc){
        //            System.err.println("Some unknown exception appeared:");
        //            exc.printStackTrace();
        //        }

        System.out.println(ElevenTest.evaluate("lb(2000000000000))"));
    }
}
