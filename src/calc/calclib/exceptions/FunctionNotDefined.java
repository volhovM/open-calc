package calc.calclib.exceptions;

/**
 * @author volhovm
 *         Created on 22.04.14
 */
public class FunctionNotDefined extends CalcException {
    private final static String shortMsg = "foo isn't defined";

    public FunctionNotDefined() {
        super("Wrong function definition: ");
    }

    public FunctionNotDefined(String fooname) {
        super("Wrong function definition: function is not present: " + fooname);
    }
}
