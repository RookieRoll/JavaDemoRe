public class IslandPerimeter {
	public int islandPerimeter(int[][] grid) {
		int repeat = 0;
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					count++;
					// 上边是否有陆地并且注意判断边界；
					if (i - 1 >= 0 && grid[i - 1][j] == 1)
						repeat++;
					// 下边是否有陆地，
					if (i + 1 < grid.length && grid[i + 1][j] == 1)
						repeat++;
					if (j - 1 >= 0 && grid[i][j - 1] == 1)
						repeat++;
					if (j + 1 < grid[i].length && grid[i][j + 1] == 1)
						repeat++;
				}
			}
		}
		return 4 * count - repeat;
	}
}
