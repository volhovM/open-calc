package calc;

import calc.calclib.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
        try {
            System.out.println(CalcDashboard.parseAndEval("-d", "10000/2/4^2"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
