package integration

import io.mockk.*
import mymath.cos
import mymath.sin
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class SinTest {
    val precision = 0.00001

    @BeforeEach
    fun mockFunctions(){
        mockkStatic(::cos)
    }

    @ParameterizedTest(name = "sin special")
    @CsvSource("""
        NaN, NaN,
        |Infinity, Infinity, 
        -Infinity, -Infinity
        """)
    fun `sin does not use cos if special`(cosVal: Double, resultSin: Double){
        every { cos(any(), any()) } returns cosVal
        // x can be any
        assertEquals(resultSin, sin(1.0, precision), precision)

        verify(exactly = 0) {
            cos(1.0, precision)
        }
    }

    @ParameterizedTest(name = "sin")
    @CsvSource("""
        -1.0, 1.0
        0.0, 0.0
        1.0, -1.0
        1.2, -1.2
    """)
    fun `sin uses cos test`(cosVal: Double, resultSin: Double) {
        every { cos(any(), any()) } returns cosVal
        // x can be any
        assertEquals(resultSin, sin(1.0, precision), precision)
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun unmockFunctions(): Unit {
            unmockkStatic(::cos)
        }
    }
}