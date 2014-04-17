package main.calc;

import main.calc.calclib.Expression3;
import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;
import main.calc.simplify.Simplifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class CalcInteractive {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Expression3> mem = new HashMap<>();
        while (true) {
            String s = reader.readLine();
            try {
                if (s.startsWith("let")) {
                    s = s.substring(4); //TODO +-1
                    mem.put(s.split("=")[0],
                            Simplifier.simplify(ExpressionParser.parse(s.split("=")[1])));

                } else {
                    Expression3 z = Simplifier.simplify(ExpressionParser.parse(s));
                    System.out.println(z);
                }
            } catch (ParseException e) {
                e.getMessage();
            }
        }
    }
}
