package dice

import com.google.protobuf.TextFormat
import dice.DiceSeriesKt.diceRoll


fun main() {
    val series = diceSeries {
        rolls += listOf(
            diceRoll { value = 5 },
            diceRoll {
                value = 20
                nickname = "critical hit"
            }
        )
    }
    println(series.toString())
    val text = object {}.javaClass.getResource("/dice_series.textproto")?.readText(Charsets.UTF_8)
    val read = TextFormat.parse(text, DiceSeries::class.java)
//    val builder = DiceSeries.newBuilder()
//    TextFormat.merge(text, builder)
//    val read = builder.build()
    val totalRolls = read.rollsList.sumOf { it.value } // 4 + 6 + 19 = 29
    println(totalRolls)
}
