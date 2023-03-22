package integration

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import mymath.*
import mymath.log
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class LogTest {
    val precision = 0.00001

    @BeforeEach
    fun mockFunctions(){
        mockkStatic(::ln)
    }

    @ParameterizedTest(name = "log")
    @CsvSource("""
        NaN, NaN, NaN
        1.0, NaN, NaN
        NaN, 1.0, NaN
        Infinity, Infinity, NaN
        1.0, Infinity, NaN
        Infinity, -1.0, NaN
        -Infinity, -Infinity, NaN
        -1, -Infinity, NaN
        -Infinity, -1, NaN
    """)
    fun `log does not use ln if x or base are special`(x: Double, base: Double, resultLog: Double) {
        every { ln(any(), any()) } returns 1.0
        assertEquals(resultLog, log(x, 2.0, precision), precision)

        verify(exactly = 0) {
            ln(x, precision)
            ln(base, precision)
        }
    }

    @ParameterizedTest(name = "log")
    @CsvSource("""
        -1, 2, NaN
        2, -1, NaN
        2, 0, NaN
        2, 1, NaN
        0, 2, NaN
        0, 1, NaN
    """)
    fun `log does not use ln if x or base are not in ODZ`(x: Double, base: Double, resultLog: Double) {
        every { ln(any(), any()) } returns 100.0
        assertEquals(resultLog, log(x, base, precision), precision)
        verify(exactly = 0) {
            ln(x, precision)
            ln(base, precision)
        }
    }

    @ParameterizedTest(name = "log")
    @CsvSource("""
        1.0, 2.0, 100.0, 1.0
        3.0, 9.0, 50.0, 1.0
        3.0, 9.0, 0.0, NaN
    """)
    fun `log usually uses ln inside`(x: Double, base: Double, lnVal: Double, resultLog: Double) {
        every { ln(x, any()) } returns lnVal
        every { ln(base, any()) } returns lnVal
        assertEquals(resultLog, log(x, base, precision), precision)
        assertEquals(ln(x, precision)/ln(base, precision), log(x, base, precision), precision)
    }


    companion object {
        @JvmStatic
        @AfterAll
        fun unmockFunctions(): Unit {
            unmockkStatic(::ln)
        }
    }
}