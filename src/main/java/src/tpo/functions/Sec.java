package tpo.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import static java.lang.Math.PI;

public class Sec {
    public BigDecimal calculate(double x) {
        return calculate(x, 100);
    }

    public BigDecimal calculate(double x, int maxIterations) {
        if ((x % (PI / 2) == 0 || x % ((3 * PI) / 2) == 0) && x % PI != 0) return null;

        BigDecimal cos = new BigDecimal(0);
        BigDecimal x2 = new BigDecimal(x).multiply(new BigDecimal(x));
        BigDecimal prevX = new BigDecimal(1);
        BigInteger lastFactorialValue = BigInteger.valueOf(1);

        for (int n = 0; n < maxIterations; n++) {
            lastFactorialValue = calculateFactorial(n*2, lastFactorialValue);
            cos = cos.add(new BigDecimal(-1)
                    .pow(n)
                    .multiply(prevX)
                    .divide(new BigDecimal(lastFactorialValue), 100, RoundingMode.HALF_UP));
            prevX = prevX.multiply(x2);
        }
        return BigDecimal.ONE.divide(cos, 16, RoundingMode.HALF_UP);
    }

    private BigInteger calculateFactorial(int step, BigInteger lastFactValue) {
        if (step == 0) return BigInteger.valueOf(1);

        return lastFactValue.multiply(BigInteger.valueOf(step-1)).multiply(BigInteger.valueOf(step));
    }

}
