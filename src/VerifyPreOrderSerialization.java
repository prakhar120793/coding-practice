import java.util.Stack;

/**
 * Question: https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 */
public class VerifyPreOrderSerialization {
    public boolean isValidSerialization(String preorder) {
        String[] preOrderArray = preorder.split(",");
        Stack<String> stack = new Stack<>();
        for (String treeNode : preOrderArray) {
            stack.push(treeNode);
            while (stack.size() >= 3) {
                String a = stack.pop();
                String b = stack.pop();
                String c = stack.pop();
                if (a.equals("#") && b.equals("#") && !c.equals("#")) {
                    stack.push("#");
                } else {
                    stack.push(c);
                    stack.push(b);
                    stack.push(a);
                    break;
                }
            }
        }
        return stack.size() == 1 && stack.peek().equals("#");
    }

    public static void main(String[] args) {
        new VerifyPreOrderSerialization().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#");
    }
}
