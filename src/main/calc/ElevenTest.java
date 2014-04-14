package main.calc;

import main.calc.calclib.Expression3;
import main.calc.calclib.exceptions.CalcException;
import main.calc.calclib.exceptions.DivideByZeroException;
import main.calc.calclib.exceptions.IncorrectVariableException;
import main.calc.calclib.exceptions.OverflowException;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class ElevenTest {
    private ElevenTest() {}

    public static String evaluate(Expression3 expression) {
        StringBuilder ret = new StringBuilder("x          f");
        for (int i = 0; i < 11; i++) {
            ret.append("\r\n").append(i).append("          ");
            try {
                ret.append(expression.evaluate(i, 0, 0));
            } catch (DivideByZeroException exc) {
                ret.append("division by zero");
            } catch (IncorrectVariableException exc) {
                ret.append("wrong variable");
            } catch (OverflowException exc) {
                ret.append("overflow");
            } catch (CalcException e) {
                e.printStackTrace();
                ret.append("calc exception");
            }
        }
        return ret.toString();
    }
}
