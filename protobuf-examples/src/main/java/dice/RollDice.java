package dice;

import dice.DiceSeries.DiceRoll;

public class RollDice {
    public static void main(String[] args) {
        DiceSeries series = DiceSeries.newBuilder()
                .addRolls(DiceRoll.newBuilder()
                        .setValue(5))
                .addRolls(DiceRoll.newBuilder()
                        .setValue(20)
                        .setNickname("critical hit"))
                .build();
    }
}
