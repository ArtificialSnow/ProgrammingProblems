package problems.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

//TODO this is slow
public class _407_TrappingRainWaterII {

    public static int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static class Cell {
        public int i;
        public int j;
        public int height;

        public Cell(int i, int j, int height) {
            this.i = i;
            this.j = j;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;
        if (m == 0) return 0;

        PriorityQueue<Cell> queue = new PriorityQueue<>(1, Comparator.comparingInt(a -> a.height));
        boolean[][] visited = new boolean[n][m];
        for(int i = 1; i < n-1; i++) {
            queue.add(new Cell(i, 0, heightMap[i][0]));
            queue.add(new Cell(i, m - 1, heightMap[i][m - 1]));
        }

        for (int i = 1; i < m-1; i++) {
            queue.add(new Cell(0, i, heightMap[0][i]));
            queue.add(new Cell(n - 1, i, heightMap[n - 1][i]));
        }

        int sum = 0;
        while(!queue.isEmpty()) {
            Cell current = queue.poll();
            if (visited[current.i][current.j]) continue;

            sum += Math.max(0, current.height - heightMap[current.i][current.j]);
            visited[current.i][current.j] = true;
            for (int[] dir : dirs) {
                int row = current.i + dir[0];
                int col = current.j + dir[1];
                if (row <= 0 || row >= n-1 || col <= 0 || col >= m-1 || visited[row][col]) continue;
                queue.add(new Cell(row, col, Math.max(current.height, heightMap[current.i][current.j])));
            }
        }

        return sum;
    }
}
