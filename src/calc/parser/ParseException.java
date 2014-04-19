package calc.parser;

/**
 * @author volhovm
 */

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, String wrongString) {
        super("Parse error on parsing " + wrongString + "\r\n Stack trace: \r\n");
    }
}
