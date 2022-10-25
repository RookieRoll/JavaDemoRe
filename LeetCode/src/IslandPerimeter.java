/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * <p>
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 * <p>
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 */
public class IslandPerimeter {
	public static void main(String[] args) {
		IslandPerimeter islandPerimeter = new IslandPerimeter();
		int[][] grid = new int[][]{
				new int[]{0, 1, 0, 0},
				new int[]{1, 1, 1, 0},
				new int[]{0, 1, 0, 0},
				new int[]{1, 1, 0, 0}
		};
		System.out.println(islandPerimeter.islandPerimeter(grid));
	}

	public int islandPerimeter(int[][] grid) {
		int islands = 0, neighbours = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					islands++; // count islands
					if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
					if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
				}
			}
		}

		return islands * 4 - neighbours * 2;
	}
}

