package calc;

import calc.calclib.exceptions.CalcException;
import calc.calclib.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 27.04.14
 */
public class ShellCalc {
    private static String getHelpMSG() {
        return "Write down expression to evaluate, let $ = ... to initialize variables (a-z), \r\n" +
                ":h to display help, " +
                ":q to exit, \r\n:t TYPE to change type, \r\n:s on or :s off to enable or disable simplifying expressions. \r\n " +
                "Current type: " + currentMode + ", simplify mode is " + (!simplifyMode ? "off" : "on");
    }

    private static boolean simplifyMode = true;
    private static String currentMode;

    public static void main(String[] args) throws TypeException {
        if (args.length > 1) {
            throw new TypeException("wrong arguments, -d, -i, -bi allowed");
        }
        if (args.length == 0) currentMode = "-d";
        else currentMode = args[0];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentInput = "";
        Number[] variables;
        switch (currentMode) {
            case "-i":
                variables = new Integer['z' - 'a'];
                break;
            case "-d":
                variables = new Double['z' - 'a'];
                break;
            case "-bi":
                variables = new BigInteger['z' - 'a'];
                break;
            default:
                throw new TypeException("wrong arguments, -d, -i, -bi allowed");
        }
        System.out.println(getHelpMSG());
        try {
            while (true) {
                try {
                    System.out.print("> ");
                    currentInput = reader.readLine().trim().toLowerCase();
                } catch (IOException e) {
                    System.out.println("Can't read the line");
                }
                if (currentInput.startsWith(":")) {
                    if (currentInput.equals(":q")) return;
                    else if (currentInput.startsWith(":t")) {
                        try {
                            main(new String[]{currentInput.substring(3).trim()});
                            return;
                        } catch (Exception exc) {
                            System.out.println(exc.getMessage());
                        }
                    } else if (currentInput.startsWith(":s")) {
                        if (currentInput.length() > 4 && currentInput.substring(3).trim().equals("on")) {
                            simplifyMode = true;
                            System.out.println("Simplify mode enabled");
                        } else if (currentInput.length() > 5 && currentInput.substring(3).trim().equals("off")) {
                            simplifyMode = false;
                            System.out.println("Simplify mode disabled");
                        } else System.out.println("Wrong :s argument, view :h");
                    } else if (currentInput.equals(":h")) {
                        System.out.println(getHelpMSG());
                    } else System.out.println("Wrong command, view :h");
                } else if (currentInput.startsWith("let")) {
                    currentInput = currentInput.substring(4);
                    try {
                        int pos = getCharPos(currentInput.split("=")[0].charAt(0));
                        if (pos == -1) {
                            System.out.println("Wrong variable, only a-z allowed.");
                            continue;
                        }
                        variables[pos] = simplifyMode ?
                                CalcDashboard.parseSimplifyAndEval(currentMode, currentInput.split("=")[1], variables) :
                                CalcDashboard.parseAndEval(currentMode, currentInput.split("=")[1], variables);
                        System.out.println(currentInput.split("=")[0].charAt(0) + " = " + variables[pos]);
                    } catch (ParseException | CalcException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        if (!simplifyMode) {
                            System.out.println(CalcDashboard.parseAndEval(currentMode, currentInput, variables));
                        } else {
                            System.out.println("Simplified: " + CalcDashboard.parseAndSimplify(currentMode, currentInput));
                            System.out.print("Evaluated: ");
                            System.out.println(CalcDashboard.parseAndEval(currentMode, currentInput, variables));
                        }
                    } catch (ParseException | CalcException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (Exception unhandled) {
            System.out.println("whoops! " + unhandled.getMessage());
            unhandled.printStackTrace();
        }
    }

    private static int getCharPos(char c) {
        if (c < 'a' || c > 'z') return -1;
        if (c == 'x') return 0;
        if (c == 'y') return 1;
        if (c == 'z') return 2;
        else return 3 + c - 'a';
    }
}
