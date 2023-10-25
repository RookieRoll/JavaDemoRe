import java.util.Arrays;

/**
 * You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
 * <p>
 * A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
 * <p>
 * Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
 */
public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        NumberOfEnclaves numberOfEnclaves = new NumberOfEnclaves();
        System.out.println(numberOfEnclaves.numEnclaves(grid));
    }

    public int numEnclaves(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if ((i == 0 || j == 0 || i == grid.length - 1 || j == grid[i].length - 1) && grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }
        return Arrays.stream(grid).mapToInt(m -> Arrays.stream(m).sum()).sum();
    }

    private void dfs(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if ((x >= 0 && x < grid.length && y >= 0 && y < grid[x].length) && grid[x][y] == 1) {
                dfs(grid, x, y);
            }
        }
    }
}
