package main.calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


@SuppressWarnings("UnusedDeclaration")
public class IncorrectVariableException extends CalcException {
    public IncorrectVariableException() {
        super();
    }

    public IncorrectVariableException(char constant) {
        super("There was an incorrect variable: " + constant + ". Only x, y, z allowed.");
    }

    public IncorrectVariableException(String s) {
        super(s);
    }

    public IncorrectVariableException(String s, Throwable cause) {
        super(s, cause);
    }
}
