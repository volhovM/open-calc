package calc.calclib.numsystems;

/**
 * @author ${USERNAME}
 *         Created on 21.04.14
 */

public interface CalcNumerable<T extends CalcNumerable> {
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
}
