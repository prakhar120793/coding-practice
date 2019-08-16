public class EditDistance {

    public int[][] answer;

    public int minDistance(String word1, String word2) {
        this.answer = new int[word1.length()][word2.length()];
        for (int i = 0; i < word1.length(); i++) {
            for (int j = 0; j < word2.length(); j++) {
                this.answer[i][j] = -1;
            }
        }
        return minDistance(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    private int minDistance(String word1, String word2, int i, int j) {
        if (i == -1 && j == -1) {
            return 0;
        }
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        if (this.answer[i][j] != -1) {
            return this.answer[i][j];
        }
        int ans;
        if (word1.charAt(i) == word2.charAt(j)) {
            ans = minDistance(word1, word2, i - 1, j - 1);
        } else {
            ans = minDistance(word1, word2, i - 1, j - 1) + 1;
        }
        ans = Math.min(ans, minDistance(word1, word2, i - 1, j) + 1);
        ans = Math.min(ans, minDistance(word1, word2, i, j - 1) + 1);
        this.answer[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new EditDistance().minDistance("intention", "execution"));
    }
}
