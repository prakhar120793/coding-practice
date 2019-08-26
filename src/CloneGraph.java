import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clone Graph: https://leetcode.com/problems/clone-graph/
 *
 * Questions:
 * Is it directed? No.
 *
 * A-B {Node A children: B} {Node B children: A}
 *
 * Complexity? How to handle the node when already visited node occurs?
 *
 * DFS... When already visited node occurs as the child then put that reference itself. Maintain a map for already visited nodes.
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;


        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Map<Node, Node> clonedNodesMap;

    public Node cloneGraph(Node node) {
        this.clonedNodesMap = new HashMap<>();
        return cloneRecursively(node);
    }

    private Node cloneRecursively(Node node){
        if(this.clonedNodesMap.containsKey(node)){
            return this.clonedNodesMap.get(node);
        }
        Node clonedNode = new Node(node.val, new ArrayList<>());
        this.clonedNodesMap.put(node, clonedNode);
        for (Node child: node.neighbors) {
            clonedNode.neighbors.add(cloneRecursively(child));
        }
        return clonedNode;
    }
}
