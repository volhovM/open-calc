package main.calc.calclib;

/**
 * @author volhovm
 */

public interface Expression {
    public double evaluate(double x, double y, double z);

    public String toString();

    public short getPriority();


    //    public static boolean almostEqualsExp(Expression expressionOne,
    // Expression expressionTwo){
    //        if (!equalsExp(expressionOne, expressionTwo)){ //if not exactly equals,
    // comparing for equality like Var, Mul (Const, Var)
    //            if (containSamePowerVars(expressionOne, expressionTwo)){
    //
    //            }
    //        } else return false;
    //    }

    //exact equality for simplified expressions! (a != 1 * a)
    //    public static boolean equalsExp(Expression expressionOne, Expression expressionTwo) {
    //        if (expressionOne == null || expressionTwo == null) {
    //            return Boolean.parseBoolean(null);
    //        }
    //        if (expressionOne.getClass() == expressionTwo.getClass()) {
    //            if (expressionOne instanceof Variable) {
    //                return ((Variable) expressionOne).getVariableType() == ((Variable)
    // expressionTwo)
    //                    .getVariableType();
    //            } else if (expressionOne instanceof Const) {
    //                return ((Const) expressionOne).getConstant() == ((Const) expressionTwo)
    //                    .getConstant();
    //            } else if (expressionOne instanceof Multiply || expressionOne instanceof Add) {
    //                return equalsExp(((BinaryOperations) expressionOne).a,
    //                                 ((BinaryOperations) expressionTwo).a) //a * b = c * d if
    //                    && equalsExp(((BinaryOperations) expressionTwo).b,
    //                                 ((BinaryOperations) expressionOne).b) // a ==c && b == d
    // || a ==
    //                                 // d && b == c
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
