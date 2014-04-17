package main.calc.simplify;

import main.calc.calclib.*;

/**
 * @author volhovm
 */

public class Simplifier {
    private Simplifier() {}

    @SuppressWarnings("FieldCanBeLocal")
    private static int SIMPLIFIER_CONSTANT = 5;

    public static Expression3 simplify(Expression3 expression3) {
        for (int i = 0; i < SIMPLIFIER_CONSTANT; i++) {
            expression3 = simplifyInner(expression3);
        }
        return expression3;
    }

    private static Expression3 simplifyInner(Expression3 expression) {
        Class expressionClass = expression.getClass();
        if (expression instanceof Const || expression instanceof Variable) {
            return expression;
        }
        if (expression instanceof BinaryOperations) {
            if (expression instanceof Add) {
                Expression3 left = ((Add) expression).a = simplify(((Add) expression).a);
                Expression3 right = ((Add) expression).b = simplify(((Add) expression).b);

                if (left instanceof Const && right instanceof Const) { //1 + 2 -> 3
                    return new Const(((Const) left).getConstant() + ((Const) right).getConstant());
                }
                if ((left instanceof Const) //0 + a = a + 0 -> a
                    || (right instanceof Const)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Expression3 a = ((left instanceof Const) ? right : left);
                    if (constant.getConstant() == 0) {
                        return a;
                    }
                }
                if (Expression3.equalsExp(left, right)) { //a + a -> 2 * a
                    return new Multiply(new Const(2), left);
                }
            } else if (expression instanceof Subtract) {
                Expression3 left = ((Subtract) expression).a = simplify(((Subtract) expression).a);
                Expression3 right = ((Subtract) expression).b = simplify(((Subtract) expression).b);

                if (left instanceof Const && right instanceof Const) { // 3 - 2 -> 1
                    return new Const(((Const) left).getConstant() - ((Const) right).getConstant());
                }
                if ((left instanceof Const) //a - 0 -> a | 0 - a -> -a
                    || (right instanceof Const)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Expression3 a = ((left instanceof Const) ? right : left);
                    if (constant.getConstant() == 0) {
                        if (left instanceof Const) {
                            return new UnaryMin(a);
                        } else {
                            return a;
                        }
                    }
                }
                //TODO ensure it works
                if (Expression3.equalsExp(left, right)) { //a - a -> 0
                    return new Const(0);
                }
            } else if (expression instanceof Multiply) {
                Expression3 left = ((Multiply) expression).a = simplify(((Multiply) expression).a);
                Expression3 right = ((Multiply) expression).b = simplify(((Multiply) expression).b);

                if (left instanceof Const && right instanceof Const) {//2 * 3 -> 6
                    return new Const(((Const) left).getConstant() * ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable)
                    || (right instanceof Const && left instanceof Variable)) { //a * 1 -> 1 | a *
                    // 0 -> 0
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 0) {
                        return new Const(0);
                    }
                    if (constant.getConstant() == 1) {
                        return variable;
                    }
                }
                if (Expression3.equalsExp(left, right)) {
                    return new Power(left, new Const(2));
                }
            } else if (expression instanceof Divide) {
                Expression3 left = ((Divide) expression).a = simplify(((Divide) expression).a);
                Expression3 right = ((Divide) expression).b = simplify(((Divide) expression).b);
                if (left instanceof Const && right instanceof Const) {      //6 / 4 = 1
                    return new Const(((Const) left).getConstant() / ((Const) right).getConstant());
                }
                if ((left instanceof Const && right instanceof Variable)    //1 / a -> a ^ (-1) |
                // a / 1 -> a | 0 / a -> 0
                    || (right instanceof Const && left instanceof Variable)) {
                    Const constant = (Const) ((left instanceof Const) ? left : right);
                    Variable variable = (Variable) ((left instanceof Variable) ? left : right);
                    if (constant.getConstant() == 1) {
                        if (right instanceof Const) {
                            return variable;
                        } else {
                            return new Power(variable, new UnaryMin(new Const(1)));
                        }
                    }
                    if (constant.getConstant() == 0) {
                        if (left instanceof Const) {
                            return new Const(0);
                        }
                    }
                }
                //TODO ensure it works too
                if (Expression3.equalsExp(left, right)) { //a / a -> 1
                    return new Const(1);
                }
            } else if (expression instanceof Power) {
                Expression3 left = ((Power) expression).a = simplify(((Power) expression).a);
                Expression3 right = ((Power) expression).b = simplify(((Power) expression).b);
                if (left instanceof Const && right instanceof Const) { //2 ^ 3 -> 8
                    return new Const((int) Math.pow(((Const) left).getConstant(),
                                                    ((Const) right).getConstant()));
                } else if (right instanceof Const) { // a ^ 0, a ^ 1
                    if (((Const) right).getConstant() == 0) {
                        return new Const(1);
                    } else if (((Const) right).getConstant() == 1) {
                        return left;
                    }
                } else if (left instanceof Const) { // 0 ^ a, 1 ^ a
                    if (((Const) left).getConstant() == 0) {
                        return new Const(0);
                    } else if (((Const) left).getConstant() == 1) {
                        return new Const(1);
                    }
                }
                return new Power(left, right);
            }
        } else if (expression instanceof UnaryOperations) {
            Expression3 a = simplify(((UnaryOperations) expression).a);
            if (a instanceof Const) {
                return new Const(expression.evaluate(-1, -1, -1)); //random, doesn't matter actually
            }
        }
        return expression;
    }
}
