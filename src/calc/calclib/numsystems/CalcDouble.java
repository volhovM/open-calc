package calc.calclib.numsystems;

/**
 * Created by volhovm on 21.04.14.
 */
public class CalcDouble implements MyCalcCalculable<Double> {
    private Double a;

    public CalcDouble(Double a) {
        this.a = a;
    }

    @Override
    public Double plus(Double b) {
        return a + b;
    }

    @Override
    public Double mul(Double b) {
        return a * b;
    }

    @Override
    public Double div(Double b) {
        return a / b;
    }

    @Override
    public Double sub(Double b) {
        return a - b;
    }

    @Override
    public Double power(Double b) {
        return Math.pow(a, b);
    }
}
