import java.util.ArrayList;
import java.util.List;

/**
 * Question: https://leetcode.com/problems/course-schedule/
 * <p>
 * Cycle detection. Using dfs/recursion.
 *
 */
public class CourseSchedule {

    private enum NodeState {
        VISITED, UNVISITED, IN_PROCESS
    }

    private NodeState[] nodeStates;
    List<Integer>[] graph;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        this.graph = new List[numCourses];
        this.nodeStates = new NodeState[numCourses];
        for (int index = 0; index < numCourses; index++) {
            this.nodeStates[index] = NodeState.UNVISITED;
            this.graph[index] = new ArrayList<>();
        }
        for (int[] edge : prerequisites) {
            this.graph[edge[0]].add(edge[1]);
        }
        boolean hasCycle = false;
        for (int index = 0; index < numCourses; index++) {
            hasCycle = hasCycle || hasCycle(index);
        }
        return !hasCycle;
    }

    public boolean hasCycle(int node) {
        switch (this.nodeStates[node]) {
            case IN_PROCESS:
                return true;
            case VISITED:
                return false;
            case UNVISITED:
                this.nodeStates[node] = NodeState.IN_PROCESS;
                boolean hasCycle = false;
                for (int child : this.graph[node]) {
                    hasCycle = hasCycle || hasCycle(child);
                }
                this.nodeStates[node] = NodeState.VISITED;
                return hasCycle;
        }
        return false;
    }
}
