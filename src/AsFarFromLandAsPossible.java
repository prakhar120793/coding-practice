import java.util.LinkedList;

public class AsFarFromLandAsPossible {

    public int maxDistance(int[][] grid) {
        int size = grid.length;
        int[][] distance = new int[size][size];
        LinkedList<Pair> pointsToBeTraversed = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distance[i][j] = -1;
                if (grid[i][j] == 1) {
                    distance[i][j] = 0;
                    pointsToBeTraversed.addLast(new Pair(i, j));
                }
            }
        }
        int maxDistance = -1;
        while (!pointsToBeTraversed.isEmpty()) {
            Pair pair = pointsToBeTraversed.removeFirst();
            int[][] nextNodes = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] next : nextNodes) {
                int x = pair.x + next[0];
                int y = pair.y + next[1];
                if (x >= 0 && x < size && y >= 0 && y < size && distance[x][y] == -1) {
                    distance[x][y] = distance[pair.x][pair.y] + 1;
                    pointsToBeTraversed.addLast(new Pair(x, y));
                    maxDistance = Math.max(maxDistance, distance[x][y]);
                }
            }
        }
        return maxDistance;
    }

    private class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        new AsFarFromLandAsPossible().maxDistance(new int[][]{
                {1,0,0,0,0,1,0,0,0,1},
                {1,1,0,1,1,1,0,1,1,0},
                {0,1,1,0,1,0,0,1,0,0},
                {1,0,1,0,1,0,0,0,0,0},
                {0,1,0,0,0,1,1,0,1,1},
                {0,0,1,0,0,1,0,1,0,1},
                {0,0,0,1,1,1,1,0,0,1},
                {0,1,0,0,1,0,0,1,0,0},
                {0,0,0,0,0,1,1,1,0,0},
                {1,1,0,1,1,1,1,1,0,0}});
    }
}
