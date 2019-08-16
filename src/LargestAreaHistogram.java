import javafx.util.Pair;

import java.util.Stack;

public class LargestAreaHistogram {

    public int largestRectangleArea(int[] heights) {
        int[] rightNextMin = calculateRightNextMin(heights);
        int[] leftNextMin = calculateLeftNextMin(heights);
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int leftIndex = 0, rightIndex = heights.length-1;
            if(rightNextMin[i]!=-1){
                rightIndex = rightNextMin[i]-1;
            }
            if(leftNextMin[i]!=-1){
                leftIndex = leftNextMin[i]+1;
            }
            ans = Math.max(ans, heights[i] * (rightIndex - leftIndex + 1));
        }
        return ans;
    }

    private int[] calculateLeftNextMin(int[] heights) {
        int[] leftNextMin = new int[heights.length];
        for (int i = 0; i < leftNextMin.length; i++) {
            leftNextMin[i] = -1;
        }
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.empty() && stack.peek().getValue() > heights[i]) {
                Pair<Integer, Integer> pair = stack.pop();
                leftNextMin[pair.getKey()] = i;
            }
            stack.push(new Pair<>(i, heights[i]));
        }
        return leftNextMin;
    }

    private int[] calculateRightNextMin(int[] heights) {
        int[] rightNextMin = new int[heights.length];
        for (int i = 0; i < rightNextMin.length; i++) {
            rightNextMin[i] = -1;
        }
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.empty() && stack.peek().getValue() > heights[i]) {
                Pair<Integer, Integer> pair = stack.pop();
                rightNextMin[pair.getKey()] = i;
            }
            stack.push(new Pair<>(i, heights[i]));
        }
        return rightNextMin;
    }

    public static void main(String[] args) {
        System.out.println(new LargestAreaHistogram().largestRectangleArea(new int[]{1}));
    }
}
