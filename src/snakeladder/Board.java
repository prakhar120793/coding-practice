package snakeladder;

import java.util.Map;

public class Board {

    private final Map<Integer, Integer> positionToNewPositionMap;

    public Board(final Map<Integer, Integer> positionToNewPositionMap) {
        if (positionToNewPositionMap == null) {
            throw new IllegalArgumentException("positionToNewPositionMap is invalid");
        }
        this.positionToNewPositionMap = positionToNewPositionMap;
    }

    public int nextPosition(final int currentPosition, final int diceRoll) {
        if (currentPosition < 0 || currentPosition > 101) {
            throw new IllegalArgumentException("current position is invalid");
        }
        if (diceRoll < 1 || diceRoll > 6) {
            throw new IllegalArgumentException("dice roll is invalid");
        }
        int newPosition = currentPosition + diceRoll;
        if (this.positionToNewPositionMap.containsKey(newPosition)) {
            return positionToNewPositionMap.get(newPosition);
        }
        if (newPosition > 100) {
            return currentPosition;
        }
        return newPosition;
    }
}
