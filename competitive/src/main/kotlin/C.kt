fun main() {
    fun readStr() = readln()
    fun readStrings() = readStr().split(" ") //
    fun readInts() = readStrings().map { it.toInt() }
    val (n, k) = readInts()
    val a = readStrings()
    println(a.joinToString("\n")) // each element of array/list of a separate line
}