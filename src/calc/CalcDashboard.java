package calc;

import calc.calclib.Expression;
import calc.calclib.numsystems.CalcBigInteger;
import calc.calclib.numsystems.CalcDouble;
import calc.calclib.numsystems.CalcInteger;
import calc.calclib.parser.ExpressionParser;
import calc.calclib.parser.ParseException;

import java.util.Arrays;

/**
 * @author volhovm
 *         Created on 03.05.14
 */
@SuppressWarnings("WeakerAccess")
public class CalcDashboard {
    private CalcDashboard() {
    }

    public static Number
    parseAndEval(String type, String expression, Number... args) throws ParseException {
        switch (type) {
            case "-i":
                Expression<CalcInteger> expI = new ExpressionParser<CalcInteger>().parse(new CalcInteger(0), expression);
                if (args.length == 0) return expI.evaluate().toInteger();
                return expI.evaluate(
                        Arrays.asList(args)
                                .stream()
                                .map(a -> a == null ? null : new CalcInteger(a.intValue()))
                                .toArray(CalcInteger[]::new)
                )
                        .toInteger();
            case "-d":
                Expression<CalcDouble> expD = new ExpressionParser<CalcDouble>().parse(new CalcDouble(0), expression);
//                if (args.length == 0) return expD.evaluate().toDouble();
                return expD.evaluate(
                        Arrays.asList(args)
                                .stream()
                                .map(a -> a == null ? null : new CalcDouble(a.doubleValue()))
                                .toArray(CalcDouble[]::new)
                )
                        .toDouble();
            case "-bi":
                Expression<CalcBigInteger> expBI = new ExpressionParser<CalcBigInteger>().parse(new CalcBigInteger(0), expression);
                if (args.length == 0) return expBI.evaluate().toBigInt();
                return expBI.evaluate(
                        Arrays.asList(args)
                                .stream()
                                .map(a -> a == null ? null : new CalcBigInteger(a.intValue()))
                                .toArray(CalcBigInteger[]::new)
                )
                        .toBigInt();
            default:
                throw new ParseException("Wrong type: " + type);
        }
    }


}
