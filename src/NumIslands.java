import java.util.ArrayList;
import java.util.List;

public class NumIslands {

    private boolean[][] visited;

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        this.visited = new boolean[grid.length][grid[0].length];
        int connectedComponents = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (this.visited[i][j] == false && grid[i][j] == '1') {
                    connectedComponents++;
                    dfs(i, j, grid);
                }
            }
        }
        return connectedComponents;
    }

    public void dfs(int x, int y, char[][] grid) {
        this.visited[x][y] = true;
        List<int[]> validNeighbours = getValidNeighbours(x, y, grid[0].length, grid.length);
        for (int[] neighbour : validNeighbours) {

            if (grid[neighbour[0]][neighbour[1]] == '1' && this.visited[neighbour[0]][neighbour[1]] == false) {
                dfs(neighbour[0], neighbour[1], grid);
            }

        }
    }

    public List<int[]> getValidNeighbours(int x, int y, int cols, int rows) {
        List<int[]> validNeighbours = new ArrayList<>();
        int[][] neighbours = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int[] neighbour : neighbours) {
            if (neighbour[0] + x >= 0 && neighbour[0] + x < rows && neighbour[1] + y >= 0 && neighbour[1] + y < cols) {
                validNeighbours.add(new int[]{neighbour[0] + x, neighbour[1] + y});
            }
        }
        return validNeighbours;
    }

    public static void main(String[] args) {
        new NumIslands().numIslands(
                new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}});
    }
}
