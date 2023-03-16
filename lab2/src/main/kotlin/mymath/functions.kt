package mymath

import kotlin.math.PI
import kotlin.math.absoluteValue
import kotlin.math.pow

val Number.isSpecial: Boolean
    get() = this in setOf(Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY)

//const val PRECISION = 0.000000001
const val MAX_ITERATIONS = 1_000_000
fun Double.normalize(accuracy: Double) = if (this.absoluteValue - 0.0 < accuracy) 0.0 else this

fun cosFormula(x:Double):Sequence<Pair<Double, Int>> {
    return generateSequence(1.0 to 1) { it.first * -x.pow(2) / ((2 * it.second - 1) * (2 * it.second)) to it.second + 1 }
}
fun cos(x: Double, precision: Double): Double {
    return if (x.isSpecial) {
        x
    } else{
        cosFormula(x)
            .takeWhile { it.first.absoluteValue > precision }
            .sumOf { it.first }.normalize(precision)
    }
}
fun sin(x: Double, precision: Double): Double {
    return if (x.isSpecial) {
        x
    } else {
        -cos(x + PI / 2, precision = precision).normalize(precision)
    }
}
fun cot(x: Double, precision: Double): Double {
    return if (x.isSpecial) {
        x
    } else cos(x, precision) / sin(x, precision)
}

fun tan(x: Double, precision: Double):Double {
    return if (x.isSpecial) {
        x
    } else sin(x, precision) / cos(x, precision)
}

// logarithmic

fun lnFormula(x:Double, isAbsLessThen1: Boolean):Sequence<Pair<Double, Int>> {
    return generateSequence(0.0 to 1) {
        it.first - ((-1.0).pow(it.second) * (x - 1).pow(if (isAbsLessThen1) it.second else -it.second) / it.second) to it.second + 1
    }
}
fun ln(x: Double, precision: Double): Double {
    val isAbsLessThen1 = (x - 1).absoluteValue <= 1
    return if (x <= 0 || x.isSpecial) Double.NaN else {
        val raw = lnFormula(x, isAbsLessThen1)
            .takeWhile { precision <= (it.first - precision).absoluteValue && it.second < MAX_ITERATIONS }
            .toList()
            .last()
            .first
        if (isAbsLessThen1) raw else raw + ln(x - 1, precision)
    }
}

fun log(x: Double, base: Double, precision: Double): Double {
    return if (x <= 0 || x.isSpecial || base.isSpecial) Double.NaN
    else ln(x, precision = precision) / ln(base, precision = precision)
}

fun log3(x:Double, precision: Double) = log(x, 3.0, precision)
fun log5(x:Double, precision: Double) = log(x, 5.0, precision)
fun log10(x:Double, precision: Double) = log(x, 10.0, precision)