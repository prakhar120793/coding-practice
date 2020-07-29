package snakeladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnakeAndLadderGame {
    private static final int WINNING_POSITIONS = 100;

    private final Map<Player, Integer> playerToPlayerPositionMap;
    private final Board board;
    private final Dice dice;

    //createGame
    //factory method
    //this is not part of this class instance
    public static SnakeAndLadderGame createGame(final List<Player> playerList) {
        if (playerList == null && playerList.size() < 2) {
            throw new IllegalArgumentException();
        }
        Dice dice = new Dice();
        Map<Player, Integer> playerToPlayerPositionMap = new HashMap<>();
        playerList.forEach(player -> {
            playerToPlayerPositionMap.put(player, 0);
        });
        Board board = new Board(new HashMap<>());
        return new SnakeAndLadderGame(playerToPlayerPositionMap, board, dice);
    }

    public SnakeAndLadderGame(final Map<Player, Integer> playerToPlayerPositionMap, Board board, Dice dice) {
        this.dice = dice;
        if (playerToPlayerPositionMap == null || board == null) {
            throw new NullPointerException();
        }
        this.playerToPlayerPositionMap = playerToPlayerPositionMap;
        this.board = board;
    }

    //returns the winner
    public Player playGame() {
        Player winner = null;
        while (winner == null) {
            for (Map.Entry<Player, Integer> playerToCurrentPositionEntry : this.playerToPlayerPositionMap.entrySet()) {
                int diceRoll = this.dice.getRandomDiceRoll();
                int newPosition = this.board.nextPosition(playerToCurrentPositionEntry.getValue(), diceRoll);
                if (newPosition == WINNING_POSITIONS) {
                    winner = playerToCurrentPositionEntry.getKey();
                    break;
                }
            }
        }
        return winner;
    }
}
