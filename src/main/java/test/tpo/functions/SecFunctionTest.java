package tpo.functions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SecFunctionTest {
    private static Sec sec;

    @BeforeAll
    static void init() {
        sec = new Sec();
    }

    @DisplayName("Test function in not satisfying function scope")
    @ParameterizedTest(name = "{index}. Test sec({0}) on null ")
    @ValueSource(doubles = {
            (PI / 2), (3 * PI) / 2, (PI / 2) + 2 * PI, ((3 * PI) / 2) + (2 * PI),
            -(PI / 2), -(3 * PI) / 2, -(PI / 2) - 2 * PI, -((3 * PI) / 2) - (2 * PI)
    })
    void checkPiValues(double x) {
        assertNull(sec.calculate(x), "Sec in PI/2 and 3PI/2 should return null");
    }

    @DisplayName("Test function in peaks")
    @ParameterizedTest(name = "{index}. Test sec({0}) in peaks ")
    @ValueSource(doubles = {-2 * PI, -PI, 0, PI, 2 * PI})
    void checkPeaks(double x) {
        assertEquals(1 / cos(x), sec.calculate(x).doubleValue(), 0.00001);
    }

    @ParameterizedTest(name = "{index}. Test sec({0}) in small derivative areas ")
    @ValueSource(doubles = {-5 * PI / 4, -3 * PI / 4, -PI / 4, 3 * PI / 4, 5 * PI / 4})
    @DisplayName("Test function in small derivative areas")
    void checkIntervalsWithSmoothDerivativeChange(double x) {
        assertEquals(1 / cos(x), sec.calculate(x).doubleValue(),  0.00001);
    }

    @DisplayName("Test function in large derivative areas")
    @ParameterizedTest(name = "{index}. Test sec({0}) in large derivative areas ")
    @ValueSource(doubles = {-3 * PI / 2 - 10e-10, -3 * PI / 2 + 10e-10,
            -PI / 2 - 10e-10, -PI / 2 + 10e-10,
            PI / 2 - 10e-10, PI / 2 + 10e-10,
            3 * PI / 2 - 10e-10, 3 * PI / 2 + 10e-10,
    })
    void checkIntervalsWithSignificantDerivativeChange(double x) {
        assertEquals(1 / cos(x), sec.calculate(x).doubleValue(), 0.00001);
    }

    @DisplayName("Test function in big X values")
    @ParameterizedTest(name = "{index}. Test sec({0}) in big X values")
    @ValueSource(doubles = {-10e2, 10e2})
    void checkBigX(double x) {
        assertEquals(1 / cos(x), sec.calculate(x, 10000).doubleValue(), 0.00001);
    }

    @DisplayName("Test function in small X values")
    @ParameterizedTest(name = "{index}. Test sec({0}) in small X values")
    @ValueSource(doubles = {-10e-15, 10e-15})
    void checkSmallX(double x) {
        assertEquals(1 / cos(x), sec.calculate(x).doubleValue(),  0.00001);
    }
}