package ru.volhovm.calc;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.parser.ParseException;

import java.io.*;
import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 27.04.14
 */
public class ShellCalc {
    private String getHelpMSG() {
        return "Write down expression to evaluate, let $ = ... to initialize variables (a-z), \r\n" +
                ":h to display help, " +
                ":q to exit, \r\n:t TYPE to change type, \r\n:s on or :s off to enable or disable simplifying expressions. \r\n " +
                "Current type: " + currentMode + ", simplify mode is " + (!simplifyMode ? "off" : "on");
    }

    private boolean simplifyMode = true;
    private String currentMode;
    private PrintWriter writer;
    private String currentInput;
    private Number[] variables;

    public ShellCalc(Reader reader, Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void init(String[] args) throws TypeException {
        if (args.length > 1) {
            throw new TypeException("wrong arguments, -d, -i, -bi allowed");
        }
        if (args.length == 0) currentMode = "-d";
        else currentMode = args[0];
        currentInput = "";
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
        writer.println(getHelpMSG());
    }

    public void response(BufferedReader reader) {
        try {
            try {
                currentInput = reader.readLine().trim().toLowerCase();
            } catch (IOException e) {
                writer.println("Can't read the line");
            }
            if (currentInput.startsWith(":")) {
                if (currentInput.equals(":q")) return;
                else if (currentInput.startsWith(":t")) {
                    try {
                        init(new String[]{currentInput.substring(3).trim()});
                    } catch (Exception exc) {
                        writer.println(exc.getMessage());
                    }
                } else if (currentInput.startsWith(":s")) {
                    if (currentInput.length() > 4 && currentInput.substring(3).trim().equals("on")) {
                        simplifyMode = true;
                        writer.println("Simplify mode enabled");
                    } else if (currentInput.length() > 5 && currentInput.substring(3).trim().equals("off")) {
                        simplifyMode = false;
                        writer.println("Simplify mode disabled");
                    } else writer.println("Wrong :s argument, view :h");
                } else if (currentInput.equals(":h")) {
                    writer.println(getHelpMSG());
                } else writer.println("Wrong command, view :h");
            } else if (currentInput.startsWith("let")) {
                currentInput = currentInput.substring(4);
                try {
                    int pos = getCharPos(currentInput.split("=")[0].charAt(0));
                    if (pos == -1) {
                        writer.println("Wrong variable, only a-z allowed.");
                        return;
                    }
                    variables[pos] = simplifyMode ?
                            CalcDashboard.parseSimplifyAndEval(currentMode, currentInput.split("=")[1], variables) :
                            CalcDashboard.parseAndEval(currentMode, currentInput.split("=")[1], variables);
                    writer.println(currentInput.split("=")[0].charAt(0) + " = " + variables[pos]);
                } catch (ParseException | CalcException e) {
                    writer.println(e.getMessage());
                }
            } else {
                try {
                    if (!simplifyMode) {
                        writer.println(CalcDashboard.parseAndEval(currentMode, currentInput, variables));
                    } else {
                        writer.println("Simplified: " + CalcDashboard.parseAndSimplify(currentMode, currentInput));
                        writer.print("Evaluated: ");
                        writer.println(CalcDashboard.parseAndEval(currentMode, currentInput, variables));
                    }
                } catch (ParseException | CalcException e) {
                    writer.println(e.getMessage());
                }
            }
        } catch (Exception unhandled) {
            writer.println("whoops! " + unhandled);
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
