fun main(args: Array<String>) {
    var n = readln().toInt() // read integer from the input
    val reached = HashSet<Int>() // a mutable hash set
    while (reached.add(n)) n = fFunctional(n) // iterate function f
    println(reached.size) // print answer to the output
}

private fun readStr() = readln() // string line
private fun readInt() = readStr().toInt() // single int
// similar for other types you'd use in your solutions

tailrec fun removeZeroes(x: Int): Int =
    if (x % 10 == 0) removeZeroes(x / 10) else x

fun fFunctional(x: Int) = removeZeroes(x + 1)

fun fImperative(x: Int): Int {
    var cur = x + 1
    while (cur % 10 == 0) cur /= 10
    return cur
}