package main.calc;

import main.calc.calclib.Expression;
import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;
import main.calc.simplify.Simplifier;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) throws ParseException {
        Expression expression =
            ExpressionParser.parse("x^2 + 1 * x^2 + x^(3-1) / 1 + (2 + 3 + 4)* y ^3 + 10*y^(7 - " +
                                       "4)");
        expression = Simplifier.simplify(expression);
        System.out.println(expression.toString());
    }
}
