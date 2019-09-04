import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BricksFallingWhenHit {

    private static class UnionFind {

        public static UnionFind createInstance(int size) {
            if (size < 0) {
                throw new IllegalArgumentException();
            }
            int[] parent = new int[size];
            int[] sizeArray = new int[size];

            for (int index = 0; index < size; index++) {
                parent[index] = index;
                sizeArray[index] = 1;
            }

            return new UnionFind(parent, sizeArray);
        }

        private final int[] parent;
        private final int[] size;

        private UnionFind(int[] parent, int[] size) {
            this.parent = parent;
            this.size = size;
        }

        public void union(int element1, int element2) {
            if (element1 >= this.parent.length || element2 >= this.parent.length) {
                throw new IllegalArgumentException();
            }
            int group1 = find(element1);
            int group2 = find(element2);
            if (group1 != group2) {
                if (this.size[group1] > this.size[group2]) {
                    this.parent[group2] = group1;
                    this.size[group1] += this.size[group2];
                } else {
                    this.parent[group1] = group2;
                    this.size[group2] += this.size[group1];
                }
            }
        }

        public int find(int element) {
            if (element >= this.parent.length) {
                throw new IllegalArgumentException();
            }
            int root = element;
            while (!(this.parent[root] == root)) {
                root = parent[root];
            }
            int root1 = element;
            while (!(this.parent[root1] == root1)) {
                root1 = this.parent[root1];
                this.parent[root1] = root;
            }
            return root;
        }
    }

    public int[] hitBricks(int[][] grid, int[][] hits) {
        if (grid.length == 0) {
            throw new IllegalArgumentException();
        }

        int rows = grid.length;
        int cols = grid[0].length;

        int[][] cloneGrid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cloneGrid[i][j] = grid[i][j];
            }
        }

        for (int[] hit : hits) {
            grid[hit[0]][hit[1]] = 0;
        }

        UnionFind unionFind = UnionFind.createInstance(grid.length * grid[0].length);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    for (int[] neighbour : fetchValidNeighbours(row, col, rows, cols)) {
                        if (grid[neighbour[0]][neighbour[1]] == 1) {
                            unionFind.union(getIndexInUnionFind(row, col, cols), getIndexInUnionFind(neighbour, cols));
                        }
                    }
                }
            }
        }

        Set<Integer> wallComponents = new HashSet<>();
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                wallComponents.add(unionFind.find(i));
            }
        }

        int[] solution = new int[hits.length];

        for (int index = hits.length - 1; index >= 0; index--) {
            Set<Integer> distinctNeighbouringComponents = new HashSet<>();
            int[] hit = hits[index];
            int ans = 0;
            if (cloneGrid[hit[0]][hit[1]] == 1) {

                grid[hits[index][0]][hits[index][1]] = 1;

                for (int[] neighbour : fetchValidNeighbours(hit[0], hit[1], rows, cols)) {
                    if (grid[neighbour[0]][neighbour[1]] == 1) {
                        distinctNeighbouringComponents.add(unionFind.find(getIndexInUnionFind(neighbour, cols)));
                    }
                }
                boolean noWallComponent = true;
                for (int component : distinctNeighbouringComponents) {
                    if (!wallComponents.contains(component)) {
                        ans += unionFind.size[component];
                    } else {
                        noWallComponent = false;
                    }
                    unionFind.union(getIndexInUnionFind(hit, cols), component);
                }

                if(!(hit[0]==0) && noWallComponent){
                    ans=0;
                }

                wallComponents = new HashSet<>();
                for (int i = 0; i < cols; i++) {
                    wallComponents.add(unionFind.find(i));
                }
            }
            solution[index] = ans;
        }
        return solution;
    }


    private List<int[]> fetchValidNeighbours(int x, int y, int rows, int cols) {
        int[][] neighbours = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        List<int[]> validNeighbours = new ArrayList<>();
        for (int[] neighbourIndex : neighbours) {
            if (x + neighbourIndex[0] >= 0 && x + neighbourIndex[0] < rows && y + neighbourIndex[1] >= 0 && y + neighbourIndex[1] < cols) {
                validNeighbours.add(new int[]{x + neighbourIndex[0], y + neighbourIndex[1]});
            }
        }
        return validNeighbours;
    }

    private int getIndexInUnionFind(int x, int y, int cols) {
        return x * cols + y;
    }

    private int getIndexInUnionFind(int[] position, int cols) {
        return position[0] * cols + position[1];
    }

    public static void main(String[] args) {

        //[[1,0,0,0],[1,1,1,0]]
        //[[1,0]]

        //[[1],[1],[1],[1],[1]]
        //[[3,0],[4,0],[1,0],[2,0],[0,0]]

        //[[1,0,1],[1,1,1]]
        //[[0,0],[0,2],[1,1]]
        new BricksFallingWhenHit().hitBricks(new int[][]{{1, 0, 1}, {1, 1, 1}}, new int[][]{{0, 0}, {0, 2}, {1, 1}});
    }
}
