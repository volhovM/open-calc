package calc;

import calc.calclib.Expression3;
import calc.calclib.exceptions.CalcException;
import calc.parser.ExpressionParser;
import calc.parser.ParseException;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class ElevenTest {
    private ElevenTest() {}

    public static String evaluate(String string) {
        try {
            return evaluate(ExpressionParser.parse(string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String evaluate(Expression3 expression) {
        StringBuilder ret = new StringBuilder("x y z        f");
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    ret.append("\r\n")
                        .append(i)
                        .append(" ")
                        .append(j)
                        .append(" ")
                        .append(k)
                        .append("        ");
                    try {
                        ret.append(expression.evaluate(i, j, k));
                    } catch (CalcException exc) {
                        ret.append(exc.getShortMsg());
                    }
                }
            }
        }
        return ret.toString();
    }
}
