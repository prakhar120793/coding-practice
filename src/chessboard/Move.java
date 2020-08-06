package chessboard;

import javafx.util.Pair;

public class Move {
    private final String color;
    private final String pieceName;
    private final Pair<Integer, Integer> startingPosition;
    private final Pair<Integer, Integer> endingPosition;

    public Move(String color, String pieceName, Pair<Integer, Integer> startingPosition,
            Pair<Integer, Integer> endingPosition) {
        this.color = color;
        this.pieceName = pieceName;
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
    }

    public String getColor() {
        return color;
    }

    public String getPieceName() {
        return pieceName;
    }

    public Pair<Integer, Integer> getStartingPosition() {
        return startingPosition;
    }

    public Pair<Integer, Integer> getEndingPosition() {
        return endingPosition;
    }
}
