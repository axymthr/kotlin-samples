package dice

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
    val totalRolls = series.rollsList.sumOf { it.value } // 5 + 20 = 25
    println(totalRolls)
}
