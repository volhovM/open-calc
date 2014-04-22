package calc.calclib.numsystems;

/**
 * @author volhovm
 *         Created on 21.04.14
 */


/*
Declare new function here, one getfoobystring switch case, and define it in all implementations
 */

public interface CalcNumerable<T extends CalcNumerable<T>> {
    public T sin();

    public T factorial();
    public T plus(T a);
    public T mul(T a);
    public T div(T a);
    public T sub(T a);
    public T power(T a);
    public T abs();
    public T binaryLog();
    public T not();
    public T unaryMin();
    public T parse(String s);
    public String toString();

    public <Z extends Number> Z getInnerVariable();

    public CalcNumerable<T> id();

    public <Z extends Number> T getInstance(Z a);

}
