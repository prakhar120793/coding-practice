import java.util.HashMap;
import java.util.Map;

public class MaximumLevelSumBinaryTree {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private Map<Integer, Integer> levelToSumMap;

    public int maxLevelSum(TreeNode root) {
        this.levelToSumMap = new HashMap<>();
        int depth = dfsTraversal(root, 0);
        int maxLevelSum = Integer.MIN_VALUE;
        int level = 0;
        for (int i = 0; i < depth; i++) {
            if (this.levelToSumMap.get(i) > maxLevelSum) {
                maxLevelSum = this.levelToSumMap.get(i);
                level = i + 1;
            }
        }
        return level;
    }

    private int dfsTraversal(TreeNode root, int level) {
        if (root == null) {
            return level;
        }
        if (this.levelToSumMap.containsKey(level)) {
            this.levelToSumMap.put(level, this.levelToSumMap.get(level) + root.val);
        } else {
            this.levelToSumMap.put(level, root.val);
        }
        int depthLeft = dfsTraversal(root.left, level + 1);
        int depthRight = dfsTraversal(root.right, level + 1);
        return Math.max(depthLeft, depthRight);
    }
}
