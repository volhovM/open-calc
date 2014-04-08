package main.calc.simplify;

import main.calc.calclib.*;

/**
 * @author volhovm
 */

public class Simplifier {
    private Simplifier() {}

    @SuppressWarnings("FieldCanBeLocal")
    private static int SIMPLIFIER_CONSTANT = 5;

    public static Expression simplify(Expression expression3) {
        for (int i = 0; i < SIMPLIFIER_CONSTANT; i++) {
            expression3 = simplifyInner(expression3);
        }
        return expression3;
    }

    private static Expression simplifyInner(Expression expression) {
        if (expression instanceof Const || expression instanceof Variable) {
            return expression;
        }
        if (expression instanceof BinaryOperation) {
            if (expression instanceof Add) {
                //1 + 2 -> 3
                //0 + a = a + 0 -> a
                //a + a -> 2 * a

                // 3 - 2 -> 1
                //a - 0 -> a | 0 - a -> -a
                //a - a -> 0

                //TODO fill add/sub logic

            } else if (expression instanceof Multiply) {
                //2 * 3 -> 6
                //a * 1 -> 1 | a * 0 -> 0
                //6 / 4 = 1

                //1 / a -> a ^ (-1) | a / 1 -> a | 0 / a -> 0
                //a / a -> 1
                //TODO fill mul/div logic

            } else if (expression instanceof Power) { // a ^ b ^ 2 ^ b -> a ^ 2 * b ^ 2 need to
                // be done?
                //2 ^ 3 -> 8
                // a ^ 0, a ^ 1
                // 0 ^ a, 1 ^ a

            }
        }
        return expression;
    }
}
