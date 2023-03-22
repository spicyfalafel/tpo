package integration

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import mymath.cos
import mymath.cot
import mymath.sin
import mymath.tan
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class CotTanTest {
    val precision = 0.00001
    @BeforeEach
    fun mockFunctions(){
        mockkStatic(::cos)
        mockkStatic(::sin)
    }

    @ParameterizedTest(name = "cot, tan special")
    @CsvSource(
        "NaN, NaN, NaN, NaN",
        "Infinity, Infinity, NaN, NaN",
        "-Infinity, -Infinity, NaN, NaN"
    )
    fun `cot and tan do not use cos and sin if x is special`(x: Double, y: Double,
                                                             resultCot: Double, resultTan: Double) {
        every { cos(any(), any()) } returns 1.0
        every { sin(any(), any()) } returns 1.0
        assertEquals(resultCot, cot(x, precision), precision)
        assertEquals(resultTan, tan(y, precision), precision)
    }

    @ParameterizedTest(name = "cot, tan")
    @CsvSource(
        "2.0, 1.0, 2, 0.5",
        "1.0, 1.0, 1, 1",
        "1.1, 1.1, 1, 1",
        "NaN, NaN, NaN, NaN",
        "Infinity, Infinity, NaN, NaN",
        "-Infinity, -Infinity, NaN, NaN")
    fun `cot and tan use sin and cos correctly test`(cosVal: Double, sinVal: Double,
                                                     resultCot: Double, resultTan: Double) {
        every { cos(any(), any()) } returns cosVal
        every { sin(any(), any()) } returns sinVal
        // x can be any
        assertEquals(resultCot, cot(1.0, precision), precision)
        assertEquals(resultTan, tan(1.0, precision), precision)
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun unmockFunctions(): Unit {
            unmockkStatic(::cos)
            unmockkStatic(::sin)
        }
    }
}