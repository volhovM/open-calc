package main.calc;

import main.calc.calclib.Expression3;
import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) throws ParseException {
        Expression3 expression = ExpressionParser.parse("(1 + 3 - 2)/x + 1/y + z/1");
        System.out.println(expression.toString());
    }
}
