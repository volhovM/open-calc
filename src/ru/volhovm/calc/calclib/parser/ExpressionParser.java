package ru.volhovm.calc.calclib.parser;

import ru.volhovm.calc.calclib.*;
import ru.volhovm.calc.calclib.numsystems.CalcBigInteger;
import ru.volhovm.calc.calclib.numsystems.CalcDouble;
import ru.volhovm.calc.calclib.numsystems.CalcInteger;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;

/**
 * @author volhovm
 */

public class ExpressionParser<T extends CalcNumerable<T>> {
    //A -> B {("+" | "-") B}
    //B -> C {("*" | "/") C}
    //C -> D ["^" (D | C)]
    //D -> "lb" C | "abs" C | "~" C | "-" C | variable | const | "(" C ")"

//    private ExpressionParser(){}

    public Expression<T>
    parse(T type, String expression) throws ParseException {
        ExpressionReader reader = new ExpressionReader(expression.trim());
        Expression<T> ret = firstLevel(type, reader);
        if (reader.getPosition() != reader.getString().length())
            System.err.println("Pay attention! Characters were parsed: " + reader.getPosition());
        return ret;
    }


    public static Expression parse(String type, String expression) throws ParseException {
        switch (type) {
            case "-i":
                return new ExpressionParser<CalcInteger>().parse(new CalcInteger(0), expression);
            case "-d":
                return new ExpressionParser<CalcDouble>().parse(new CalcDouble(0), expression);
            case "-bi":
                return new ExpressionParser<CalcBigInteger>().parse(new CalcBigInteger(0), expression);
            default:
                throw new ParseException("Wrong type: " + type);
        }
    }


    @SuppressWarnings("Convert2Diamond")
    private Expression<T>
    firstLevel(T type, ExpressionReader reader) throws ParseException {
        boolean isTypeDouble = type.getClass() == CalcDouble.class;
        Add<T> ret = new Add<T>(isTypeDouble ? normalSecondLevel(type, reader) : primitiveSecondLevel(type, reader));
        String s = reader.next();
        while (s.equals(" + ") || s.equals(" - ")) {
            reader.consume();
            switch (s) {
                case " + ":
                    ret.addArgument(isTypeDouble ? normalSecondLevel(type, reader) : primitiveSecondLevel(type, reader));
                    break;
                case " - ":
                    ret.addArgument(new UnaryMin<T>(isTypeDouble ? normalSecondLevel(type, reader) : primitiveSecondLevel(type, reader)));
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
    private Expression<T>
    normalSecondLevel(T type, ExpressionReader reader) throws ParseException {
        Multiply<T> ret = new Multiply<T>(thirdLevel(type, reader));
        String s = reader.next();
        while (s.equals(" * ") || s.equals(" / ")) {
            reader.consume();
            switch (s) {
                case " * ":
                    ret.addArgument(new Power<T>(thirdLevel(type, reader), new Const<T>(type.parse("1"))));
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
    private Expression<T>
    primitiveSecondLevel(T type, ExpressionReader reader) throws ParseException {
        Expression<T> ret = thirdLevel(type, reader);
        String s = reader.next();
        while (s.equals(" * ") || s.equals(" / ")) {
            reader.consume();
            switch (s) {
                case " * ":
                    ret = new Multiply<T>(ret, thirdLevel(type, reader));
                    break;
                case " / ":
                    ret = new Divide<T>(ret, thirdLevel(type, reader));
                    break;
            }
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret;
    }

    @SuppressWarnings("Convert2Diamond")
    private Expression<T>
    thirdLevel(T type, ExpressionReader reader) throws ParseException {
        Expression<T> ret = fourthLevel(type, reader);
        String s = reader.next();
        while (s.equals(" ^ ")) {
            reader.consume();
            ret = new Power<T>(ret, thirdLevel(type, reader));
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret;
    }

    @SuppressWarnings("Convert2Diamond")
    private Expression<T>
    fourthLevel(T type, ExpressionReader reader) throws ParseException {
        String s = reader.next();
        Expression<T> ret;
        if (s.equals("abs")) {
            reader.consume();
            ret = new Abs<T>(fourthLevel(type, reader));
        } else if (s.equals("lb")) {
            reader.consume();
            ret = new BinaryLog<T>(fourthLevel(type, reader));
        } else if (s.length() == 1 && Character.isLowerCase(s.charAt(0)) &&
                Character.isAlphabetic(s.charAt(0))) {
            reader.consume();
            ret = new Variable<T>(s.charAt(0));
        } else if (Character.isDigit(s.charAt(0))) {
            reader.consume();
            ret = new Const<T>(type.parse(s));
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
            throw new ParseException("Symbol in a wrong place: '" + s + "' while parsing '" +
                    reader.getString() + "' at position " + reader
                    .getPosition());
        }
        return ret;
    }
}
