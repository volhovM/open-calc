package calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class IncorrectLogariphmArgument extends CalcException {
    private final static String shortMsg = "incorrect logarithm argument";

    @Override
    public String getShortMsg() {
        return shortMsg;
    }

    public IncorrectLogariphmArgument() {
        super("Incorrect binary logarithm argument");
    }

    public IncorrectLogariphmArgument(String s) {
        super(s);
    }
}
