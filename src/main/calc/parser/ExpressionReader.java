package main.calc.parser;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class ExpressionReader {
    private int position;
    private String string;

    ExpressionReader(String string) {
        this.string = string;
        position = 0;
    }

    public String getString() {
        return string;
    }

    public int getPosition() {
        return position;
    }

    private class Pair {
        String str;
        int value;

        private Pair(String str, int value) {
            this.str = str;
            this.value = value;
        }
    }

    private Pair getToken() throws ParseException {
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
        if (c == 'a' && string.charAt(i + 1) == 'b') {
            return new Pair(" abs ", i + 2);
        }
        if (Character.isLowerCase(c) && Character.isAlphabetic(c)) {
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
        //        return new Pair("EOF", 0);
        throw new ParseException("Wrong symbol '" + c + "' while parsing " + string + "at " +
                                     "position " + position);
    }

    String next() throws ParseException {
        return getToken().str;
    }

    void consume() throws ParseException {
        position = getToken().value + 1;
    }


}
