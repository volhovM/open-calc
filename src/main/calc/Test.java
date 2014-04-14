package main.calc;

import main.calc.calclib.Expression3;
import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) throws ParseException {
        //        Expression3 expression =
        //            ExpressionParser.parse("2^(x^2 + 2 * x^2 + (2 + 3 + 4)* y ^3 + 10*y^(7 - 4)
        // )");
        //        expression = Simplifier.simplify(expression);
        //        System.out.println(expression.toString());
        Expression3 expression = ExpressionParser.parse("100000*x*x*x*x*x*x/(x-1)");
        System.out.println(ElevenTest.evaluate(expression));
    }
}
