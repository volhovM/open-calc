package calc.calclib.numsystems;

import java.math.BigInteger;

/**
 * @author volhovm
 *         Created on 23.04.14
 */
public interface Convertible {
    public default <T extends CalcNumerable> Number getInner(T a) {
        if (a.getClass() == CalcInteger.class) {
            return a.toInteger();
        } else if (a.getClass() == CalcDouble.class) {
            return a.toDouble();
        } else if (a.getClass() == CalcBigInteger.class) {
            return a.toBigInt();
        }
        return null;
    }

    public Integer toInteger();

    public Double toDouble();

    public BigInteger toBigInt();
}
