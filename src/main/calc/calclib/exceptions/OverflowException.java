package main.calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


@SuppressWarnings("UnusedDeclaration")
public class OverflowException extends CalcException {
    private static final String shortMsg = "overflow";

    public String getShortMsg() {
        return shortMsg;
    }

    public OverflowException() {
        super("there was a numeric overflow while evaluating expression");
    }

    public OverflowException(String message) {
        super(message);
    }
}
