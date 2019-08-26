import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/maximum-product-of-word-lengths/
public class MaximumProductOfWordLength {
    //edge cases: single word, empty
    //base case answer: 0
    public int maxProduct(String[] words) {
        Map<Character, Integer>[] charMapOfWords = new Map[words.length];
        for (int i = 0; i < words.length; i++) {
            charMapOfWords[i] = new HashMap<>();
            for (int j = 0; j < words[i].length(); j++) {
                if (charMapOfWords[i].containsKey(words[i].charAt(j))) {
                    Integer integer = charMapOfWords[i].get(words[i].charAt(j));
                    charMapOfWords[i].put(words[i].charAt(j), ++integer);
                } else {
                    charMapOfWords[i].put(words[i].charAt(j), 1);
                }
            }
        }

        int maxProduct = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i+1; j < words.length; j++) {
                boolean distinct = true;
                for (int k = 0; k < words[i].length(); k++) {
                    if(charMapOfWords[j].containsKey(words[i].charAt(k))){
                        distinct = false;
                    }
                }
                if(distinct){
                    maxProduct=Math.max(maxProduct,words[i].length()*words[j].length());
                }
            }
        }
        return maxProduct;
    }


    //optimizations possible:
    //we can reduce the number of comparisons by just comparing the largest string in cases of repeated characters.

}
