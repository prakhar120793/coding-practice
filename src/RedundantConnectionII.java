import java.util.*;

public class RedundantConnectionII {

    private class GraphNode {
        private int val;
        private List<Integer> outgoingEdges;
        private List<Integer> incomingEdges;

        public GraphNode(int val) {
            this.val = val;
            this.outgoingEdges = new ArrayList<>();
            this.incomingEdges = new ArrayList<>();
        }

        public void addIncomingEdge(int node) {
            this.incomingEdges.add(node);
        }

        public void addOutgoingEdge(int node) {
            this.outgoingEdges.add(node);
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        Map<Integer, GraphNode> graph = new HashMap<>();
        Map<List<Integer>, Integer> edgeRank = new HashMap<>();
        int counter = 0;
        for (int[] edge : edges) {
            edgeRank.put(new ArrayList<Integer>() {{
                add(edge[0]); add(edge[1]);
            }}, counter++);
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new GraphNode(edge[0]));
            }
            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new GraphNode(edge[1]));
            }
            graph.get(edge[0]).addOutgoingEdge(edge[1]);
            graph.get(edge[1]).addIncomingEdge(edge[0]);
        }
        int source = edges[0][0];
        Set<Integer> visitedNodes = new HashSet<>();
        Stack<GraphNode> stack = new Stack<>();
        stack.push(graph.get(source));
        Stack<Integer> visit = new Stack<>();

        for(GraphNode graphNode:graph.values()){
            if(graphNode.incomingEdges.size() == 2){
                return new int[]{graphNode.incomingEdges.get(1), graphNode.val};
            }
        }

        while (!stack.empty()) {
            GraphNode node = stack.pop();
            if (node == null) {
                visit.pop();
                continue;
            }
            if (visitedNodes.contains(node.val)) {
                if (node.incomingEdges.size() == 2) {
                    return new int[]{node.incomingEdges.get(1), node.val};
                } else {
                    List<Integer> cycle = new ArrayList<>();
                    cycle.add(node.val);
                    while (visit.peek() != node.val) {
                        cycle.add(visit.pop());
                    }
                    cycle.add(node.val);
                    List<Integer> largestEdge = new ArrayList<Integer>() {{
                        add(cycle.get(cycle.size() - 1)); add(cycle.get(cycle.size() - 2));
                    }};
                    int largestRank = edgeRank.get(largestEdge);
                    for (int i = cycle.size() - 2; i > 0; i--) {
                        int finalI = i;
                        List<Integer> edge = new ArrayList<Integer>() {{
                            add(cycle.get(finalI)); add(cycle.get(finalI - 1));
                        }};
                        int rank = edgeRank.get(edge);
                        if (rank > largestRank) {
                            largestRank = rank;
                            largestEdge = edge;
                        }
                    }
                    return new int[]{largestEdge.get(0), largestEdge.get(1)};
                }
            } else {
                visit.push(node.val);
                visitedNodes.add(node.val);
                stack.push(null);
                for (int outgoingNode : node.outgoingEdges) {
                    stack.push(graph.get(outgoingNode));
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new RedundantConnectionII().findRedundantDirectedConnection(new int[][]{{2,1},{3,1},{4,2},{1,4}});
    }

}
