package main.calc.parser;

import java.util.Arrays;

/**
 * Created by volhovm on 01.04.14.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(String message, String wrongString) {
        System.err.println("Parse error on parsing " + wrongString + "\r\n Stack trace: \r\n");
        System.err.println(Arrays.toString(this.getStackTrace()));
    }
}
