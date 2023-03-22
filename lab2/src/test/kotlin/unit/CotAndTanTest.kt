package unit

import mymath.cot
import mymath.tan
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class CotAndTanTest {

    val precision = 0.00001

    @ParameterizedTest(name = "cot")
    @ValueSource(doubles = [- PI /2.0, -0.75,  PI /2.0, 1.2])
    fun `test cot`(x: Double) {
        assertEquals(kotlin.math.cos(x)/kotlin.math.sin(x), cot(x, precision), precision)
    }

    @Test
    fun `test cot 0 `() {
        assertEquals(Double.NEGATIVE_INFINITY, cot(0.0, precision))
    }

    @ParameterizedTest(name = "tan")
    @ValueSource(doubles = [- PI /2.0, -0.75, 0.0, PI /2.0, 1.2])
    fun `test tan`(x: Double) {
        assertEquals(kotlin.math.sin(x)/kotlin.math.cos(x), tan(x, precision), precision)
    }

    @Test
    fun `test tan 0 `() {
        assertEquals(0.0, tan(0.0, precision), precision)
    }
}