package calc;

import calc.calclib.Expression;
import calc.calclib.numsystems.CalcDouble;
import calc.parser.ExpressionParser;
import calc.parser.ParseException;

/**
 * @author volhovm
 */

public class Test {
    public static void main(String[] args) {
//        assert args.length > 1;
//        StringBuilder s = new StringBuilder();
//        for (int i = 1; i < args.length; i++) {
//            s.append(args[i]);
//        }

//        System.out.println(GenericParser.evaluate("-d", "- - x - - abs( - 1.3860872724170914E-14 / y ) + - - ( - y / - x - abs( ( - abs( 6.098930456454957E-11 + - y ) + y - ( ( x ) * 7.412021636206215E15 + ( ( 3.1592947877698519E18 ) * 9.536978987305107E-21 * y * 3.53314037531117E-8 - 1.0308986030989243E-11 + - ( - - y ) ) - ( ( - 3.5558769791907074E-8 - 9.602347288063473E-12 ) ) ) + 1.296886661829093 ) ) ) - - - abs( 4.177001609747015E-15 + ( y * - - y ) * x - - abs( x / - ( - abs( y / ( - 0.010124567706715071 / - x ) ) ) / - - abs( 7.333829586059131E-14 ) / x ) * - - - x )\n"));

        try {
            Expression<CalcDouble> expression =
                    ExpressionParser.parse(new CalcDouble(0), "sin(2*x)+factorial(10)");
            System.out.println(expression.evaluate(new CalcDouble(2), new CalcDouble(3), new CalcDouble(0)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        expression = SimplifierBinary.simplify(expression);
        //        System.out.println(expression.toString());

        //        try {
        //            System.out.println(GenericParser.evaluate("100000*x*x*x*x*x*x/(x-1)"));
        //        } catch (Exception exc){
        //            System.err.println("Some unknown exception appeared:");
        //            exc.printStackTrace();
        //        }

    }
}
