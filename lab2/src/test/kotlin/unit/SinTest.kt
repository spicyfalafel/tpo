package unit

import mymath.sin
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.math.PI
import kotlin.test.assertEquals

class SinTest {

    val precision = 0.00001

    @ParameterizedTest(name = "sin")
    @ValueSource(doubles = [- PI /2.0, -0.75, 0.0, PI /2.0, 1.2, PI])
    fun `test sin`(x: Double) {
        assertEquals(kotlin.math.sin(x), sin(x, precision), precision)
    }
}