package main.calc;

import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;

/**
 * Created by volhovm on 01.04.14.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        System.out.println(ExpressionParser.parse("~((1 + ((2 + 3) + 4)) + (5 + (6 + 7) + (8 + 9)))").toString());
    }
}
