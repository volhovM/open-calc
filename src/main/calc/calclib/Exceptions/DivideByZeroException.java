package main.calc.calclib.Exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


@SuppressWarnings("UnusedDeclaration")
public class DivideByZeroException extends CalcException {
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
