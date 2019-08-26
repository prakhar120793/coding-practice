import java.util.List;

/**
 * Question: https://leetcode.com/problems/word-break/
 */
public class WordBreak {

    private Boolean[] storedAnswer;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.storedAnswer = new Boolean[s.length() + 1];
        return wordBreak(0, s, wordDict);
    }

    private boolean wordBreak(int i, String s, List<String> wordDict) {
        if (this.storedAnswer[i] != null) {
            return this.storedAnswer[i];
        }
        if (i == s.length()) {
            return true;
        }
        boolean answer = false;
        for (String word : wordDict) {
            String subString = s.substring(i);
            if (subString.length() >= word.length()) {
                boolean prefix = true;
                for (int j = 0; j < word.length(); j++) {
                    if (subString.charAt(j) != word.charAt(j)) {
                        prefix = false;
                    }
                }
                if (prefix) {
                    answer = answer || wordBreak(i + word.length(), s, wordDict);
                }
            }
        }
        this.storedAnswer[i] = answer;
        return answer;
    }
}
