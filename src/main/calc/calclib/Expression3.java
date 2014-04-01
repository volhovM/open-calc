package main.calc.calclib;

/**
 * Created by volhovm on 3/15/14.
 */
public interface Expression3 {
    public int evaluate(int x, int y, int z);

    public String toString();

    public short getPriority();
    //    public default Expression3 simplify(){
    //        return this;
    //    }
}
