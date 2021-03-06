package ru.volhovm.calc.calclib.parser;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


class ExpressionReader {
    private int position;
    private final String string;
    private Pair tempPair = null;

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
        final String str;
        final int value;

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
//        if (c == 'a' && string.charAt(i + 1) == 'b') {
//            return new Pair(" abs ", i + 2);
//        }
//        if (c == 'l' && string.charAt(i + 1) == 'b') {
//            return new Pair(" lb ", i + 1);
//        }
        if (Character.isLowerCase(c) && Character.isAlphabetic(c)) {
            StringBuilder stringBuilder = new StringBuilder();
            int j;
            for (j = i; j < string.length() && Character.isLowerCase(string.charAt(j)) && Character.isAlphabetic(string.charAt(j)); j++) {
                stringBuilder.append(string.charAt(j));
            }
            return new Pair(stringBuilder.toString(), j - 1);
        }
        if (Character.isDigit(c)) {
            String number = "";
            int j;
            boolean dotFound = false;
            boolean doubleExpFound = false;
            boolean minusAfterExpFound = false;
            for (j = i; j < string.length() &&
                    (Character.isDigit(string.charAt(j)) || (doubleExpFound && string.charAt(j) == '-')
                            || string.charAt(j) == 'e' || string.charAt(j) == 'E' || string.charAt(j) == '.'); j++) {
                if (string.charAt(j) == '.') {
                    if (!dotFound) dotFound = true;
                    else throw new ParseException("Redundant dot in number while parsing '" + string + "' at " +
                            "position " + j);
                }
                if (string.charAt(j) == 'e' || string.charAt(j) == 'E') {
                    if (!doubleExpFound) {
                        doubleExpFound = true;
                    } else throw new ParseException("Redundant exponent in number while parsing '" + string + "' at " +
                            "position " + j);
                }
                if (string.charAt(j) == '-') {
                    if (!minusAfterExpFound) {
                        minusAfterExpFound = true;
                    } else throw new ParseException("Redundant minus in number while parsing '" + string + "' at " +
                            "position " + j);
                }
                number += string.charAt(j);
            }
            return new Pair(number, j - 1);
        }
        //        return new Pair("EOF", 0);
        throw new ParseException("Wrong symbol '" + c + "' while parsing '" + string + "' at " +
                "position " + position);
    }

    String next() throws ParseException {
        if (tempPair == null) {
            tempPair = getToken();
        }
        return tempPair.str;
    }

    void consume() throws ParseException {
        position = tempPair.value + 1;
        tempPair = null;
    }
}
