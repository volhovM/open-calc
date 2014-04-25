package calc;

import calc.parser.ExpressionParser;
import calc.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(ExpressionParser.parseAndEval("-i", "factorial(x + y + z)", 2, 2, 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
