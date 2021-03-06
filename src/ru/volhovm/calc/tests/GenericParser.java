package ru.volhovm.calc.tests;

import ru.volhovm.calc.calclib.Expression;
import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcBigInteger;
import ru.volhovm.calc.calclib.numsystems.CalcDouble;
import ru.volhovm.calc.calclib.numsystems.CalcInteger;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;
import ru.volhovm.calc.calclib.parser.ExpressionParser;
import ru.volhovm.calc.calclib.parser.ParseException;

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

    private static String evaluate(String type, String string) {
        try {
            if (type.equals("-i"))
                return evaluate(new ExpressionParser<CalcInteger>().parse(new CalcInteger(0), string), new CalcInteger(0));
            if (type.equals("-d")) return evaluate(new ExpressionParser<CalcDouble>().parse(
                    new CalcDouble(0), string), new CalcDouble(0));
            if (type.equals("-bi")) return evaluate(new ExpressionParser<CalcBigInteger>().parse(
                    new CalcBigInteger(BigInteger.ZERO), string), new CalcBigInteger(BigInteger.ZERO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    private static <T extends CalcNumerable<T>> String evaluate(Expression<T> expression, T type) {
        StringBuilder ret = new StringBuilder();
        for (int i = -100; i <= 100; i++) {
            for (int j = -100; j <= 100; j++) {
                try {
                    //noinspection unchecked
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
