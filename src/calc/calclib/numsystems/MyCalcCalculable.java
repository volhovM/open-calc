package calc.calclib.numsystems;

/**
 * Created by volhovm on 21.04.14.
 */
public interface MyCalcCalculable<T extends MyCalcCalculable> {
    public T plus(T a);

    public T mul(T a);

    public T div(T a);

    public T sub(T a);

    public T power(T a);
}
