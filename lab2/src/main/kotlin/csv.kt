import java.io.File
import java.io.FileOutputStream
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun write(file:File, objects: Iterable<Any>) =
    FileOutputStream(file.apply { createNewFile() }, true).bufferedWriter().use {
        it.write(objects.joinToString(separator = "\n") { o -> o.toString() } + "\n")
}

fun write(filename: String, objects: Iterable<Any>) {
    val file = File("${filename}_${DateTimeFormatter
        .ofPattern("yyyy-MM-dd_HH-mm-ss")
        .withZone(ZoneOffset.UTC)
        .format(Instant.now())}.csv")
    write(file, objects)
}

val Double.replacedWithDot: String
    get() = this.toString().replace(".", ",")

fun writeFuncSeries(filename: String,
                    func: (Double, Double)->Double,
                    from: Double,
                    precision: Double = 0.001,
                    until: Double = from + precision) {

    val xAndYStrings = generateSequence(from) { it + precision }
        .takeWhile { it <= until }
        .map { "${it.replacedWithDot}; ${func(it, precision).replacedWithDot}" }
        .toList()
    write(filename, xAndYStrings)
}