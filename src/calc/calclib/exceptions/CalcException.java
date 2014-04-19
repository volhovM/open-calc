package calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class CalcException extends ArithmeticException {
    private static final String shortMsg = "calc exception";

    public String getShortMsg() {
        return shortMsg;
    }

    public CalcException() {
        super();
    }

    public CalcException(String s) {
        super(s);
    }
}
