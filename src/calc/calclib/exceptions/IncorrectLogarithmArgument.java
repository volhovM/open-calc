package calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 15.04.14
 */


public class IncorrectLogarithmArgument extends CalcException {
    private final static String shortMsg = "incorrect logarithm argument";

    @Override
    public String getShortMsg() {
        return shortMsg;
    }

    public IncorrectLogarithmArgument() {
        super("Incorrect binary logarithm argument");
    }

    public IncorrectLogarithmArgument(String s) {
        super(s);
    }
}
