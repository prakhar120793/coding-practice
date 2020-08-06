package chessboard;

import javafx.util.Pair;

public abstract class Piece {
    private final String color;
    private final String pieceName;

    protected Piece(String color, String pieceName) {
        this.color = color;
        this.pieceName = pieceName;
    }

    //this could be used to validate the move;
    //this would be implemented by the implementation of the pieces.
    public abstract boolean isValidMove(Pair<Integer, Integer> currentPosition, Pair<Integer, Integer> nextPosition);

    public String getColor() {
        return color;
    }

    public String getPieceName() {
        return pieceName;
    }
}
