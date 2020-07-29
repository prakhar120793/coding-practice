package snakeladder;

import java.util.Random;

public class Dice {

    public int getRandomDiceRoll() {
        return new Random().nextInt(5) + 1;
    }
}
