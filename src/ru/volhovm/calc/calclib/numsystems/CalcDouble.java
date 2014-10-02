package ru.volhovm.calc.calclib.numsystems;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.exceptions.IncorrectLogarithmArgument;
import com.sun.istack.internal.NotNull;

import java.math.BigInteger;


/**
 * @author ${USERNAME}
 *         Created on 21.04.14
 */

public class CalcDouble implements CalcNumerable<CalcDouble>, Comparable<CalcDouble> {
    private
    @NotNull
    Double a;

    public CalcDouble(Double a) {
        this.a = a;
    }

    public CalcDouble(int a) {
        this.a = (double) a;
    }

    @Override
    public CalcDouble plus(CalcDouble b) throws CalcException {
        Double ret = a + b.a;
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble mul(CalcDouble b) {
        Double ret = a * b.a;
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble div(CalcDouble b) {
//        if (b.a == 0 && a != 0) throw new DivideByZeroException(a.toString());
        Double ret = a / b.a;
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
//        if (ret.isNaN()) {
//            throw new DivideByZeroException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble sub(CalcDouble b) {
        Double ret = a - b.a;
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble power(CalcDouble b) {
//        if (a == 0 && b.a == -1) throw new DivideByZeroException();
        Double ret = Math.pow(a, b.a);
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble abs() {
        return new CalcDouble(Math.abs(a));
    }

    @Override
    public CalcDouble binaryLog() {
        if (a <= 0) {
            throw new IncorrectLogarithmArgument();
        }
        Double ret = Math.log(a) / 0.693147d;
//        if (ret.isInfinite()) {
//            throw new OverflowException(a.toString());
//        }
        return new CalcDouble(ret);
    }

    @Override
    public CalcDouble not() { //bitwise not is not supported in double
        return new CalcDouble(a);
    }

    @Override
    public CalcDouble unaryMin() {
        return new CalcDouble(-a);
    }

    @Override
    public CalcDouble parse(String s) {
        return new CalcDouble(Double.parseDouble(s));
    }

    public String toString() {
        return (a % a == 0) ? String.valueOf(a.intValue()) : a.toString();
    }

    @Override
    public CalcDouble changeArg(Number arg) {
        a = arg.doubleValue();
        return this;
    }

    @Override
    public CalcNumerable<CalcDouble> id() {
        return this;
    }

    @Override
    public <Z extends Convertible> CalcDouble getInstance(Z a) {
        return null;
    }

    @Override
    public String getType() {
        return "Double";
    }

    @Override
    public CalcDouble replace(CalcNumerable a) {
        this.a = a.toDouble();
        return this;
    }

    @Override
    public int compareTo(@NotNull CalcDouble o) {
        if (o == null) throw new NullPointerException("comparing null objects");
        return a.compareTo(o.a);
    }

    @Override
    public Integer toInteger() {
        return a.intValue();
    }

    @Override
    public Double toDouble() {
        return a;
    }

    @Override
    public BigInteger toBigInt() {
        return new BigInteger(a.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalcDouble that = (CalcDouble) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return a != null ? a.hashCode() : 0;
    }
}
