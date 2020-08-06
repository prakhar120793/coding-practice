package roottoleafsum;

import roottoleafsum.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafSum {

    public List<List<Integer>> getAllPaths(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        return getAllPathsRecursively(root, new ArrayList<>(), 0, sum);
    }

    /**
     *
     *          8
     *         / \
     *        7   5
     *       / \ / \
     *      6  3 2  1
     */

    private List<List<Integer>> getAllPathsRecursively(TreeNode root, List<Integer> currentPath, int currentPathSum,
            int sum) {
        //is leaf node
        if (root.isLeaf()) {
            if (currentPathSum + root.getValue() == sum) {
                List<List<Integer>> paths = new ArrayList<>();
                List<Integer> validPath = new ArrayList<>();
                validPath.addAll(currentPath);
                validPath.add(root.getValue());
                paths.add(validPath);
                return paths;
            } else {
                return new ArrayList<>();
            }
        }
        //is not leaf node
        List<List<Integer>> paths = new ArrayList<>();


        List<Integer> nextPath = new ArrayList<>();
        nextPath.addAll(currentPath);
        nextPath.add(root.getValue());

        if (root.getLeft() != null) {
            paths.addAll(getAllPathsRecursively(root.getLeft(), nextPath, currentPathSum + root.getValue(), sum));
        }

        if (root.getRight() != null) {
            paths.addAll(getAllPathsRecursively(root.getRight(), nextPath, currentPathSum + root.getValue(), sum));
        }

        return paths;
    }

}
