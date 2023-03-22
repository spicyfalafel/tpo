package integration


import io.mockk.*
import mymath.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.math.PI
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SystemTest {
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

    private fun argsForX0(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(-PI/2, 0, Double.NEGATIVE_INFINITY, 0),
            Arguments.of(-3*PI/2, 0, Double.NEGATIVE_INFINITY, 0)
        )
    }

    @ParameterizedTest(name="system x = 0 left")
    @MethodSource("argsForX0")
    fun `test x = 0 left`(x: Double, cotVal: Double, tanVal: Double, result: Double){
        every { cot(x, any()) } returns cotVal
        every { tan(x, any()) } returns tanVal
        assertEquals(result, theSystem(x, precision), precision)
    }

    private fun kotlinCot(x:Double):Double {
        return kotlin.math.cos(x) / kotlin.math.sin(x)
    }

    private fun kotlinTan(x:Double):Double {
        return kotlin.math.sin(x) / kotlin.math.cos(x)
    }

    private fun argsForPointsNextToX0(): Stream<Arguments?>? {
        return Stream.of(
            Arguments.of(-PI/2-1.0, 2.42551),
            Arguments.of(-PI/2+1.0, 2.42551),
            Arguments.of(-3*PI/2-1.0, 2.42551),
            Arguments.of(-3*PI/2+1.0, 2.42551)
        )
    }

    @ParameterizedTest(name="system next to x = 0 left")
    @MethodSource("argsForPointsNextToX0")
    fun `left test points next to x=0 points`(x: Double, result: Double){
        every { cot(x, any()) } returns kotlinCot(x)
        every { tan(x, any()) } returns kotlinTan(x)
        assertEquals(result, theSystem(x, precision), precision)
    }



    @ParameterizedTest(name = "the system x>=0")
    @CsvSource("""
        1.0, 0.0, 0.0, 0.0, 0.0, 0.0
    """)
    fun `when x=1`(x: Double, lnVal:Double, log3Val:Double, log5Val: Double, log10Val: Double, result: Double) {
        every { log10(x, any()) } returns log10Val
        every { log5(x, any()) } returns log5Val
        every { log3(x, any()) } returns log3Val
        every { ln(x, any()) } returns lnVal

        assertEquals(result, theSystem(x, precision), precision)
    }


    @ParameterizedTest(name = "the system x>=0")
    @CsvSource("""
        14.563, 0
        7.61, -3.53
        0.131, 3.53
        20.0, 6.138
    """)
    fun `test other check points`(x: Double, result: Double) {
        every { log10(x, any()) } returns kotlin.math.log10(x)
        every { log5(x, any()) } returns kotlin.math.log(x, 5.0)
        every { log3(x, any()) } returns kotlin.math.log(x, 3.0)
        every { ln(x, any()) } returns kotlin.math.ln(x)

        assertEquals(result, theSystem(x, precision), 0.001)
    }


    companion object {
        @JvmStatic
        @AfterAll
        fun unmockFunctions(): Unit {
            unmockkStatic(::theSystem)
        }
    }
}