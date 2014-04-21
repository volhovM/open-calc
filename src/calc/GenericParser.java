package calc;

import calc.calclib.Expression3;
import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcBigInteger;
import calc.calclib.numsystems.CalcDouble;
import calc.calclib.numsystems.CalcInt;
import calc.calclib.numsystems.CalcNumerable;
import calc.parser.ExpressionParser;
import calc.parser.ParseException;

import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


class GenericParser {
    private GenericParser() {
    }

    public static void main(String[] args) {
        System.out.println(evaluate(args[0], args[1]));
    }

    public static String evaluate(String type, String string) {
        try {
            if (type.equals("-i")) return evaluate(ExpressionParser.parse(
                    new CalcInt(0), string), new CalcInt(0));
            if (type.equals("-d")) return evaluate(ExpressionParser.parse(
                    new CalcDouble(0), string), new CalcDouble(0));
            if (type.equals("-bi")) return evaluate(ExpressionParser.parse(
                    new CalcBigInteger(BigInteger.ZERO), string), new CalcBigInteger(BigInteger.ZERO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T extends CalcNumerable<T>> String evaluate(Expression3<T> expression, T type) {
        StringBuilder ret = new StringBuilder();
        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
                try {
                    ret.append(expression.evaluate(
                            type.parse(String.valueOf(i)), type.parse(String.valueOf(j)), type.parse(String.valueOf(0))));
                    ret.append("\r\n");
                } catch (CalcException exc) {
                    ret.append("error\r\n");
                }
            }
        }
        return ret.toString();
    }
}
