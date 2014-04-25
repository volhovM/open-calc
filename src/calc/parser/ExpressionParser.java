package calc.parser;

import calc.calclib.*;
import calc.calclib.numsystems.CalcBigInteger;
import calc.calclib.numsystems.CalcDouble;
import calc.calclib.numsystems.CalcInt;
import calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class ExpressionParser {
    //A -> B {("+" | "-") B}
    //B -> C {("*" | "/") C}
    //C -> D ["^" (D | C)]
    //D -> "lb" C | "abs" C | "~" C | "-" C | variable | const | "(" C ")"
    private ExpressionParser() {
    }


    public static <T extends CalcNumerable<T>> Expression<T>
    parse(T type, String expression) throws ParseException {
        ExpressionReader reader = new ExpressionReader(expression.trim());
        return firstLevel(type, reader);
    }


    public static Number
    parseAndEval(String type, String expression, Number a, Number b, Number c) throws ParseException {
        switch (type) {
            case "-i":
                Expression<CalcInt> expI = ExpressionParser.parse(new CalcInt(0), expression);
                return expI.evaluate(
                        new CalcInt(a.intValue()), new CalcInt(b.intValue()), new CalcInt(c.intValue())).toInteger();
            case "-d":
                Expression<CalcDouble> expD = ExpressionParser.parse(new CalcDouble(0), expression);
                return expD.evaluate(
                        new CalcDouble(a.doubleValue()), new CalcDouble(b.doubleValue()), new CalcDouble(c.doubleValue())).toDouble();
            case "-bi":
                Expression<CalcBigInteger> exp = ExpressionParser.parse(new CalcBigInteger(0), expression);
                return exp.evaluate(new CalcBigInteger(a.intValue()), new CalcBigInteger(b.intValue()), new CalcBigInteger(c.intValue())).toBigInt();
            default:
                throw new ParseException("Wrong type: " + type);
        }
    }

    public static Expression parse(String type, String expression) throws ParseException {
        switch (type) {
            case "-i":
                return ExpressionParser.parse(new CalcInt(0), expression);
            case "-d":
                return ExpressionParser.parse(new CalcDouble(0), expression);
            case "-bi":
                return ExpressionParser.parse(new CalcBigInteger(0), expression);
            default:
                throw new ParseException("Wrong type: " + type);
        }
    }


    @SuppressWarnings("Convert2Diamond")
    private static <T extends CalcNumerable<T>> Expression<T>
    firstLevel(T type, ExpressionReader reader) throws ParseException {
        Add<T> ret = new Add<T>(secondLevel(type, reader));
        String s = reader.next();
        while (s.equals(" + ") || s.equals(" - ")) {
            reader.consume();
            switch (s) {
                case " + ":
                    ret.addArgument(secondLevel(type, reader));
                    break;
                case " - ":
                    ret.addArgument(new UnaryMin<T>(secondLevel(type, reader)));
                    break;
            }
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret.size() > 1 ? ret : ret.getOnlyArgument();
    }

    @SuppressWarnings("Convert2Diamond")
    private static <T extends CalcNumerable<T>> Expression<T>
    secondLevel(T type, ExpressionReader reader) throws ParseException {
        Multiply<T> ret = new Multiply<T>(thirdLevel(type, reader));
        String s = reader.next();
        while (s.equals(" * ") || s.equals(" / ")) {
            reader.consume();
            switch (s) {
                case " * ":
                    ret.addArgument(thirdLevel(type, reader));
                    break;
                case " / ":
                    ret.addArgument(new Power<T>(thirdLevel(type, reader), new UnaryMin<T>(new Const<T>(type.parse("1")))));
                    break;
            }
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret.size() > 1 ? ret : ret.getOnlyArgument();
    }

    @SuppressWarnings("Convert2Diamond")
    private static <T extends CalcNumerable<T>> Expression<T>
    thirdLevel(T type, ExpressionReader reader) throws ParseException {
        Power<T> ret = new Power<T>(fourthLevel(type, reader));
        String s = reader.next();
        while (s.equals(" ^ ")) {
            reader.consume();
            ret.addArgument(thirdLevel(type, reader));
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret.size() > 1 ? ret : ret.getOnlyArgument();
    }

    @SuppressWarnings("Convert2Diamond")
    private static <T extends CalcNumerable<T>> Expression<T>
    fourthLevel(T type, ExpressionReader reader) throws ParseException {
        String s = reader.next();
        Expression<T> ret;
        if (s.equals(" abs ")) {
            reader.consume();
            ret = new Abs<>(fourthLevel(type, reader));
        } else if (s.equals(" lb ")) {
            reader.consume();
            ret = new BinaryLog<>(fourthLevel(type, reader));
        } else if (s.length() == 1 && Character.isLowerCase(s.charAt(0)) &&
                Character.isAlphabetic(s.charAt(0))) {
            reader.consume();
            ret = new Variable<>(s.charAt(0));
        } else if (Character.isDigit(s.charAt(0))) {
            reader.consume();
            ret = new Const<>(type.parse(s));
        } else if (s.equals("(")) {
            reader.consume();
            ret = firstLevel(type, reader);
            if (!reader.next().equals(")")) {
                throw new ParseException("stuff", ") -- bracket is not closed.");
            } else {
                reader.consume();
            }
        } else if (s.equals(" - ")) {
            reader.consume();
            ret = new UnaryMin<T>(fourthLevel(type, reader));
        } else if (s.equals(" ~ ")) {
            reader.consume();
            ret = new Not<T>(fourthLevel(type, reader));
        } else if (Character.isAlphabetic(s.charAt(0)) && Character.isLowerCase(s.charAt(0))) {
            reader.consume();
            ret = new AbstractFunction<T>(s, type, fourthLevel(type, reader));
        } else {
            throw new ParseException("Symbol in a wrong place: '" + s + "' while parsing " +
                    reader.getString() + " at position " + reader
                    .getPosition());
        }
        return ret;
    }
}
