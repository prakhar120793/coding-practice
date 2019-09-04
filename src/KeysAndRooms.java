import java.util.List;

/**
 * Question: https://leetcode.com/problems/keys-and-rooms/
 * <p>
 * Starting from room 0, can we visit all rooms?
 * <p>
 * BFS/DFS from room 0.
 */
public class KeysAndRooms {

    private boolean[] visited;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        this.visited = new boolean[rooms.size()];
        if (rooms.size() > 0) {
            dfs(0, rooms);
        }
        for (int i = 0; i < this.visited.length; i++) {
            if (!this.visited[i]) {
                return false;
            }
        }
        return true;
    }

    public void dfs(int room, List<List<Integer>> rooms) {
        if (!this.visited[room]) {
            this.visited[room] = true;
            for (Integer childRoom : rooms.get(room)) {
                dfs(childRoom, rooms);
            }
        }
    }
}
