import java.util.ArrayList;
import java.util.List;

/**
 * Question: https://leetcode.com/problems/word-break-ii/
 */
public class WordBreakII {

    private List<String>[] storedAnswer;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.storedAnswer = new List[s.length()];
        return wordBreak(0, s, wordDict);
    }

    private List<String> wordBreak(int i, String s, List<String> wordDict) {
        if (this.storedAnswer[i] != null) {
            return this.storedAnswer[i];
        }
        List<String> answer = new ArrayList<>();
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
                    if (subString.length() == word.length()) {
                        answer.add(subString);
                    } else if (subString.length() > word.length()) {
                        List<String> recursiveAns = wordBreak(i + word.length(), s, wordDict);
                        for (String str : recursiveAns) {
                            answer.add(s.substring(i, i + word.length()) + " " + str);
                        }
                    }
                }
            }
        }
        this.storedAnswer[i] = answer;
        return answer;
    }
}
