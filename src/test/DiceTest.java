package test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import mainGame.battle.DiceRoll;

public class DiceTest {

    /**
     * Tests the distribution of dice rolls to ensure uniform probability.
     * Rolls a dice 6000 times and checks if each face appears with a similar
     * frequency.
     * We use a 10% acceptable range from the expected frequency to account for
     * random variation.
     */
    @Test
    public void testDiceRollDistribution() {
        // Setup DiceRoll object and roll tally
        DiceRoll dice = new DiceRoll();
        int rolls = 6000;
        int[] tally = new int[6];

        // Roll the dice 6000 times, tallying each result
        for (int i = 0; i < rolls; i++) {
            int result = dice.roll(6);
            tally[result - 1]++; // Increment the tally for the rolled number
        }

        // Determine acceptable range based on expected frequency
        int expectedFrequency = rolls / 6;
        int acceptableRange = (int) (expectedFrequency * 0.1);

        // Validate that each face appears within the acceptable frequency range
        for (int i = 0; i < tally.length; i++) {
            int lowerLimit = expectedFrequency - acceptableRange;
            int upperLimit = expectedFrequency + acceptableRange;
            assertTrue(tally[i] >= lowerLimit && tally[i] <= upperLimit,
                    "Number " + (i + 1) + " is out of acceptable range (" + lowerLimit + " - " + upperLimit + ")");
        }
    }

}
