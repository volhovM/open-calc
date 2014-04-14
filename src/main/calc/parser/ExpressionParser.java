package main.calc.parser;

import main.calc.calclib.*;

/**
 * @author volhovm
 */

public class ExpressionParser {
    //A -> B {("+" | "-") B}
    //B -> C {("*" | "/") C}
    //C -> D ["^" (D | C)]
    //D -> "abs" C | "~" C | "-" C | variable | const | "(" C ")"
    // --NO POWER COMPOSITION ALLOWED


    public static Expression3 parse(String expression) throws ParseException {
        ExpressionReader reader = new ExpressionReader(expression.trim());
        return firstLevel(reader);
    }

    private static Expression3 firstLevel(ExpressionReader reader) throws ParseException {
        Expression3 ret = secondLevel(reader);
        String s = reader.next();
        while (s.equals(" + ") || s.equals(" - ")) {
            reader.consume();
            switch (s) {
                case " + ":
                    ret = new Add(ret, secondLevel(reader));
                    break;
                case " - ":
                    ret = new Subtract(ret, secondLevel(reader));
                    break;
            }
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret;
    }

    private static Expression3 secondLevel(ExpressionReader reader) throws ParseException {
        Expression3 ret = thirdLevel(reader);
        String s = reader.next();
        while (s.equals(" * ") || s.equals(" / ")) {
            reader.consume();
            switch (s) {
                case " * ":
                    ret = new Multiply(ret, thirdLevel(reader));
                    break;
                case " / ":
                    ret = new Divide(ret, thirdLevel(reader));
                    break;
            }
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret;
    }

    private static Expression3 thirdLevel(ExpressionReader reader) throws ParseException {
        Expression3 ret = fourthLevel(reader);
        String s = reader.next();
        while (s.equals(" ^ ")) {
            reader.consume();
            ret = new Power(ret, thirdLevel(reader));
            s = reader.next();
            if (s.equals("EOF")) {
                break;
            }
        }
        return ret;
    }

    private static Expression3 fourthLevel(ExpressionReader reader) throws ParseException {
        String s = reader.next();
        Expression3 ret = null;
        if (s.length() == 1 && Character.isLowerCase(s.charAt(0)) && Character.isAlphabetic(s.charAt(
            0))) {
            reader.consume();
            ret = new Variable(s);
        } else if (Character.isDigit(s.charAt(0))) {
            reader.consume();
            ret = new Const((int) Long.parseLong(s));
        } else if (s.equals("(")) {
            reader.consume();
            ret = firstLevel(reader);
            if (!reader.next().equals(")")) {
                throw new ParseException("stuff", ") -- bracket is not closed.");
            } else {
                reader.consume();
            }
        } else if (s.equals(" - ")) {
            reader.consume();
            ret = new UnaryMin(fourthLevel(reader));
        } else if (s.equals(" ~ ")) {
            reader.consume();
            ret = new Not(fourthLevel(reader));
        } else if (s.equals(" abs ")) {
            reader.consume();
            ret = new Abs(fourthLevel(reader));
        } else {
            throw new ParseException("Symbol in a wrong place: '" + s + "' while parsing " +
                                         reader.getString() + " at position " + reader.getPosition());
        }
        return ret;
    }
}
