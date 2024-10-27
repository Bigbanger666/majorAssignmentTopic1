package mainGame.battle;

import java.util.Random;

public class DiceRoll {

    /*
     Rolls a virtual dice with a specified number of sides.
     Returns a random result between 1 and the given number of sides.
    */
    public int roll(int sides) {
        Random rand = new Random();
        return rand.nextInt(sides) + 1; // Generate random roll result from 1 to `sides`
    }
}
