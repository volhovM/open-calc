package main.calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class CalcException extends Exception {
    public CalcException() {
        super();
    }

    public CalcException(String s) {
        super(s);
    }

    public CalcException(String s, Throwable cause) {
        super(s, cause);
    }
}
