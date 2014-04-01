package main.calc.simplify;

import main.calc.calclib.*;
import main.calc.parser.ExpressionParser;
import main.calc.parser.ParseException;

/**
 * Created by volhovm on 01.04.14.
 */
public class Simplifier {
    public static void main(String[] args) throws ParseException {
        System.out.println(Simplifier.simplify(ExpressionParser.parse(
            "(2+3+4+5)* x * x * (y + 3*y + y) / (2-6)")).toString());
    }

    public static Expression3 simplify(Expression3 expression) {
        if (expression instanceof Const || expression instanceof Variable) {
            return expression;
        }
        if (expression instanceof BinaryOperations) {
            if (expression instanceof Add) {
                Expression3 left = ((Add) expression).a = simplify(((Add) expression).a);
                Expression3 right = ((Add) expression).b = simplify(((Add) expression).b);
                if (left instanceof Const && right instanceof Const) {
                    return new Const(((Const) left).getConstant() + ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable) || (right instanceof Const && left instanceof Variable)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 0) {
                        return variable;
                    }
                }
                if (left instanceof Variable && right instanceof Variable) {
                    Variable variableOne = (Variable) left;
                    Variable variableTwo = (Variable) right;
                    if (variableOne.getVariableType() == variableTwo.getVariableType()) {
                        return new Multiply(new Const(2), variableTwo);
                    }
                }
            } else if (expression instanceof Subtract) {
                Expression3 left = ((Subtract) expression).a = simplify(((Subtract) expression).a);
                Expression3 right = ((Subtract) expression).b = simplify(((Subtract) expression).b);
                if (left instanceof Const && right instanceof Const) {
                    return new Const(((Const) left).getConstant() - ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable) || (right instanceof Const && left instanceof Variable)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 0) {
                        if (left instanceof Const) {
                            return new UnaryMin(variable);
                        } else {
                            return variable;
                        }
                    }
                }
                if (left instanceof Variable && right instanceof Variable) {
                    Variable variableOne = (Variable) left;
                    Variable variableTwo = (Variable) right;
                    if (variableOne.getVariableType() == variableTwo.getVariableType()) {
                        return new Const(0);
                    }
                }
            } else if (expression instanceof Multiply) {
                Expression3 left = ((Multiply) expression).a = simplify(((Multiply) expression).a);
                Expression3 right = ((Multiply) expression).b = simplify(((Multiply) expression).b);
                if (left instanceof Const && right instanceof Const) {
                    return new Const(((Const) left).getConstant() * ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable) || (right instanceof Const && left instanceof Variable)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 0) {
                        return new Const(0);
                    }
                    if (constant.getConstant() == 1) {
                        return variable;
                    }
                }
            } else if (expression instanceof Divide) {
                Expression3 left = ((Divide) expression).a = simplify(((Divide) expression).a);
                Expression3 right = ((Divide) expression).b = simplify(((Divide) expression).b);
                if (left instanceof Const && right instanceof Const) {
                    return new Const(((Const) left).getConstant() / ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable) || (right instanceof Const && left instanceof Variable)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 1) {
                        if (right instanceof Const) {
                            return variable;
                        }
                    }
                    if (constant.getConstant() == 0) {
                        if (left instanceof Const) {
                            return new Const(0);
                        }
                    }
                }
                if (left instanceof Variable && right instanceof Variable) {
                    Variable variableOne = (Variable) left;
                    Variable variableTwo = (Variable) right;
                    if (variableOne.getVariableType() == variableTwo.getVariableType()) {
                        return new Const(1);
                    }
                }
            }
        }
        return expression;
    }
}
