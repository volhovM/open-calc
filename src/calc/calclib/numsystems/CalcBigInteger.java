//package calc.calclib.numsystems;
//
//import java.math.BigInteger;
//
///**
// * Created by volhovm on 21.04.14.
// */
//public class CalcBigInteger implements CalcNumerable<BigInteger> {
//    @Override
//    public BigInteger plus(BigInteger a, BigInteger b) {
//        return a.add(b);
//    }
//
//    @Override
//    public BigInteger mul(BigInteger a, BigInteger b) {
//        return a.multiply(b);
//    }
//
//    @Override
//    public BigInteger div(BigInteger a, BigInteger b) {
//        return a.divide(b);
//    }
//
//    @Override
//    public BigInteger sub(BigInteger a, BigInteger b) {
//        return a.subtract(b);
//    }
//
//    @Override
//    public BigInteger power(BigInteger a, BigInteger b) {
//        BigInteger result = BigInteger.ONE;
//        while (a.signum() > 0) {
//            if (a.testBit(0)) result = result.multiply(b);
//            b = b.multiply(b);
//            a = a.shiftRight(1);
//        }
//        return result;
//    }
//}
