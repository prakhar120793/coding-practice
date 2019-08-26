public class RegexMatching {

    private Boolean[][] solution;

    public boolean isMatch(String s, String p) {
        this.solution = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int stringIndex, int patternIndex) {
        if (this.solution[stringIndex][patternIndex] != null) {
            return this.solution[stringIndex][patternIndex];
        }
        boolean isMatch = false;
        if (stringIndex == s.length() && (patternIndex == p.length())) {
            isMatch = true;
        } else if (patternIndex == p.length()) {
            isMatch = false;
        }
        //pattern is there
        //string might or might not be there
        else if (p.charAt(patternIndex) == '.' && patternIndex + 1 < p.length() && p.charAt(patternIndex + 1) == '*') {
            if (stringIndex < s.length()) {
                isMatch = isMatch || isMatch(s, p, stringIndex + 1, patternIndex + 2) || //one
                        isMatch(s, p, stringIndex + 1, patternIndex);
            }
            isMatch = isMatch || isMatch(s, p, stringIndex, patternIndex + 2); //zero
        } else if (patternIndex + 1 < p.length() && p.charAt(patternIndex + 1) == '*') {
            if (stringIndex < s.length() && s.charAt(stringIndex) == p.charAt(patternIndex)) {
                isMatch = isMatch || isMatch(s, p, stringIndex + 1, patternIndex + 2) || //one
                        isMatch(s, p, stringIndex + 1, patternIndex); //more
            }
            isMatch = isMatch || isMatch(s, p, stringIndex, patternIndex + 2); //zero
        } else if (stringIndex < s.length() && (p.charAt(patternIndex) == '.' || p.charAt(patternIndex) == s.charAt(stringIndex))) {
            isMatch = isMatch(s, p, stringIndex + 1, patternIndex + 1);
        }
        this.solution[stringIndex][patternIndex] = isMatch;
        return isMatch;
    }

    public static void main(String[] args) {
        System.out.println(new RegexMatching().isMatch("ab",".*c"));
    }
}
