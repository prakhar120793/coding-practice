package chessboard;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Pair<Integer, Integer>, Piece> positionToPiece;


    protected Board(
            Map<Pair<Integer, Integer>, Piece> positionToPiece) {
        this.positionToPiece = positionToPiece;
    }

    public static Board createBoard(String[][] board) {
        Map<Pair<Integer, Integer>, Piece> positionToPiece = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //"-" is used to represent an empty
                if (board[i][j] != "-") {
                    Piece piece; //create a new piece and put it in the map
                    String pieceInfo = board[i][j];
                    char color = pieceInfo.charAt(0);
                    char pieceName = pieceInfo.charAt(1);

                    if(pieceName == 'R') {

                    }
                }
            }
        }
        return new Board(positionToPiece);
    }

    public void applyMoves(List<Move> moveList) {
        for (int i = 0; i < moveList.size(); i++) {
            Move move = moveList.get(i);
            boolean isValidMove = isValidMove(move);
            if (isValidMove) {
                //remove from the current position
                Piece pieceToBeMoved = this.positionToPiece.remove(move.getStartingPosition());
                //put to the new position. this is also going to remove if there exist any other piece of the
                // opposite color.
                this.positionToPiece.put(move.getEndingPosition(), pieceToBeMoved);
            }
        }
    }

    private boolean isValidMove(Move move) {
        //check is the piece is there on the place specified;
        Piece piece = this.positionToPiece.get(move.getStartingPosition());
        //is present in the map
        if (piece != null) {
            //check color is same
            if (!piece.getColor().equals(move.getColor())) {
                return false;
            }

            //check is same color is not present on the end loaction
            Piece pieceOnEndPosition = this.positionToPiece.get(move.getEndingPosition());
            if (pieceOnEndPosition != null && pieceOnEndPosition.getColor() == move.getColor()) {
                return false;
            }

            //check if valid move using piece
            piece.isValidMove(move.getStartingPosition(), move.getEndingPosition());
        }

        //is invalid move
        return false;
    }

    public String[][] printBoard() {
        String[][] board = new String[8][8];
        //initialising the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //empty position
                board[i][j] = "-";
            }
        }

        for (Map.Entry<Pair<Integer, Integer>, Piece> pieceEntry : this.positionToPiece.entrySet()) {
            Pair<Integer, Integer> piecePosition = pieceEntry.getKey();
            Piece piece = pieceEntry.getValue();

            board[piecePosition.getKey()][piecePosition.getValue()] = piece.getColor() + piece.getPieceName();
        }
        return board;
    }


}
