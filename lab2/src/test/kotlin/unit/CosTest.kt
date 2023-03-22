package unit

import mymath.cos
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.test.assertEquals

class CosTest {

    val precision = 0.00001

    @ParameterizedTest(name = "cos")
    @ValueSource(doubles = [ -PI, - PI/2.0, -0.75, 0.0, PI/2.0, 1.2, PI])
    fun `test cos`(x: Double) {
        assertEquals(kotlin.math.cos(x), cos(x, precision), precision)
    }
}