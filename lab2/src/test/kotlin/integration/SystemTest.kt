package integration


import io.mockk.*
import kotlin.test.assertEquals
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import mymath.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.pow

class SystemTest {
    /*

    return if (x <= 0) {
        (cot(x, precision) / tan(x, precision))
    } else{
        (((((log10(x, precision) * ln(x, precision)).pow(2)) * log5(x, precision)) - log5(x, precision)) - (log3(x, precision).pow(3)))
    }
     */
    val precision = 0.00001

    @BeforeEach
    fun mockFunctions(){
        mockkStatic(::cos)
        mockkStatic(::tan)
        mockkStatic(::log)
        mockkStatic(::log3)
        mockkStatic(::log5)
        mockkStatic(::log10)
        mockkStatic(::ln)
    }

    @ParameterizedTest(name = "the system x<= 0")
    @CsvSource("""
        0.0, 1.0, 1.0, 1.0
    """)
    fun `when x is less or equal 0 ensure sut uses cot and tan correctly`(x: Double,
                                                                          cotVal: Double,
                                                                          tanVal: Double,
                                                                          result: Double) {
        every { cot(x, any()) } returns cotVal
        every { tan(x, any()) } returns tanVal
        assertEquals(result, theSystem(x, precision), precision)
    }

    @ParameterizedTest(name = "the system x>=0")
    @CsvSource("""
        1.0, 0.0, 0.0
        1.0, 0.0
    """)
    fun `when x is greater than 0 sut uses (log10 mul ln(x))^2`(x: Double,
                                                                log10val: Double,
                                                                result: Double) {
        // (((((log10(x, precision) * ln(x, precision)).pow(2)) * log5(x, precision)) - log5(x, precision)) - (log3(x, precision).pow(3)))
        // log10 вернет 0 и ( log10(x)*ln(x) )^2 = 0, => -log5(x) - log3(x)^3
        every { log10(x, any()) } returns log10val

        assertEquals(-log5(x, precision) - log3(x, precision).pow(3), theSystem(x, precision), precision)
    }

    companion object {
        @JvmStatic
        @AfterAll
        fun unmockFunctions(): Unit {
            unmockkStatic(::theSystem)
        }
    }
}