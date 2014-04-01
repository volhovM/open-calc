package main.calc.calclib;

/**
 * Created by volhovm on 3/15/14.
 */
public class Add extends BinaryOperations implements Expression3 {
    final short PRIORITY = 2;

    public Add(Expression3 a, Expression3 b) {
        super(a, b);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return a.evaluate(x, y, z) + b.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return (a.getPriority() >= PRIORITY ? a.toString() : "(" + a.toString() + ")") + " + " + (b.getPriority() >= PRIORITY ? b.toString() : "(" + b.toString() + ")");
    }

    //    @Override
    //    public Expression3 simplify() {
    //        Expression3 left = a.simplify();
    //        Expression3 right = b.simplify();
    //        if (left instanceof Const && right instanceof Const) {
    //            Class<Const> constClass = new Class<Const>();
    //            this = new Const(((Const) left).getConstant() + ((Const) right).getConstant());
    //        } else if (left instanceof Const || right instanceof Const) {
    //            ((Const) a.simplify()).getConstant();
    //        } else if (b.simplify().evaluate(-1, -2, -3) == 0) {
    //
    //        } else {
    //            return simplify()
    //        }
    //    }

    @Override
    public short getPriority() {
        return PRIORITY;
    }
}
