public class StoneGame {
    private Winnings[][] storedWinnings;

    public boolean stoneGame(int[] piles) {
        this.storedWinnings = new Winnings[piles.length][piles.length];
        Winnings winnings = stoneGame(piles, 0, piles.length - 1);
        return winnings.player1Winnings > winnings.player2Winnings;
    }

    private static class Winnings {
        private int player1Winnings;
        private int player2Winnings;

        Winnings(int player1Winnings, int player2Winnings) {
            this.player1Winnings = player1Winnings;
            this.player2Winnings = player2Winnings;
        }
    }

    private Winnings stoneGame(int[] piles, int start, int last) {
        if (start > last) {
            return new Winnings(0, 0);
        }
        if (this.storedWinnings[start][last] != null) {
            return this.storedWinnings[start][last];
        }

        Winnings game1 = stoneGame(piles, start + 1, last);
        Winnings game2 = stoneGame(piles, start, last - 1);

        int player1Winnings, player2Winnings;

        if (game1.player2Winnings + piles[start] > game2.player2Winnings + piles[last]) {
            player1Winnings = game1.player2Winnings + piles[start];
            player2Winnings = game1.player1Winnings;
        } else if (game1.player2Winnings + piles[start] < game2.player2Winnings + piles[last]) {
            player1Winnings = game2.player2Winnings + piles[last];
            player2Winnings = game2.player1Winnings;
        } else {
            player1Winnings = game2.player2Winnings + piles[last];
            player2Winnings = Math.min(game1.player1Winnings, game2.player1Winnings);
        }
        this.storedWinnings[start][last] = new Winnings(player1Winnings, player2Winnings);
        return this.storedWinnings[start][last];
    }

    public static void main(String[] args) {
        new StoneGame().stoneGame(new int[]{4,2,10,9});
    }
}
