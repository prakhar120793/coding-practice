import java.util.Stack;

public class KthSmallestElementBST {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class ReturnObj {
        int k;
        TreeNode node;

        public ReturnObj(int k, TreeNode node) {
            this.k = k;
            this.node = node;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return -1;
            //ask the interviewer
        }
        return kthSmallestIterative(root, k);
    }

    private ReturnObj kthSmallestRecursive(TreeNode node, int k) {
        if (node == null) {
            return new ReturnObj(0, null);
        }
        ReturnObj leftReturn = kthSmallestRecursive(node.left, k);
        if (leftReturn.k == k) {
            return leftReturn;
        }
        if (leftReturn.k == k - 1) {
            return new ReturnObj(k, node);
        }
        ReturnObj rightReturn = kthSmallestRecursive(node.right, k - leftReturn.k - 1);
        if (rightReturn.node == null) {
            return new ReturnObj(leftReturn.k + 1, node);
        }
        return new ReturnObj(leftReturn.k + rightReturn.k + 1, rightReturn.node);
    }

    private int kthSmallestIterative(TreeNode node, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = node;
        while (true) {
            while (null != root) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.empty()) {
                TreeNode removedNode = stack.pop();
                k--;
                if (k == 0) {
                    return removedNode.val;
                }
                root = removedNode.right;
            } else {
                return -1;
            }
        }
    }


}
