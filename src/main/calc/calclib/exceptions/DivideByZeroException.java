package main.calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


@SuppressWarnings("UnusedDeclaration")
public class DivideByZeroException extends CalcException {
    private final static String shortMsg = "division by zero";

    public String getShortMsg() {
        return shortMsg;
    }

    public DivideByZeroException() {
        super("there was a division by zero while evaluating expression");
    }

    public DivideByZeroException(String s) {
        super(s);
    }

    public DivideByZeroException(String s, Throwable cause) {
        super(s, cause);
    }
}
