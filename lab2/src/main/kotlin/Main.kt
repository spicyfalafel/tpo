import mymath.*
import kotlin.math.PI

fun main(args: Array<String>) {
   val step:Double
    if (args.size != 1) {
        step = 0.03;
        println("Шаг наращивания дефолтный $step")
    } else {
        step = args.first().toDouble()
        println("Шаг наращивания $step")
    }
//    writeFuncSeries("reports/cos", func = ::cos, from = -PI, precision = step, until = PI)
//    println("cos done")
//    writeFuncSeries("reports/sin", func = ::sin, from = -PI, precision = step, until = PI)
//    println("sin done")
//    writeFuncSeries("reports/cot", func = ::cot, from = -PI, precision = step, until = PI)
//    println("cot done")
//    writeFuncSeries("reports/tan", func = ::tan, from = -PI, precision = step, until = PI)
//    println("tan done")
//    writeFuncSeries("reports/ln", func = ::ln, from = 0.1, precision = step, until = PI)
//    println("ln done")
//    writeFuncSeries("reports/log3", func = ::log3, from = 0.1, precision = step, until = PI)
//    println("log3 done")
//    writeFuncSeries("reports/log5", func = ::log5, from = 0.1, precision = step, until = PI)
//    println("log5 done")
//    writeFuncSeries("reports/log10", func = ::log10, from = 0.1, precision = step, until = PI)
//    println("log10 done")
    writeFuncSeries("reports/system", func = ::theSystem, from = -6.0, precision = step, until = 15.0)
    println("system done")

    println("Выполнено")
}