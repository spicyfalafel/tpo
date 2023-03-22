package unit

import mymath.log10
import mymath.log3
import mymath.log5
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

class LogTest {

    val precision = 0.00001

    @ParameterizedTest(name = "log3")

    @CsvSource("""
        -1.0, NaN
        0.5, -0.431
        1.0, 0.0
        1.5 0.252
    """)
    fun `log3 test`(x: Double, result: Double) {
        assertEquals(result, log3(x, precision), precision)
    }

    @ParameterizedTest(name = "log5")
    @CsvSource("""
        -1.0, NaN
        0.5, -0.431
        1.0, 0.0
        1.5 0.252
        5.0, 1.0
        -1.0, NaN
        0.0, NaN
    """)
    fun `log5(x) is the same as log(x, 5)`(x: Double, resultLog: Double) {
        assertEquals(resultLog, log5(x, precision), precision)
    }

    @ParameterizedTest(name = "log10")
    @CsvSource("""
        1.0, 0.0
        10.0, 1.0
        -1.0, NaN
        0.0, NaN
    """)
    fun `log10(x) is the same as log(x, 10)`(x: Double, resultLog: Double) {
        assertEquals(resultLog, log10(x, precision), precision)
    }
}