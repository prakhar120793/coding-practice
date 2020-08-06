package chessboard;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        String[][] boardAsString = new String[][]{
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""},
                {"", "", "", "", "", "", "", ""}
        };

        Board board = Board.createBoard(boardAsString);
        List<Move> moves = new ArrayList<>();

        //add moves

        board.applyMoves(moves);

        System.out.println(board.printBoard());
    }
}
