package calc.calclib.numsystems;

import calc.calclib.exceptions.CalcException;
import calc.calclib.exceptions.OverflowException;

/**
 * Created by volhovm on 21.04.14.
 */
public class CalcInt implements MyCalcCalculable<CalcInt> {
    private Integer a;

    public CalcInt(Integer a) {
        this.a = a;
    }

    @Override
    public CalcInt plus(CalcInt b) throws CalcException {
        if (((long) a) + ((long) b.a) > Integer.MAX_VALUE) throw new OverflowException();
        return new CalcInt(a + b.a);
    }

    @Override
    public CalcInt mul(CalcInt b) {
        return new CalcInt(a * b.a);
    }

    @Override
    public CalcInt div(CalcInt b) {
        return new CalcInt(a / b.a);
    }

    @Override
    public CalcInt sub(CalcInt b) {
        return new CalcInt(a - b.a);
    }

    @Override
    public CalcInt power(CalcInt b) {
        int c = a;
        for (int i = 0; i < b.a - 1; i++) {
            c = c * b.a;
        }
        return new CalcInt(c);
    }
}
