package calc.calclib.numsystems;

import calc.calclib.exceptions.DivideByZeroException;
import calc.calclib.exceptions.FunctionNotDefined;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;

/**
 * @author volhovm
 * Created on 21.04.14
 */
public class CalcBigInteger implements CalcNumerable<CalcBigInteger>, Comparable<CalcBigInteger> {
    private
    @NotNull
    BigInteger a;

    public CalcBigInteger(BigInteger a) {
        this.a = a;
    }

    public CalcBigInteger(Integer a) {
        this.a = new BigInteger(String.valueOf(a));
    }

    @Override
    public CalcBigInteger sin() {
        throw new FunctionNotDefined();
    }

    @Override
    public CalcBigInteger factorial() {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; a.compareTo(new BigInteger(String.valueOf(i))) <= 0; i++) {
            ret = ret.multiply(new BigInteger(String.valueOf(i)));
        }
        return new CalcBigInteger(ret);
    }


    //no overflow i guess
    @Override
    public CalcBigInteger plus(CalcBigInteger b) {
        return new CalcBigInteger(a.add(b.a));
    }

    @Override
    public CalcBigInteger mul(CalcBigInteger b) {
        return new CalcBigInteger(a.multiply(b.a));
    }

    @Override
    public CalcBigInteger div(CalcBigInteger b) {
        if (b.a.equals(BigInteger.ZERO)) throw new DivideByZeroException();
        return new CalcBigInteger(a.divide(b.a));
    }

    @Override
    public CalcBigInteger sub(CalcBigInteger b) {
        return new CalcBigInteger(a.subtract(b.a));
    }

    @Override
    public CalcBigInteger power(CalcBigInteger b) {
        BigInteger result = BigInteger.ONE;
        BigInteger c = b.a;
        while (a.signum() > 0) {
            if (a.testBit(0)) result = result.multiply(c);
            c = c.multiply(c);
            a = a.shiftRight(1);
        }
        return new CalcBigInteger(result);
    }

    @Override
    public CalcBigInteger abs() {
        return new CalcBigInteger(a.abs());
    }

    @Override
    public CalcBigInteger binaryLog() {
        BigInteger ret = BigInteger.ZERO;
        while (a.compareTo(BigInteger.ONE) > 0) {
            a.divide(BigInteger.ONE.add(BigInteger.ONE));
            ret.add(BigInteger.ONE);
        }
        return new CalcBigInteger(ret); //TODO insert binarylog code
    }

    @Override
    public CalcBigInteger not() {
        return new CalcBigInteger(a.not());
    }

    @Override
    public CalcBigInteger unaryMin() {
        return new CalcBigInteger(a.negate());
    }

    @Override
    public CalcBigInteger parse(String s) {
        return new CalcBigInteger(new BigInteger(s));
    }

    @Override
    public int compareTo(@NotNull CalcBigInteger o) {
        if (o == null) throw new NullPointerException();
        return a.compareTo(o.a);
    }

    @Override
    public String toString() {
        return a.toString();
    }

    @Override
    public CalcBigInteger id() {
        return this;
    }

}
