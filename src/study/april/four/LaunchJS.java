package study.april.four;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author volhovm
 *         Created on 27.04.14
 */
public class LaunchJS {
    public static void main(String[] args) throws FileNotFoundException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            engine.eval(new FileReader(System.getProperty("user.dir") + "/src/study/may/one/somescript.js"));
        } catch (ScriptException e) {
            System.err.println(e.getMessage());
        }
    }
}
