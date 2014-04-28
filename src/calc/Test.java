package calc;

import calc.parser.ExpressionParser;
import calc.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(ExpressionParser.parseAndEval("-d", "10000/2/4^2"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
