package mymath

import mymath.*
import kotlin.math.pow

fun theSystem (x:Double, precision: Double):Double {
    return if (x <= 0) {
        (cot(x, precision) / tan(x, precision))
    } else{
        (((((log10(x, precision) * ln(x, precision)).pow(2)) * log5(x, precision)) - log5(x, precision)) - (log3(x, precision).pow(3)))
    }
}
