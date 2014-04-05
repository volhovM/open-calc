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

    private static class Reader {
        int position;
        String string;

        private Reader(String string) {
            this.string = string;
            position = 0;
        }

        private class Pair {
            String str;
            int value;

            private Pair(String str, int value) {
                this.str = str;
                this.value = value;
            }
        }

        private Pair getToken() {
            if (position >= string.length()) {
                return new Pair("EOF", 0);
            }
            int i;
            for (i = position; i < string.length() && Character.isWhitespace(string.charAt(i));
                 i++) {
                position++;
            }
            Character c = string.charAt(position);
            if (c == ')' || c == '(') {
                return new Pair(c.toString(), i);
            }
            if (c == '+') {
                return new Pair(" + ", i);
            }
            if (c == '-') {
                return new Pair(" - ", i);
            }
            if (c == '/') {
                return new Pair(" / ", i);
            }
            if (c == '*') {
                return new Pair(" * ", i);
            }
            if (c == '~') {
                return new Pair(" ~ ", i);
            }
            if (c == '^') {
                return new Pair(" ^ ", i);
            }
            if (c == 'a') {
                return new Pair(" abs ", i + 2);
            }
            if (c == 'x' || c == 'y' || c == 'z') {
                return new Pair(c.toString(), i);
            }
            if (Character.isDigit(c)) {
                String number = "";
                int j;
                for (j = i; j < string.length() && Character.isDigit(string.charAt(j)); j++) {
                    number += string.charAt(j);
                }
                return new Pair(number, j - 1);
            }
            return new Pair("EOF", 0);
        }

        private String next() {
            return getToken().str;
        }

        private void consume() {
            position = getToken().value + 1;
        }
    }

    public static Expression parse(String expression) throws ParseException {
        Reader reader = new Reader(expression.trim());
        return firstLevel(reader);
    }

    private static Expression firstLevel(Reader reader) throws ParseException {
        Expression ret = secondLevel(reader);
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

    private static Expression secondLevel(Reader reader) throws ParseException {
        Expression ret = thirdLevel(reader);
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

    private static Expression thirdLevel(Reader reader) throws ParseException {
        Expression ret = fourthLevel(reader);
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

    private static Expression fourthLevel(Reader reader) throws ParseException {
        String s = reader.next();
        Expression ret = null;
        if (s.equals("z") || s.equals("x") || s.equals("y")) {
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
            throw new ParseException("Some strange symbol: " + s + " while parsing " + reader
                .position);
        }
        return ret;
    }
}
