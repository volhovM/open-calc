package calc.calclib.numsystems;

import calc.calclib.exceptions.DivideByZeroException;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 21.04.14
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
    public CalcBigInteger changeArg(Number arg) {
        a = (BigInteger) arg;
        return this;
    }

    @Override
    public CalcBigInteger id() {
        return this;
    }

    @Override
    public <Z extends Convertible> CalcBigInteger getInstance(Z a) {
        return null;
    }

    @Override
    public String getType() {
        return "BigInteger";
    }

    @Override
    public CalcBigInteger replace(CalcNumerable a) {
        this.a = a.toBigInt();
        return this;
    }

    @Override
    public Integer toInteger() {
        return a.intValue();
    }

    @Override
    public Double toDouble() {
        return a.doubleValue();
    }

    @Override
    public BigInteger toBigInt() {
        return a;
    }
}
