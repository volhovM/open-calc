package calc.calclib;

import calc.calclib.exceptions.CalcException;
import calc.calclib.numsystems.CalcNumerable;
import com.sun.istack.internal.NotNull;

/**
 * @author volhovm
 */

public interface Expression<T extends CalcNumerable> {
    T evaluate(@NotNull T... args) throws CalcException;

    String toString();

    short getPriority();

    Expression<T> simplify(T type);

    boolean equals(Expression<T> a);

    //test
//    public static boolean equalsExp(Expression3 expressionOne, Expression3 expressionTwo) {
//        if (expressionOne == null || expressionTwo == null) {
//            return Boolean.parseBoolean(null);
//        }
//        if (expressionOne.getClass() == expressionTwo.getClass()) {
//            if (expressionOne instanceof Variable) {
//                return ((Variable) expressionOne).getVariableType() == ((Variable) expressionTwo)
//                    .getVariableType();
//            } else if (expressionOne instanceof Const) {
//                return ((Const) expressionOne).getConstant() == ((Const) expressionTwo)
//                    .getConstant();
//            } else if (expressionOne instanceof Multiply || expressionOne instanceof Add) {
//                return equalsExp(((BinaryOperations) expressionOne).a,
//                                 ((BinaryOperations) expressionTwo).a) //a * b = c * d if
//                    && equalsExp(((BinaryOperations) expressionTwo).b,
//                                 ((BinaryOperations) expressionOne).b) // a ==c && b == d || a ==
//                    // d && b == c
//                    ||
//                    equalsExp(((BinaryOperations) expressionOne).a,
//                              ((BinaryOperations) expressionTwo).b)
//                        && equalsExp(((BinaryOperations) expressionTwo).b,
//                                     ((BinaryOperations) expressionOne).a);
//
//            } else if (expressionOne instanceof Divide || expressionOne instanceof Subtract ||
//                expressionOne instanceof Power) {
//                return equalsExp(((BinaryOperations) expressionOne).a,
//                                 ((BinaryOperations) expressionTwo).a)
//                    && equalsExp(((BinaryOperations) expressionTwo).b,
//                                 ((BinaryOperations) expressionOne).b);
//            } else if (expressionOne instanceof UnaryOperations) {
//                return equalsExp(((UnaryOperations) expressionOne).a,
//                                 ((UnaryOperations) expressionTwo).a);
//            }
//        }
//        return false;
//    }
}
