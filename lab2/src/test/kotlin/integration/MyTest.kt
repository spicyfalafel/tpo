package integration

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import mymath.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class MyTest {
    val precision = 0.00001

    @ParameterizedTest(name = "cot, tan")
    @CsvSource(
        "2.0, 1.0, 2, 0.5",
        "1.0, 1.0, 1, 1"
    )
    @DisplayName("abc")
    fun `test that cot and tan use sin and cos correctly`(cosVal: Double, sinVal: Double,
                                                          resultCot: Double, resultTan: Double) {
        mockkStatic("mymath.FunctionsKt")
        every {cos(any(), any())} returns cosVal
        every {sin(any(), any())} returns sinVal
        // x can be any
        assertEquals(resultCot, cot(1.0, precision))
        assertEquals(resultTan, tan(1.0, precision))
    }
}